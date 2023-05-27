package numble.webnovel.member.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String nickname;
    private String password;
    private String role;
    private String email;
    private int ownCache;

    public static Member createMember(String nickname, String password, String role, String email){
        Member member = new Member();
        member.setNickname(nickname);
        member.setPassword(password);
        member.setRole(role);
        member.setEmail(email);
        member.setOwnCache(0);
        return member;
    }

    @Builder
    public Member(Long memberId, String nickname, String password, String role, String email, int ownCache) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.password = password;
        this.role = role;
        this.email = email;
        this.ownCache = ownCache;
    }


}
