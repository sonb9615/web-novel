package numble.webnovel.member.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.exception.ErrorCode;
import numble.webnovel.exception.WebNovelServiceException;
import numble.webnovel.member.domain.Member;
import numble.webnovel.member.dto.MemberLoginRequest;
import numble.webnovel.member.dto.MemberSignUpRequest;
import numble.webnovel.member.repository.MemberRepository;
import numble.webnovel.util.security.jwt.JwtAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

import static numble.webnovel.util.security.jwt.JwtAuthenticationProvider.AUTHORIZATION_TOKEN;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthenticationProvider jwtAuthProvider;

    @Transactional
    public void signUp(MemberSignUpRequest request){
        if(memberRepository.existsByNickname(request.getNickname())){
            throw new WebNovelServiceException(ErrorCode.ALREADY_EXISTS_NICKNAME);
        }
        validateDuplicateMember(request.getEmail());
        String encryptedPassword = passwordEncoder.encode(request.getPassword());
        Member member = request.toMember(encryptedPassword);
        memberRepository.save(member);
    }

    @Transactional
    public void login(MemberLoginRequest request, HttpServletResponse response){
        Member member = memberRepository.findByNickname(request.getNickname()).orElseThrow(
                () -> new WebNovelServiceException(ErrorCode.NO_EXISTS_MEMBER)
        );
        validatePassword(request.getPassword(), member.getPassword());
        getToken(response, member.getNickname());
    }

    public void getToken(HttpServletResponse response, String nickname){
        String accessToken = jwtAuthProvider.createAccessToken(nickname);
        response.addHeader(AUTHORIZATION_TOKEN, accessToken);
    }

    public void validatePassword(String encryptedPassword, String input){
        if(!passwordEncoder.matches(encryptedPassword, input)){
            throw new WebNovelServiceException(ErrorCode.NOT_CORRECT_PASSWORD);
        }
    }

    public void validateDuplicateMember(String email){
        if(memberRepository.existsByEmail(email)){
            throw new WebNovelServiceException(ErrorCode.ALREADY_EXISTS_EMAIL);
        }
    }
}
