package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.UserInfo;
import numble.webnovel.enums.CommonExceptionEnum;
import numble.webnovel.exceptions.CommonException;
import numble.webnovel.repository.UserInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;

    @Transactional
    public UserInfo findByUserNo(String userNo){
        UserInfo userInfo = userInfoRepository.findById(userNo);
        if(userInfo == null){
            throw new CommonException(CommonExceptionEnum.RESULT_NOT_EXIST_EXCEPTION);
        }
        return userInfo;
    }
}
