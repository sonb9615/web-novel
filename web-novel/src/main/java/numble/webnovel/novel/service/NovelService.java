package numble.webnovel.novel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.exception.WebNovelServiceException;
import numble.webnovel.novel.domain.Novel;
import numble.webnovel.novel.dto.NovelRegisterRequest;
import numble.webnovel.novel.dto.NovelUpdateRequest;
import numble.webnovel.novel.repository.NovelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static numble.webnovel.exception.ErrorCode.NO_EXISTS_NOVEL;

@Service
@RequiredArgsConstructor
public class NovelService {

    private final NovelRepository novelRepository;

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
    public Novel findNovelById(Long novelId){
        return novelRepository.findById(novelId)
                .orElseThrow(() -> new WebNovelServiceException(NO_EXISTS_NOVEL));
    }
}
