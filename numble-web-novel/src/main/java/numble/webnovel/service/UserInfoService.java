package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.UserInfo;
import numble.webnovel.enums.ExceptionEnum;
import numble.webnovel.exceptions.CommonException;
import numble.webnovel.repository.UserInfoRepository;
import numble.webnovel.repository.dto.request.LoginRequest;
import numble.webnovel.repository.dto.request.SignUpRequest;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final UUIDGeneration uuidGeneration;

    @Transactional
    public void saveUserInfo(UserInfo userInfo){
        userInfoRepository.saveUserInfo(userInfo);
    }

    @Transactional
    public UserInfo findByUserNo(String userNo){
        UserInfo userInfo = userInfoRepository.findById(userNo);
        if(userInfo == null){
            throw new CommonException(ExceptionEnum.RESULT_NOT_EXIST_EXCEPTION);
        }
        return userInfo;
    }

    @Transactional
    public void signUp(SignUpRequest request){
        if(isDuplicatedUserId(request.getUserId())){
            UserInfo userInfo = UserInfo.createUserInfo(uuidGeneration.getUUID(), request.getUserId(), request.getPassword(), request.getRole()
                , request.getPhone(), request.getGender(), request.getEmail());
            userInfoRepository.saveUserInfo(userInfo);
        }
    }

    @Transactional
    public boolean login(LoginRequest request){
        return validLogin(request.getUserId(), request.getPassword());
    }

    @Transactional
    public boolean isDuplicatedUserId(String userId){
        List<UserInfo> userInfoList = userInfoRepository.findByUserId(userId);
        if(userInfoList.size() > 0){
            throw new CommonException(ExceptionEnum.DUPLICATE_USER_ID);
        }
        return true;
    }

    @Transactional
    public boolean validLogin(String userId, String password){
        List<UserInfo> userInfoList = userInfoRepository.findByUserIdPW(userId, password);
        if(userInfoList.size() != 1){
            throw new CommonException(ExceptionEnum.INVALID_LOGIN);
        }
        return true;
    }


    @Transactional
    public void deleteByUserNo(String userNo){
        userInfoRepository.delete(userNo);
    }
}
