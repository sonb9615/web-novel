package numble.webnovel.repository;

import numble.webnovel.domain.NovelTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NovelTicketRepository extends JpaRepository<NovelTicket, String> {

    @Query("select t from NovelTicket t join fetch t.novel n join fetch t.userInfo u" +
            " where u.userNo = :userNo and n.novelId = :novelId order by t.regDt")
    List<NovelTicket> findAllTicketsByNovelIdUserId(@Param("userNo") String userNo, @Param("novelId") String novelId);
}
