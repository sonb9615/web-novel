package numble.webnovel.repository.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
public class SignUpRequest {

    private String userId;
    private String password;
    private String role;
    private String phone;
    private String gender;
    private String email;


    public static SignUpRequest createSignUpRequest(String userId, String password){
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUserId(userId);
        signUpRequest.setPassword(password);
        return signUpRequest;
    }
}
