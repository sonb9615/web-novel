package numble.webnovel.library.repository;

import numble.webnovel.library.domain.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LibraryRepository extends JpaRepository<Library, Long> {

    @Query("SELECT l FROM Library l WHERE l.episode.episodeId = :episodeId AND l.member.memberId = :memberId")
    Optional<Library> findByEpisodeIdAndMemberId(@Param("episodeId") Long episodeId, @Param("memberId") Long memberId);

    boolean existsByMemberIdAndEpisodeId(Long memberId, Long episodeId);
}
