package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.Novel;
import numble.webnovel.enums.CommonStatusEnum;
import numble.webnovel.repository.NovelRepository;
import numble.webnovel.repository.dto.request.NovelSaveRequest;
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
    public void saveNovel(NovelSaveRequest request){
        if(CommonStatusEnum.CREATE.equals(request.getStatus())) {
            request.setNovelId(uuidGeneration.getUUID());
            Novel novel = request.toNovel();
            novelRepository.save(novel);
        }else if(CommonStatusEnum.UPDATE.equals(request.getStatus())){
            Novel novel = novelRepository.findById(request.getNovelId());
            novel.updateNovel(request.getNovelInfo(), request.getNovelImg(), request.getEpisodeCost(), request.getGenre());
        }else{
            novelRepository.delete(request.getNovelId());
        }

    }


    @Transactional
    public void save(Novel novel){

    }

    @Transactional
    public void update(){

    }

    @Transactional
    public void delete(){

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
    }

    public boolean validateRequestParam(NovelSaveRequest request){
        return !request.getTitle().isEmpty() && !request.getAuthor().isEmpty() && request.getEpisodeCost() >= 0;
    }

}
