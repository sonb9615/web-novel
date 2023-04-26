package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.CacheChargeHis;
import numble.webnovel.domain.CacheChargeRequest;
import numble.webnovel.domain.UserInfo;
import numble.webnovel.enums.ExceptionEnum;
import numble.webnovel.exceptions.CommonException;
import numble.webnovel.repository.CacheChargeHisRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CacheChargeService {

    private final UserInfoService userInfoService;
    private final UUIDGeneration uuidGeneration;
    private final CacheChargeHisRepository cacheChargeHisRepository;
    private final ChargeValidationService chargeValidationService;

    @Transactional
    public int cacheCharge(String userNo, int money) throws InterruptedException {
        if(chargeValidationService.isDuplicatedCharge(userNo)){
            throw new CommonException(ExceptionEnum.DUPLICATE_CHARGE_EXCEPTION);
        }
        chargeValidationService.saveCharge(userNo, userNo);
        // 결제 가짜 로직
        Thread.sleep(1000);
        // 캐시 저장
        UserInfo userInfo = userInfoService.findByUserNo(userNo);
        userInfo.chargeCache(money);
        // 히스토리 테이블 저장
        String uuid = uuidGeneration.getUUID();
        CacheChargeHis cacheChargeHis = CacheChargeHis.createCacheChargeHis(uuid, userInfo, money, money);
        this.saveCacheChargeHis(cacheChargeHis);
        return money;
    }

    @Transactional
    public void saveCacheChargeHis(CacheChargeHis cacheChargeHis){
        cacheChargeHisRepository.save(cacheChargeHis);
    }

    @Transactional
    public CacheChargeHis findByPaymentNo(String paymentNo){
        return cacheChargeHisRepository.findById(paymentNo);
    }

    public boolean validRequestParam(CacheChargeRequest cacheChargeRequest){
        return !cacheChargeRequest.getUserNo().isEmpty() && cacheChargeRequest.getMoney() > 0;
    }
}
