package numble.webnovel.repository;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.UserNovelTickets;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserNovelTicketsRepository {

    private final EntityManager em;

    public void save(UserNovelTickets userNovelTickets){
        em.persist(userNovelTickets);
    }

    public List<UserNovelTickets> findUsableTicketsByNovelIdUserId(String userNo, String novelId){
        return em.createQuery("select t from UserNovelTickets t where t.userNo = :userNo and t.novelId = :novelId and t.usableTicketCnt > 0 order by t.regDt", UserNovelTickets.class)
                .setParameter("userNo", userNo)
                .setParameter("novelId", novelId)
                .getResultList();
    }
}
