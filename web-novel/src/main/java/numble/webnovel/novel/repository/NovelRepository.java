package numble.webnovel.novel.repository;

import numble.webnovel.novel.domain.Novel;
import numble.webnovel.novel.enums.Genre;
import numble.webnovel.novel.enums.SerialStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NovelRepository extends JpaRepository<Novel, Long> {

    List<Novel> findByGenre(Genre genre);
    List<Novel> findBySerialStatus(SerialStatus status);
    List<Novel> findByTitleContainingOrAuthorContaining(String title, String author);
}
