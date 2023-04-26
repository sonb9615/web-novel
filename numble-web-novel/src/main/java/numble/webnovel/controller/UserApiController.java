package numble.webnovel.controller;

import lombok.RequiredArgsConstructor;
import numble.webnovel.repository.dto.request.LoginRequest;
import numble.webnovel.repository.dto.request.SignUpRequest;
import numble.webnovel.repository.dto.response.LoginResponse;
import numble.webnovel.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserApiController {

    // 추후 Spring Security, jwt 관련 공부 진행 후, 적용

    private final UserInfoService userInfoService;

    @PostMapping("/signUp")
    public LoginResponse signUp(@RequestBody @Validated SignUpRequest signUpRequest){
        userInfoService.signUp(signUpRequest);
        return LoginResponse.createLoginResponse("회원가입 되셨습니다.");
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest loginRequest){
        userInfoService.login(loginRequest);
        return LoginResponse.createLoginResponse("로그인 되셨습니다.");
    }
}
