package numble.webnovel.repository;

import numble.webnovel.domain.NovelEpisode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NovelEpisodeRepository extends JpaRepository<NovelEpisode, String> {
}
