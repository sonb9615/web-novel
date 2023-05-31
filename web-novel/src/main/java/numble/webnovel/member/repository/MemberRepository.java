package numble.webnovel.member.repository;

import numble.webnovel.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.nickname = :nickname")
    Optional<Member> findByNickName(@Param("nickName") String nickName);
}
