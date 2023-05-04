package numble.webnovel.repository;

import numble.webnovel.domain.Novel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NovelRepository extends JpaRepository<Novel, Long> {
}
