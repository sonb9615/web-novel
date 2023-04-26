package numble.webnovel.repository.dto.response;

import lombok.Data;

@Data
public class LoginResponse {

    private String result;

    public static LoginResponse createLoginResponse(String result) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setResult(result);
        return loginResponse;
    }
}
