package numble.webnovel.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import numble.webnovel.enums.ExceptionEnum;
import numble.webnovel.exceptions.CommonException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String nickname;
    private String password;
    private String role;
    private String email;
    private int ownCache;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<CacheChargeHis> cacheChargeHisList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<NovelTicket> novelTickets = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Library> libraryList = new ArrayList<>();

    public void setCacheChargeHis(CacheChargeHis his){
        this.cacheChargeHisList.add(his);
        his.setMember(this);
    }

    public void setNovelTicket(NovelTicket ticket){
        this.novelTickets.add(ticket);
        ticket.setMember(this);
    }

    public void setLibrary(Library library){
        this.libraryList.add(library);
        library.setMember(this);
    }

    public static Member createMember(String nickname, String password, String role, String email) {
        Member member = new Member();
        member.setNickname(nickname);
        member.setPassword(password);
        member.setRole(role);
        member.setEmail(email);
        member.setOwnCache(0);
        return member;
    }

    // 비지니스 로직
    public void chargeCache(int money){
        if(money % 100 != 0) {
            throw new CommonException(ExceptionEnum.CHARGE_RANGE_EXCEPTION);
        }
        this.ownCache = money;
    }

    public void buyTicket(int episodeCost, int episodeCnt){
        if(this.ownCache < episodeCost * episodeCnt){
            throw new CommonException(ExceptionEnum.CACHE_SHORTAGE_EXCEPTION);
        }
        this.ownCache -= episodeCost * episodeCnt;
    }
}
