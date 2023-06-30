package numble.webnovel.library.repository;

import numble.webnovel.episode.domain.Episode;
import numble.webnovel.library.domain.Library;
import numble.webnovel.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LibraryRepository extends JpaRepository<Library, Long> {

    @Query("SELECT l FROM Library l JOIN fetch l.episode e JOIN FETCH e.novel n WHERE l.episode.episodeId = :episodeId AND l.member.memberId = :memberId")
    Optional<Library> findByEpisodeIdAndMemberIdWithEpisodeAndNovel(@Param("episodeId") Long episodeId, @Param("memberId") Long memberId);

    boolean existsByMemberAndEpisode(Member member, Episode episode);
}
