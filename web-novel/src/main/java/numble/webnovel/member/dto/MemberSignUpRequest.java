package numble.webnovel.member.dto;

import lombok.Getter;
import numble.webnovel.member.domain.Member;

@Getter
public class MemberSignUpRequest {

    private String nickname;
    private String email;
    private String role;
    private String password;

    public Member toMember(String encryptPassword){
        return Member.builder()
                .nickname(nickname)
                .password(encryptPassword)
                .role(role)
                .email(email)
                .ownCache(0)
                .build();
    }

    // 테스트를 위한 메서드 (이외에는 사용하지 않는다.)
    public void setNicknameForTest(String nickname){
        this.nickname = nickname;
    }

    public void setEmailForTest(String email){
        this.email = email;
    }

    public void setRoleForTest(String role){
        this.role = role;
    }

}
