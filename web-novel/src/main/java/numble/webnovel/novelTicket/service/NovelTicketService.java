package numble.webnovel.novelTicket.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.exception.WebNovelServiceException;
import numble.webnovel.member.domain.Member;
import numble.webnovel.member.repository.MemberRepository;
import numble.webnovel.novel.domain.Novel;
import numble.webnovel.novel.repository.NovelRepository;
import numble.webnovel.novelTicket.domain.NovelTicket;
import numble.webnovel.novelTicket.dto.ChargeNovelTicketRequest;
import numble.webnovel.novelTicket.repository.NovelTicketRepository;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

import static numble.webnovel.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class NovelTicketService {

    private final NovelTicketRepository novelTicketRepository;
    private final RedissonClient redissonClient;
    private final MemberRepository memberRepository;
    private final NovelRepository novelRepository;

    private static final String NOVEL_TICKET_LOCK_NAME = "TICKET_CHARGE";

    @Transactional
    public void chargeNovelTicket(Member currentMember, Long novelId, ChargeNovelTicketRequest request){
        String lockName = NOVEL_TICKET_LOCK_NAME +  currentMember.getMemberId();
        RLock lock = redissonClient.getLock(lockName);

        try {
            if(!lock.tryLock(1,3, TimeUnit.SECONDS)){
                throw new WebNovelServiceException(NO_AVAILABLE_LOCK);
            }
            //소설 유무 확인
            Novel novel = novelRepository.findById(novelId)
                    .orElseThrow(() -> new WebNovelServiceException(NO_EXISTS_NOVEL));
            //유저 보유 캐시 확인
            Member member = memberRepository.findById(currentMember.getMemberId())
                    .orElseThrow(() -> new WebNovelServiceException(NO_EXISTS_MEMBER));

            if(!member.isEnoughOwnCache(novel.getEpisodeCost(), request.getChargeTicketCnt())){
                throw new WebNovelServiceException(NO_ENOUGH_CACHE);
            }
            member.chargeNovelTicket(novel.getEpisodeCost() * request.getChargeTicketCnt());

            //티켓 구매
            if(novelTicketRepository.existsByMemberAndNovelId(member, novelId)){
                //티켓 구매 이력이 있다면 해당 데이터에 업데이트
                NovelTicket novelTicket = findByMemberIdNovelId(member.getMemberId(), novelId);
                novelTicket.chargeNovelTicket(request.getChargeTicketCnt());
            }else {
                //티켓 구매 이력이 없다면 새로 저장
                NovelTicket novelTicket = request.toNovelTicket(currentMember, novelId, novel.getEpisodeCost());
                novelTicketRepository.save(novelTicket);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    private NovelTicket findByMemberIdNovelId(Long memberId, Long novelId){
        return novelTicketRepository.findNovelTicketByMemberIdAndNovelId(memberId, novelId)
                .orElseThrow(() -> new WebNovelServiceException(INVALID_TICKET));
    }
}
