package numble.webnovel.novel.repository;

import numble.webnovel.novel.domain.Novel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NovelRepository extends JpaRepository<Novel, Long> {


}
