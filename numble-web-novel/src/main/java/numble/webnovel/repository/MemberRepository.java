package numble.webnovel.repository;

import numble.webnovel.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    @Query("select m from Member m where m.userId = :userId")
    Optional<Member> findByUserId(@Param("userId") String userId);

    @Query("select u from Member u where u.userId = :userId and u.password = :password")
    Optional<Member> findByUserIdPW(@Param("userId") String userId, @Param("password") String password);
}
