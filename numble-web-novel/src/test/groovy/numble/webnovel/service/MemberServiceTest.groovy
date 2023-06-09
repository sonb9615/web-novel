package numble.webnovel.service

import numble.webnovel.domain.Member
import numble.webnovel.exceptions.CommonException
import numble.webnovel.repository.MemberRepository
import numble.webnovel.repository.dto.request.LoginRequest
import numble.webnovel.repository.dto.request.SignUpRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

@SpringBootTest
@Rollback
@Transactional
class MemberServiceTest extends Specification{

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    def "회원찾기"(){
        given:
        String userId = "testId_1"
        when:
//        List<UserInfo> user = userInfoInterface.findByUserId(userId);
        Member info = memberService.findByMemberId("269235c0ac264698951ac2058304aa2a");
        then:
        info.getUserId() == userId;
        println info.getPassword();
    }

    def "회원가입"(){
        given:
        SignUpRequest signUpRequest = SignUpRequest.createSignUpRequest("user_id", "user_pw");

        when:
        memberService.signUp(signUpRequest);

        then:
        Member userInfo = memberRepository.findByNickname("user_id").get();
        println userInfo.getMemberId();
        userInfo.getPassword() == "user_pw";
    }

    def "중복된 아이디로 회원가입 진행하면 오류 발생"(){
        given:
        SignUpRequest signUpRequest = SignUpRequest.createSignUpRequest("user_id", "user_pw");
        memberService.signUp(signUpRequest);

        when:
        memberService.signUp(signUpRequest);

        then:
        def e = thrown(CommonException.class)
        println(e);
    }

    def "로그인"(){
        given:
        SignUpRequest signUpRequest = SignUpRequest.createSignUpRequest("user_id", "user_pw");
        memberService.signUp(signUpRequest);

        when:
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserId("user_id");
        loginRequest.setPassword("user_pw");

        then:
        memberService.login(loginRequest);
    }

    def "올바르지 않은 id pw들어오면 로그인 오류"(){
        given:
        SignUpRequest signUpRequest = SignUpRequest.createSignUpRequest("user_id", "user_pw");
        memberService.signUp(signUpRequest);

        when:
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserId("userid");
        loginRequest.setPassword("user_pw");
        memberService.login(loginRequest);

        then:
        def e = thrown(CommonException.class)
        println(e);
    }

    def"유저 no로 찾기"(){
        given:
        String userNo = "269235c0ac264698951ac2058304aa2a";
        when:
        Member userInfo = memberService.findByMemberId(userNo);
        then:
        userInfo.getGender() == "female";
    }

    def "유저 아이디 비밀번호 유효성 검사"(){
        given:
        SignUpRequest signUpRequest = SignUpRequest.createSignUpRequest("user_id", "user_pw");
        memberService.signUp(signUpRequest);
        when:
        Optional<Member> info =  memberRepository.findByNickname("user_id");
        then:
        info.get().getPassword() == "user_pw";
    }
}
