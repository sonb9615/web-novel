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

    private final UUIDGeneration uuidGeneration;
    private final MemberRepository memberRepository;

    @Transactional
    public Member findByUserNo(String userNo){
         return memberRepository.findById(userNo)
                 .orElseThrow(() -> new CommonException(ExceptionEnum.RESULT_NOT_EXIST_EXCEPTION));
    }

    @Transactional
    public void signUp(SignUpRequest request){
        if(isDuplicatedUserId(request.getNickname())){
            Member member = Member.createMember(request.getNickname(), request.getPassword(), request.getRole(), request.getEmail());
            memberRepository.save(member);
        }
    }

    @Transactional
    public boolean login(LoginRequest request){
        return validLogin(request.getUserId(), request.getPassword());
    }

    @Transactional
    public boolean isDuplicatedUserId(String userId){
        if(memberRepository.findByUserId(userId).isPresent()){
            throw new CommonException(ExceptionEnum.DUPLICATE_USER_ID);
        }
        return true;
    }

    @Transactional
    public boolean validLogin(String userId, String password){
        if(memberRepository.findByUserIdPW(userId, password).isEmpty()){
            throw new CommonException(ExceptionEnum.INVALID_LOGIN);
        }
        return true;
    }

    @Transactional
    public void deleteByUserNo(String userNo){
        memberRepository.deleteById(userNo);
    }
}
