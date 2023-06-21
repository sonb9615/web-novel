package numble.webnovel.episode.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.episode.domain.Episode;
import numble.webnovel.episode.dto.EpisodeRegisterRequest;
import numble.webnovel.episode.repository.EpisodeRepository;
import numble.webnovel.novel.domain.Novel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EpisodeService {

    private final EpisodeRepository episodeRepository;

    @Transactional
    public void registerEpisode(Novel novel, EpisodeRegisterRequest request){
        Episode episode = request.toEpisode(novel);
        episodeRepository.save(episode);
    }


}
