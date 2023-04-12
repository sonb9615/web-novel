package numble.webnovel.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="user_info")
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
    private long cache;

    public static UserInfo userInfo(String userNo, String password, String role, String phone, String gender, String email, long cache, String userId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserNo(userNo);
        userInfo.setUserId(userId);
        userInfo.setPassword(password);
        userInfo.setRole(role);
        userInfo.setPhone(phone);
        userInfo.setGender(gender);
        userInfo.setEmail(email);
        userInfo.setCache(cache);
        return userInfo;
    }
}
