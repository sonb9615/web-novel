package numble.webnovel.episode.repository;

import numble.webnovel.episode.domain.Episode;
import numble.webnovel.novel.domain.Novel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {

    boolean existsByEpisodeId(Long episodeId);

    @Query("SELECT e, l FROM Episode e LEFT OUTER JOIN Library l on e.episodeId = l.episode.episodeId and l.member.memberId = :memberId WHERE e.novel.novelId = :novelId")
    Optional<List<Episode>> findByNovelIdWithLibrary(@Param("novelId") Long novelId, @Param("memberId") Long memberId);

    Optional<List<Episode>> findByNovel(Novel novel);
}
