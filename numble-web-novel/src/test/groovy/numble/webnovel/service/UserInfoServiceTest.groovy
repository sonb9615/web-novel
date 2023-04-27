package numble.webnovel.service

import numble.webnovel.domain.UserInfo
import numble.webnovel.exceptions.CommonException
import numble.webnovel.repository.UserInfoRepository
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
class UserInfoServiceTest extends Specification{

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UserInfoRepository userInfoInterface;

    def "회원찾기"(){
        given:
        String userId = "testId_1"
        when:
//        List<UserInfo> user = userInfoInterface.findByUserId(userId);
        UserInfo info = userInfoService.findByUserNo("269235c0ac264698951ac2058304aa2a");
        then:
        info.getUserId() == userId;
        println info.getPassword();
    }

    def "회원가입"(){
        given:
        SignUpRequest signUpRequest = SignUpRequest.createSignUpRequest("user_id", "user_pw");

        when:
        userInfoService.signUp(signUpRequest);

        then:
        UserInfo userInfo = userInfoRepository.findByUserId("user_id").get(0);
        userInfo.getPassword() == "user_pw";
    }

    def "중복된 아이디로 회원가입 진행하면 오류 발생"(){
        given:
        SignUpRequest signUpRequest = SignUpRequest.createSignUpRequest("user_id", "user_pw");
        userInfoService.signUp(signUpRequest);

        when:
        userInfoService.signUp(signUpRequest);

        then:
        def e = thrown(CommonException.class)
        println(e);
    }

    def "로그인"(){
        given:
        SignUpRequest signUpRequest = SignUpRequest.createSignUpRequest("user_id", "user_pw");
        userInfoService.signUp(signUpRequest);

        when:
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserId("user_id");
        loginRequest.setPassword("user_pw");

        then:
        userInfoService.login(loginRequest);
    }

    def "올바르지 않은 id pw들어오면 로그인 오류"(){
        given:
        SignUpRequest signUpRequest = SignUpRequest.createSignUpRequest("user_id", "user_pw");
        userInfoService.signUp(signUpRequest);

        when:
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserId("userid");
        loginRequest.setPassword("user_pw");
        userInfoService.login(loginRequest);

        then:
        def e = thrown(CommonException.class)
        println(e);
    }
}
