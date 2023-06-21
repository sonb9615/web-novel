package numble.webnovel.member.service

import numble.webnovel.exception.WebNovelServiceException
import numble.webnovel.member.domain.Member
import numble.webnovel.member.dto.MemberLoginRequest
import numble.webnovel.member.dto.MemberSignUpRequest
import numble.webnovel.member.repository.MemberRepository
import numble.webnovel.util.security.jwt.JwtAuthenticationProvider
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification

import javax.servlet.http.HttpServletResponse

class MemberServiceTest extends Specification{

    def memberRepository
    def passwordEncoder
    def jwtAuthProvider
    def memberService

    def setup(){
        memberRepository = Mock(MemberRepository.class)
        passwordEncoder = Mock(PasswordEncoder.class)
        jwtAuthProvider = Mock(JwtAuthenticationProvider.class)
        memberService = new MemberService(memberRepository, passwordEncoder, jwtAuthProvider)
    }

    def "회원가입 정상 케이스"(){
        given:
        def nickname = "testNickname"
        def password = "encryptedPassword"
        def role = "testRole"
        def email = "test@email.com"

        MemberSignUpRequest request = new MemberSignUpRequest()
        request.setNicknameForTest(nickname)
        request.setEmailForTest(email)
        request.setRoleForTest(role)
        Member member = request.toMember(password)

        passwordEncoder.encode(request.getPassword()) >> "encryptedPassword"
        memberRepository.existsByNickname(nickname) >> false
        memberRepository.existsByEmail(email) >> false
        memberRepository.save(member) >> member

        when:
        memberService.signUp(request)

        then:
        1*memberRepository.save(_ as Member)
    }

    def "회원가입 시 중복된 nickname 인 경우 에러 발생"(){
        given:
        def nickname = "testNickname"
        def password = "encryptedPassword"
        def role = "testRole"
        def email = "test@email.com"

        MemberSignUpRequest request = new MemberSignUpRequest()
        request.setNicknameForTest(nickname)
        request.setEmailForTest(email)
        request.setRoleForTest(role)
        Member member = request.toMember(password)

        passwordEncoder.encode(request.getPassword()) >> "encryptedPassword"
        memberRepository.existsByNickname(nickname) >> true
        memberRepository.existsByEmail(email) >> false
        memberRepository.save(member) >> member

        when:
        memberService.signUp(request)

        then:
        thrown(WebNovelServiceException)
    }

    def "회원가입 시 중복된 email(사용자) 인 경우 에러 발생"(){
        given:
        def nickname = "testNickname"
        def password = "encryptedPassword"
        def role = "testRole"
        def email = "test@email.com"

        MemberSignUpRequest request = new MemberSignUpRequest()
        request.setNicknameForTest(nickname)
        request.setEmailForTest(email)
        request.setRoleForTest(role)
        Member member = request.toMember(password)

        passwordEncoder.encode(request.getPassword()) >> "encryptedPassword"
        memberRepository.existsByNickname(nickname) >> false
        memberRepository.existsByEmail(email) >> true
        memberRepository.save(member) >> member

        when:
        memberService.signUp(request)

        then:
        thrown(WebNovelServiceException)
    }

    def "로그인 정상 케이스"(){
        given:
        def nickname = "testNickname"
        def password = "testPassword"

        Member member = Member.builder()
                        .nickname(nickname)
                        .password(password)
                        .build()

        def request = new MemberLoginRequest()
        request.setNickname(nickname)
        request.setPassword(password)

        def response = Mock(HttpServletResponse.class)

        memberRepository.findByNickname(nickname) >> Optional.of(member)
        passwordEncoder.matches(request.getPassword(), member.getPassword()) >> true

        when:
        memberService.login(request, response)

        then:
        1*response.addHeader("AUTH_TOKEN",_)
    }

    def "로그인 시 올바르지 않은 nickname 인 경우 에러 발생"(){
        given:
        def nickname = "testNickname"
        def password = "testPassword"

        Member member = Member.builder()
                .nickname(nickname)
                .password(password)
                .build()

        def request = new MemberLoginRequest()
        request.setNickname(nickname)
        request.setPassword(password)

        def response = Mock(HttpServletResponse.class)

        memberRepository.findByNickname(nickname) >> null
        passwordEncoder.matches(request.getPassword(), member.getPassword()) >> true

        when:
        memberService.login(request, response)

        then:
        thrown(NullPointerException)
    }

    def "로그인 시 올바르지 않은 password 인 경우 에러 발생"(){
        given:
        def nickname = "testNickname"
        def password = "testPassword"

        Member member = Member.builder()
                .nickname(nickname)
                .password(password)
                .build()

        def request = new MemberLoginRequest()
        request.setNickname(nickname)
        request.setPassword(password)

        def response = Mock(HttpServletResponse.class)

        memberRepository.findByNickname(nickname) >> Optional.of(member)
        passwordEncoder.matches(request.getPassword(), member.getPassword()) >> false

        when:
        memberService.login(request, response)

        then:
        thrown(WebNovelServiceException)
    }
}
