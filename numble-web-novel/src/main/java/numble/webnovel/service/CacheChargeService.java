package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.enums.CommonExceptionEnum;
import numble.webnovel.exceptions.CommonException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CacheChargeService {

    private final UserInfoService userInfoService;

    @Transactional
    public void cacheCharge(int cache) throws InterruptedException {
        if(cache <= 0) throw new CommonException(CommonExceptionEnum.CHARGE_RANGE_EXCEPTION);

        // 결제 시작..? -> redis validation

        // 결제 가짜 로직
        Thread.sleep(1000);

        // 로그에 먼저?
        

    }

}
