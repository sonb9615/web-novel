package numble.webnovel.favoriteNovel.repository;

import numble.webnovel.favoriteNovel.domain.FavoriteNovel;
import numble.webnovel.member.domain.Member;
import numble.webnovel.novel.domain.Novel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FavoriteNovelRepository extends JpaRepository<FavoriteNovel, Long> {

    Optional<FavoriteNovel> findByNovelAndMember(Novel novel, Member member);

    boolean existsByNovelAndMember(Novel novel, Member member);

    @Query("SELECT f FROM FavoriteNovel f JOIN FETCH f.novel n WHERE f.member.memberId = :memberId")
    Optional<List<FavoriteNovel>> findByMemberWithNovel(@Param("memberId") Long memberId);
}
