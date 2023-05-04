package numble.webnovel.repository.dto.request;

import lombok.Builder;
import lombok.Data;
import numble.webnovel.domain.Member;

@Data
public class SignUpRequest {

    private String nickname;
    private String password;
    private String role;
    private String email;


    public static SignUpRequest createSignUpRequest(String nickname, String password){
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setNickname(nickname);
        signUpRequest.setPassword(password);
        return signUpRequest;
    }

    public Member toMember(){
        return Member.builder()
                .nickname(this.nickname)
                .password(this.password)
                .role(this.role)
                .email(this.email)
                .build();
    }
}
