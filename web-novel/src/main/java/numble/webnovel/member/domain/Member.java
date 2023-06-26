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
    @Column(nullable = false, unique = true)
    private String nickname;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String role;
    @Column(nullable = false)
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

    public void updateNickname(String newNickName){
        this.nickname = newNickName;
    }

    public void chargeCache(int cache){
        this.ownCache += cache;
    }

    public boolean isEnoughOwnCache(int cache, int ticketCnt){
        return this.ownCache >= cache * ticketCnt;
    }

    public void chargeNovelTicket(int paymentCache){
        this.ownCache -= paymentCache;
    }
}
