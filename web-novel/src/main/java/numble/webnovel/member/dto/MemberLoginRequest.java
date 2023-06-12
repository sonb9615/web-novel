package numble.webnovel.member.dto;

import lombok.Getter;

@Getter
public class MemberLoginRequest {

    private String nickname;
    private String password;

    // test용 메서드
    public void setNickname(String testNickname){
        this.nickname = testNickname;
    }

    public void setPassword(String testPassword){
        this.password = testPassword;
    }
}
