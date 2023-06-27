package numble.webnovel.episode.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.episode.domain.Episode;
import numble.webnovel.episode.dto.EpisodeRegisterRequest;
import numble.webnovel.episode.dto.EpisodeUpdateRequest;
import numble.webnovel.episode.repository.EpisodeRepository;
import numble.webnovel.exception.WebNovelServiceException;
import numble.webnovel.novel.domain.Novel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static numble.webnovel.exception.ErrorCode.NO_EXISTS_EPISODE;

@Service
@RequiredArgsConstructor
public class EpisodeService {

    private final EpisodeRepository episodeRepository;

    @Transactional
    public void registerEpisode(Novel novel, EpisodeRegisterRequest request){
        Episode episode = request.toEpisode(novel);
        episodeRepository.save(episode);
    }

    @Transactional
    public void updateEpisode(Long episodeId, EpisodeUpdateRequest request){
        Episode episode = this.findEpisodeById(episodeId);
        episode.updateContent(request.getContent());
        episode.updateFreeYn(request.isFreeYn());
        episode.updateTotalPage(request.getTotalPage());
        episode.updateCapacity(request.getCapacity());
        episode.updateUdtDt();
    }

    @Transactional
    public void deleteEpisode(Long episodeId){
        Episode episode = this.findEpisodeById(episodeId);
        episodeRepository.delete(episode);
    }

    private Episode findEpisodeById(Long episodeId){
        return episodeRepository.findById(episodeId)
                .orElseThrow(()-> new WebNovelServiceException(NO_EXISTS_EPISODE));
    }

}
