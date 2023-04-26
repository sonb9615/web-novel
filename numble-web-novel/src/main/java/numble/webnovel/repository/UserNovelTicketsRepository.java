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

    public UserNovelTickets findById(String ticketNo){
        return em.find(UserNovelTickets.class, ticketNo);
    }

    public List<UserNovelTickets> findAllTicketsByNovelIdUserId(String userNo, String novelId){
        return em.createQuery("select t from UserNovelTickets t" +
                        " join fetch t.novel n" +
                        " join fetch t.userInfo u" +
                        " where u.userNo = :userNo" +
                        " and n.novelId = :novelId" +
                        " order by t.regDt", UserNovelTickets.class)
                .setParameter("userNo", userNo)
                .setParameter("novelId", novelId)
                .getResultList();
    }
}
