package numble.webnovel.repository;


import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.UserInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserInfoRepository{

    private final EntityManager em;
    private final UserInfoInterface userInfoInterface;

    // 회원정보 저장
    public void saveUserInfo(UserInfo userInfo){em.persist(userInfo);}

    // userNo로 회원정보 조회
    public UserInfo findById(String userNo){
        return em.find(UserInfo.class, userNo);
    }

    //동일한 id를 갖는 회원이 있는지
    public List<UserInfo> findByUserId(String userId){
        return em.createQuery("select u from UserInfo u where u.userId = :userId", UserInfo.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public List<UserInfo> findByUserIdPW(String userId, String password){
        return em.createQuery("select u from UserInfo u" +
                        " where u.userId = :userId" +
                        " and u.password = :password", UserInfo.class)
                .setParameter("userId", userId)
                .setParameter("password", password)
                .getResultList();
    }

    public void delete(String userNo){
        userInfoInterface.deleteById(userNo);
    }

}
