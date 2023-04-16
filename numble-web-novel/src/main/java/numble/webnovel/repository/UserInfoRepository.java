package numble.webnovel.repository;


import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.UserInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

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

    public void delete(String userNo){
        userInfoInterface.deleteById(userNo);
    }

}
