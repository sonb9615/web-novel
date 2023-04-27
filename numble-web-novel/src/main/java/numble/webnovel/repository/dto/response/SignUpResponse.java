package numble.webnovel.repository.dto.response;

import lombok.Data;

@Data
public class SignUpResponse {

    private String result;

    public static SignUpResponse createSignUpResponse(String result) {
        SignUpResponse signUpResponse = new SignUpResponse();
        signUpResponse.setResult(result);
        return signUpResponse;
    }
}
