package numble.webnovel.member.repository;

import numble.webnovel.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByNickname(String nickName);

    boolean existsByNickname(String nickname);

    boolean existsByEmail(String email);

}
