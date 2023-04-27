package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.UserInfo;
import numble.webnovel.enums.ExceptionEnum;
import numble.webnovel.exceptions.CommonException;
import numble.webnovel.repository.UserInfoRepository;
import numble.webnovel.repository.dto.request.LoginRequest;
import numble.webnovel.repository.dto.request.SignUpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserInfoService {

    private final UUIDGeneration uuidGeneration;
    private final UserInfoRepository userInfoRepository;

    @Transactional
    public UserInfo findByUserNo(String userNo){
         return userInfoRepository.findById(userNo)
                 .orElseThrow(() -> new CommonException(ExceptionEnum.RESULT_NOT_EXIST_EXCEPTION));
    }

    @Transactional
    public void signUp(SignUpRequest request){
        if(isDuplicatedUserId(request.getUserId())){
            UserInfo userInfo = UserInfo.createUserInfo(uuidGeneration.getUUID(), request.getUserId(), request.getPassword(), request.getRole()
                , request.getPhone(), request.getGender(), request.getEmail());
            userInfoRepository.save(userInfo);
        }
    }

    @Transactional
    public boolean login(LoginRequest request){
        return validLogin(request.getUserId(), request.getPassword());
    }

    @Transactional
    public boolean isDuplicatedUserId(String userId){
        if(userInfoRepository.findByUserId(userId).isPresent()){
            throw new CommonException(ExceptionEnum.DUPLICATE_USER_ID);
        }
        return true;
    }

    @Transactional
    public boolean validLogin(String userId, String password){
        if(userInfoRepository.findByUserIdPW(userId, password).isEmpty()){
            throw new CommonException(ExceptionEnum.INVALID_LOGIN);
        }
        return true;
    }

    @Transactional
    public void deleteByUserNo(String userNo){
        userInfoRepository.deleteById(userNo);
    }
}
