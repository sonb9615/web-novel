package numble.webnovel.repository;

import numble.webnovel.domain.NovelTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NovelTicketRepository extends JpaRepository<NovelTicket, Long> {

    @Query("select t from NovelTicket t join fetch t.novel n join fetch t.member m" +
            " where m.memberId = :memberId and n.novelId = :novelId order by t.regDt")
    List<NovelTicket> findAllTicketsByNovelIdMemberId(@Param("memberId") Long memberId, @Param("novelId") Long novelId);
}
