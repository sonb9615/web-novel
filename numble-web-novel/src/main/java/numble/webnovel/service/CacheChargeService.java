package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.CacheChargeHis;
import numble.webnovel.repository.dto.request.CacheChargeRequest;
import numble.webnovel.domain.Member;
import numble.webnovel.enums.ExceptionEnum;
import numble.webnovel.exceptions.CommonException;
import numble.webnovel.repository.CacheChargeHisRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CacheChargeService {

    private final MemberService memberService;
    private final UUIDGeneration uuidGeneration;
    private final ChargeValidationService chargeValidationService;
    private final CacheChargeHisRepository cacheChargeHisRepository;

    @Transactional
    public int cacheCharge(String userNo, int money) throws InterruptedException {
        if(chargeValidationService.isDuplicatedCharge(userNo)){
            throw new CommonException(ExceptionEnum.DUPLICATE_CHARGE_EXCEPTION);
        }
        chargeValidationService.saveCharge(userNo, userNo);
        // 결제 가짜 로직
        Thread.sleep(1000);
        // 캐시 저장
        Member member = memberService.findByUserNo(userNo);
        member.chargeCache(money);
        // 히스토리 테이블 저장
        String uuid = uuidGeneration.getUUID();
        CacheChargeHis cacheChargeHis = CacheChargeHis.createCacheChargeHis(member, money, money);
        this.saveCacheChargeHis(cacheChargeHis);
        return money;
    }

    @Transactional
    public void saveCacheChargeHis(CacheChargeHis cacheChargeHis){
        cacheChargeHisRepository.save(cacheChargeHis);
    }

    @Transactional
    public CacheChargeHis findByPaymentNo(String paymentNo){
        return cacheChargeHisRepository.findById(paymentNo)
                .orElseThrow(() -> new CommonException(ExceptionEnum.RESULT_NOT_EXIST_EXCEPTION));
    }

    public boolean validRequestParam(CacheChargeRequest cacheChargeRequest){
        return !cacheChargeRequest.getUserNo().isEmpty() && cacheChargeRequest.getMoney() > 0;
    }
}
