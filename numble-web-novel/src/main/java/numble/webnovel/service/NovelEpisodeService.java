package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.NovelEpisode;
import numble.webnovel.enums.ExceptionEnum;
import numble.webnovel.exceptions.CommonException;
import numble.webnovel.repository.NovelEpisodeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NovelEpisodeService {

    private final NovelEpisodeRepository novelEpisodeRepository;

    public NovelEpisode findNovelEpisodeById(String episodeId){
        NovelEpisode novelEpisode = novelEpisodeRepository.findById(episodeId);
        if(novelEpisode == null){
            throw new CommonException(ExceptionEnum.RESULT_NOT_EXIST_EXCEPTION);
        }
        return novelEpisode;
    }

}
