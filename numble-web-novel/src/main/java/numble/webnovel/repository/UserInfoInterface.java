package numble.webnovel.repository;

import numble.webnovel.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoInterface extends JpaRepository<UserInfo, String> {
}
