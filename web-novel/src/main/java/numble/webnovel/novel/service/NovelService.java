package numble.webnovel.novel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.episode.domain.Episode;
import numble.webnovel.episode.repository.EpisodeRepository;
import numble.webnovel.exception.WebNovelServiceException;
import numble.webnovel.library.domain.Library;
import numble.webnovel.library.repository.LibraryRepository;
import numble.webnovel.member.domain.Member;
import numble.webnovel.novel.domain.Novel;
import numble.webnovel.novel.dto.NovelDetailInfoResponse;
import numble.webnovel.novel.dto.NovelInfoResponseList;
import numble.webnovel.novel.dto.NovelRegisterRequest;
import numble.webnovel.novel.dto.NovelUpdateRequest;
import numble.webnovel.novel.enums.Genre;
import numble.webnovel.novel.enums.SerialStatus;
import numble.webnovel.novel.repository.NovelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static numble.webnovel.exception.ErrorCode.NO_EXISTS_EPISODES;
import static numble.webnovel.exception.ErrorCode.NO_EXISTS_NOVEL;

@Service
@RequiredArgsConstructor
public class NovelService {

    private final NovelRepository novelRepository;
    private final EpisodeRepository episodeRepository;
    private final LibraryRepository libraryRepository;

    @Transactional
    public void registerNovel(NovelRegisterRequest request){
        Novel novel = request.toNovel();
        novelRepository.save(novel);
    }

    @Transactional
    public void updateNovel(Long novelId, NovelUpdateRequest request){
        Novel novel = this.findNovelById(novelId);
        novel.updateNovel(request);
    }

    @Transactional
    public void deleteNovel(Long novelId){
        Novel novel = this.findNovelById(novelId);
        novelRepository.delete(novel);
    }

    @Transactional(readOnly = true)
    public NovelInfoResponseList showNovelList(){
        List<Novel> novelList = novelRepository.findAll();
        return NovelInfoResponseList.toNovelInfoResponseList(novelList);
    }

    @Transactional(readOnly = true)
    public NovelInfoResponseList showNovelListBySearchWord(String searchWord){
        List<Novel> novelList = novelRepository.findByTitleContainingOrAuthorContaining(searchWord, searchWord);
        return NovelInfoResponseList.toNovelInfoResponseList(novelList);
    }

    @Transactional(readOnly = true)
    public NovelInfoResponseList showNovelListByGenre(String genreName){
        Genre genre = Genre.toGenreCode(genreName);
        List<Novel> novelList = novelRepository.findByGenre(genre);
        return NovelInfoResponseList.toNovelInfoResponseList(novelList);
    }

    @Transactional(readOnly = true)
    public NovelInfoResponseList showNovelListBySerialStatus(String serialStatusName){
        SerialStatus status = SerialStatus.toNovelStatus(serialStatusName);
        List<Novel> novelList = novelRepository.findBySerialStatus(status);
        return NovelInfoResponseList.toNovelInfoResponseList(novelList);
    }

    @Transactional(readOnly = true)
    public NovelDetailInfoResponse showNovelDetails(Member member, Long novelId){
        Novel novel = this.findNovelById(novelId);
        List<Episode> episodes = episodeRepository.findByNovel(novel)
                .orElseThrow(() -> new WebNovelServiceException(NO_EXISTS_EPISODES));
        List<Library> libraryList = libraryRepository.findByMemberAndEpisodeIn(member, episodes);
        return NovelDetailInfoResponse.toResponse(novel, episodes, libraryList);
    }

    private Novel findNovelById(Long novelId){
        return novelRepository.findById(novelId)
                .orElseThrow(() -> new WebNovelServiceException(NO_EXISTS_NOVEL));
    }
}
