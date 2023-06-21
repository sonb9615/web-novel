package numble.webnovel.episode.repository;

import numble.webnovel.episode.domain.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
}
