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
@Table(name="user_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfo {

    @Id
    @Column(name="user_no")
    private String userNo;

    @Column(name = "user_id")
    private String userId;

    @Column(name="password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "phone")
    private String phone;

    @Column(name = "gender")
    private String gender;

    @Column(name = "email")
    private String email;

    @Column(name = "cache")
    private int cache;

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private List<CacheChargeHis> cacheChargeHisList = new ArrayList<>();

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private List<UserNovelTickets> userNovelTickets = new ArrayList<>();

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private List<UserLibrary> userLibraryList = new ArrayList<>();

    public void setCacheChargeHis(CacheChargeHis his){
        this.cacheChargeHisList.add(his);
        his.setUserInfo(this);
    }

    public void setNovelTicket(UserNovelTickets ticket){
        this.userNovelTickets.add(ticket);
        ticket.setUserInfo(this);
    }

    public void setUserLibrary(UserLibrary library){
        this.userLibraryList.add(library);
        library.setUserInfo(this);
    }

    public static UserInfo createUserInfo(String userNo, String userId, String password, String role
            , String phone, String gender, String email) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserNo(userNo);
        userInfo.setUserId(userId);
        userInfo.setPassword(password);
        userInfo.setRole(role);
        userInfo.setPhone(phone);
        userInfo.setGender(gender);
        userInfo.setEmail(email);
        userInfo.setCache(0);
        return userInfo;
    }

    // 비지니스 로직
    public void chargeCache(int money){
        if(money % 100 != 0) {
            throw new CommonException(ExceptionEnum.CHARGE_RANGE_EXCEPTION);
        }
        this.cache = money;
    }

    public void buyTicket(int episodeCost, int episodeCnt){
        if(this.cache < episodeCost * episodeCnt){
            throw new CommonException(ExceptionEnum.CACHE_SHORTAGE_EXCEPTION);
        }
        this.cache -= episodeCost * episodeCnt;
    }
}
