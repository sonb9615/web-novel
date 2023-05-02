package numble.webnovel.repository;

import numble.webnovel.domain.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, String> {
}
