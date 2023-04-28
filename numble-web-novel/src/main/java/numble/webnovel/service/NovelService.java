package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.Novel;
import numble.webnovel.enums.CommonStatusEnum;
import numble.webnovel.enums.ExceptionEnum;
import numble.webnovel.exceptions.CommonException;
import numble.webnovel.repository.NovelRepository;
import numble.webnovel.repository.dto.request.NovelSaveRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NovelService {

    private final UUIDGeneration uuidGeneration;
    private final NovelRepository novelRepository;

    @Transactional
    public void saveNovel(NovelSaveRequest request){
        if(CommonStatusEnum.CREATE.equals(request.getStatus())) {
            request.setNovelId(uuidGeneration.getUUID());
            Novel novel = request.toNovel();
            novelRepository.save(novel);
        }else{
            Novel novel = this.findNovel(request.getNovelId());
            if(CommonStatusEnum.UPDATE.equals(request.getStatus())){
                novel.updateNovel(request.getNovelInfo(), request.getNovelImg(), request.getEpisodeCost(), request.getGenre());
            }else{
                novelRepository.delete(novel);
            }
        }
    }

    @Transactional
    public Novel findNovel(String novelId){
        return novelRepository.findById(novelId)
                .orElseThrow(() -> new CommonException(ExceptionEnum.RESULT_NOT_EXIST_EXCEPTION));
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
