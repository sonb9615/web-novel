package numble.webnovel.repository;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.UserLibrary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserLibraryRepository {

    private final EntityManager em;

    public void save(UserLibrary userLibrary){
        em.persist(userLibrary);
    }

    public List<UserLibrary> findListByUserNo(String userNo){
        return em.createQuery("select lib from UserLibrary lib where lib.userNo = :userNo", UserLibrary.class)
                .setParameter("userNo", userNo)
                .getResultList();
    }
}
