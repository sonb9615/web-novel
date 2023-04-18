package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.Novel;
import numble.webnovel.repository.NovelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NovelService {

    private final NovelRepository novelRepository;
    private final UUIDGeneration uuidGeneration;

    @Transactional
    public void saveNovel(Novel novel){
        if(novel.getNovelId().isEmpty()) {
            novel.setNovelId(uuidGeneration.getUUID());
            novel.setRegDt(LocalDateTime.now());
        }else{
            novel.setUdtDt(LocalDateTime.now());
        }
        novelRepository.save(novel);
    }

    @Transactional
    public Novel findNovel(String novelId){
        return novelRepository.findById(novelId);
    }

    @Transactional
    public void plusNovelLickCnt(String novelId, int cnt){
        Novel novel = this.findNovel(novelId);
        novel.setLikeCnt(novel.getLikeCnt() + cnt);
        novel.setUdtDt(LocalDateTime.now());
        this.saveNovel(novel);
    }

    public boolean validateRequestParam(Novel novel){
        return !novel.getTitle().isEmpty() && !novel.getAuthor().isEmpty() && novel.getEpisodeCost() >= 0;
    }

}
