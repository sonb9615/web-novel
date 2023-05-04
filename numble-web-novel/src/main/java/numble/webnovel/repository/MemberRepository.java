package numble.webnovel.repository;

import numble.webnovel.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.nickname = :nickname")
    Optional<Member> findByNickname(@Param("nickname") String nickname);

    @Query("select m from Member m where m.nickname = :nickname and m.password = :password")
    Optional<Member> findByNicknamePW(@Param("nickname") String nickname, @Param("password") String password);
}
