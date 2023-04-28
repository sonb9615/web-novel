package numble.webnovel.repository;

import numble.webnovel.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    @Query("select u from UserInfo u where u.userId = :userId")
    Optional<UserInfo> findByUserId(@Param("userId") String userId);

    @Query("select u from UserInfo u where u.userId = :userId and u.password = :password")
    Optional<UserInfo> findByUserIdPW(@Param("userId") String userId, @Param("password") String password);
}
