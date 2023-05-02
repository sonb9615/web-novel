package numble.webnovel.controller;

import lombok.RequiredArgsConstructor;
import numble.webnovel.repository.dto.request.LoginRequest;
import numble.webnovel.repository.dto.request.SignUpRequest;
import numble.webnovel.repository.dto.response.LoginResponse;
import numble.webnovel.repository.dto.response.SignUpResponse;
import numble.webnovel.service.MemberService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserApiController {

    // 추후 Spring Security, jwt 관련 공부 진행 후, 적용

    private final MemberService memberService;

    @PostMapping("/signUp")
    public SignUpResponse signUp(@RequestBody @Validated SignUpRequest signUpRequest){
        memberService.signUp(signUpRequest);
        return SignUpResponse.createSignUpResponse("회원가입 성공");
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest loginRequest){
        memberService.login(loginRequest);
        return LoginResponse.createLoginResponse("로그인 성공");
    }
}
