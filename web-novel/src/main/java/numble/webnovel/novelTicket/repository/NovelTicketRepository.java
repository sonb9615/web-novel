package numble.webnovel.novelTicket.repository;

import numble.webnovel.member.domain.Member;
import numble.webnovel.novelTicket.domain.NovelTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface NovelTicketRepository extends JpaRepository<NovelTicket, Long> {

    @Query("SELECT nt FROM NovelTicket nt WHERE nt.member.memberId = :memberId AND nt.novelId = :novelId")
    Optional<NovelTicket> findNovelTicketByMemberIdAndNovelId(@Param("memberId") Long memberId, @Param("novelId") Long novelId);

    boolean existsByMemberAndNovelId(Member member, Long novelId);
}