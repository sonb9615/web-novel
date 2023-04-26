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

    public UserLibrary findById(String id){
        return em.find(UserLibrary.class, id);
    }

//    public List<UserLibrary> findListByUserNo(String userNo){
//        return em.createQuery("select lib\n" +
//                        "from UserLibrary lib\n" +
//                        "inner join NovelEpisode epi\n" +
//                        "on epi.episodeId = lib.episodeId\n" +
//                        "inner join Novel nvl\n" +
//                        "on nvl.novelId = epi.novelId\n" +
//                        "where lib.userNo = :userNo", UserLibrary.class)
//                .setParameter("userNo", userNo)
//                .getResultList();
//    }
}
