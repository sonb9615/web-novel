package numble.webnovel.repository.dto.request;

import lombok.Data;

@Data
public class LoginRequest {

    private String userId;
    private String password;
}
