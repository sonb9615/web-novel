package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.CacheChargeHis;
import numble.webnovel.domain.Member;
import numble.webnovel.enums.ExceptionEnum;
import numble.webnovel.exceptions.CommonException;
import numble.webnovel.repository.CacheChargeHisRepository;
import numble.webnovel.repository.dto.request.CacheChargeRequest;
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
    public int cacheCharge(Long memberId, int money) throws InterruptedException {
        if(chargeValidationService.isDuplicatedCharge(memberId)){
            throw new CommonException(ExceptionEnum.DUPLICATE_CHARGE_EXCEPTION);
        }
        chargeValidationService.saveCharge(memberId, "CHARGE");
        // 결제 가짜 로직
        Thread.sleep(1000);
        // 캐시 저장
        Member member = memberService.findByMemberId(memberId);
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

    public boolean validRequestParam(CacheChargeRequest cacheChargeRequest){
        return cacheChargeRequest.getMemberId() != null && cacheChargeRequest.getMoney() > 0;
    }
}
