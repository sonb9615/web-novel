package numble.webnovel.library.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.episode.domain.Episode;
import numble.webnovel.episode.repository.EpisodeRepository;
import numble.webnovel.exception.WebNovelServiceException;
import numble.webnovel.library.domain.Library;
import numble.webnovel.library.dto.OwnEpisodeReadInfoResponse;
import numble.webnovel.library.repository.LibraryRepository;
import numble.webnovel.member.domain.Member;
import numble.webnovel.novel.domain.Novel;
import numble.webnovel.novelTicket.domain.NovelTicket;
import numble.webnovel.novelTicket.repository.NovelTicketRepository;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

import static numble.webnovel.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final RedissonClient redissonClient;
    private final NovelTicketRepository novelTicketRepository;
    private final EpisodeRepository episodeRepository;
    private final LibraryRepository libraryRepository;

    private static final String EPISODE_PURCHASE_LOCK_NAME = "EPISODE_PURCHASE";

    @Transactional
    public void purchaseEpisode(Member currentMember, Long episodeId){

        String lockName = EPISODE_PURCHASE_LOCK_NAME + currentMember.getMemberId();
        RLock lock = redissonClient.getLock(lockName);

        try{
            if(!lock.tryLock(1,3, TimeUnit.SECONDS)){
                throw  new WebNovelServiceException(NO_AVAILABLE_LOCK);
            }
            Episode episode = episodeRepository.findById(episodeId)
                    .orElseThrow(() -> new WebNovelServiceException(NO_EXISTS_EPISODE));
            Long novelId = episode.getNovel().getNovelId();
            // 이미 존재하는 에피소드 확인
            throwIfAlreadyOwnEpisode(currentMember, episode);
            // 에피소드가 무료이면 이용권 사용하지 않아도 된다.
            if(!episode.isFreeYn()){
                // 소설 이용권 충분한지 확인
                NovelTicket novelTicket = novelTicketRepository.findNovelTicketByMemberIdAndNovelId(currentMember.getMemberId(), novelId)
                        .orElseThrow(() -> new WebNovelServiceException(NO_ENOUGH_TICKET));
                if(!novelTicket.isEnoughNovelTicket()){
                    throw new WebNovelServiceException(NO_ENOUGH_TICKET);
                }
                novelTicket.useNovelTicket();
            }
            // 라이브러리에 저장
            Library library = Library.createLibrary(currentMember, episode);
            libraryRepository.save(library);

        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }finally {
            lock.unlock();
        }
    }

    private void throwIfAlreadyOwnEpisode(Member member, Episode episode){
        if(libraryRepository.existsByMemberAndEpisode(member, episode)){
            throw new WebNovelServiceException(ALREADY_EXIST_EPISODE);
        }
    }

    @Transactional
    public OwnEpisodeReadInfoResponse readOwnEpisode(Member currentMember, Long episodeId){
        Library library = findByMemberIdEpisodeId(episodeId, currentMember.getMemberId());
        Episode episode = library.getEpisode();
        Novel novel = episode.getNovel();
        //소설 구매 카운트 +1
        novel.plusPaymentCnt();
        library.checkRead(library.getLastReadPage());
        return OwnEpisodeReadInfoResponse.toInfoResponse(library);
    }

    @Transactional
    public void readNextPage(Member currentMember, Long episodeId){
        Library library = findByMemberIdEpisodeId(episodeId, currentMember.getMemberId());
        Episode episode = library.getEpisode();
        throwIfExceedTotalPage(episode, library.getLastReadPage());
        library.readNextPage();
    }

    public void readPreviousPage(Member currentMember, Long episodeId){
        Library library = findByMemberIdEpisodeId(episodeId, currentMember.getMemberId());
        Episode episode = library.getEpisode();
        throwIfNoExistPreviousPage(episode, library.getLastReadPage());
        library.readPreviousPage();
    }

    private void throwIfExceedTotalPage(Episode episode,int currentPage){
        if(!episode.checkNextPage(currentPage)){
            throw new WebNovelServiceException(EXCEED_TOTAL_PAGE);
        }
    }

    private void throwIfNoExistPreviousPage(Episode episode, int currentPage){
        if(!episode.checkPreviousPage(currentPage)){
            throw new WebNovelServiceException(EXCEED_TOTAL_PAGE);
        }
    }

    private Library findByMemberIdEpisodeId(Long episodeId, Long memberId){
        return libraryRepository.findByEpisodeIdAndMemberIdWithEpisodeAndNovel(episodeId, memberId)
                .orElseThrow(() -> new WebNovelServiceException(NOT_FOUND_EPISODE));
    }
}
