package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.CacheCargeRequest;
import numble.webnovel.domain.CacheChargeHis;
import numble.webnovel.domain.ChargeApiResponse;
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
    private final NovelEpisodeService novelEpisodeService;
    private final ChargeValidationService chargeValidationService;

    @Transactional
    public ChargeApiResponse cacheCharge(CacheCargeRequest cacheCargeRequest) throws InterruptedException {
        String userNo = cacheCargeRequest.getUserNo();
        int money = cacheCargeRequest.getMoney();
        if(chargeValidationService.isDuplicatedCharge(userNo)){
            throw new CommonException(ExceptionEnum.DUPLICATE_CHARGE_EXCEPTION);
        }
        chargeValidationService.saveCharge(userNo, userNo);
        if(money <= 0) {
            throw new CommonException(ExceptionEnum.CHARGE_RANGE_EXCEPTION);
        }
        // 결제 가짜 로직
        Thread.sleep(1000);
        // 유저정보에 저장
        int cache = userInfoService.sumUserCache(userNo, money);
        // 히스토리 테이블 저장
        String uuid = uuidGeneration.getUUID();
        CacheChargeHis cacheChargeHis = CacheChargeHis.cacheChargeHis(uuid, userNo, money, cache);
        this.saveCacheChargeHis(cacheChargeHis);
        ChargeApiResponse chargeApiResponse = new ChargeApiResponse();
        chargeApiResponse.setResult("SUCESS");
        return chargeApiResponse;
    }

    @Transactional
    public void saveCacheChargeHis(CacheChargeHis cacheChargeHis){
        cacheChargeHisRepository.save(cacheChargeHis);
    }

    @Transactional
    public CacheChargeHis findByPaymentNo(String paymentNo){
        return cacheChargeHisRepository.findById(paymentNo);
    }

    public boolean validRequestParam(CacheCargeRequest cacheCargeRequest){
        return !cacheCargeRequest.getUserNo().isEmpty() && cacheCargeRequest.getMoney() > 0;
    }
}
