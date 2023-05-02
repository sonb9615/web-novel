package numble.webnovel.repository.dto.request;

import lombok.Builder;
import lombok.Data;

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
}
