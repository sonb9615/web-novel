package numble.webnovel.member.controller;

import lombok.RequiredArgsConstructor;
import numble.webnovel.common.CommonResponse;
import numble.webnovel.member.dto.MemberLoginRequest;
import numble.webnovel.member.dto.MemberSignUpRequest;
import numble.webnovel.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/auth/signUp")
    public ResponseEntity<CommonResponse<Void>> signUp(@RequestBody MemberSignUpRequest request){
        memberService.signUp(request);
        return new ResponseEntity<>(new CommonResponse<>("회원가입 성공", null), HttpStatus.CREATED);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<CommonResponse<Void>> login(@RequestBody MemberLoginRequest request, HttpServletResponse response){
        memberService.login(request, response);
        return new ResponseEntity<>(new CommonResponse<>("로그인 성공", null), HttpStatus.OK);
    }



}
