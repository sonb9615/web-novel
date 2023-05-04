package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.Member;
import numble.webnovel.enums.ExceptionEnum;
import numble.webnovel.exceptions.CommonException;
import numble.webnovel.repository.MemberRepository;
import numble.webnovel.repository.dto.request.LoginRequest;
import numble.webnovel.repository.dto.request.SignUpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member findByMemberId(Long memberId){
         return memberRepository.findById(memberId)
                 .orElseThrow(() -> new CommonException(ExceptionEnum.RESULT_NOT_EXIST_EXCEPTION));
    }

    @Transactional
    public void signUp(SignUpRequest request){
        if(isDuplicatedNickname(request.getNickname())){
            Member member = request.toMember();
            memberRepository.save(member);
        }
    }

    @Transactional
    public boolean login(LoginRequest request){
        return validLogin(request.getNickname(), request.getPassword());
    }

    @Transactional
    public boolean isDuplicatedNickname(String nickname){
        if(memberRepository.findByNickname(nickname).isPresent()){
            throw new CommonException(ExceptionEnum.DUPLICATE_NICKNAME);
        }
        return true;
    }

    @Transactional
    public boolean validLogin(String nickname, String password){
        if(memberRepository.findByNicknamePW(nickname, password).isEmpty()){
            throw new CommonException(ExceptionEnum.INVALID_LOGIN);
        }
        return true;
    }

    @Transactional
    public void deleteByUserNo(Long memberId){
        memberRepository.deleteById(memberId);
    }
}
