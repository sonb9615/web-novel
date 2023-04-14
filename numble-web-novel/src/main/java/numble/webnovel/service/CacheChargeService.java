package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.CacheCargeInfo;
import numble.webnovel.domain.CacheChargeHis;
import numble.webnovel.domain.UserInfo;
import numble.webnovel.enums.CommonExceptionEnum;
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

    @Transactional
    public void cacheCharge(CacheCargeInfo cacheCargeInfo) throws InterruptedException {
        String userNo = cacheCargeInfo.getUser_no();
        int money = cacheCargeInfo.getMoney();

        if(money <= 0) throw new CommonException(CommonExceptionEnum.CHARGE_RANGE_EXCEPTION);

        // 결제 시작..? -> redis validation

        // 결제 가짜 로직
        Thread.sleep(1000);

        // 유저정보에 저장
        int cache = userInfoService.sumUserCache(userNo, money);
        // 히스토리 테이블 저장
        String uuid = uuidGeneration.getUUID();
        CacheChargeHis cacheChargeHis = CacheChargeHis.cacheChargeHis(uuid, userNo, money, cache);
        this.saveCacheChargeHis(cacheChargeHis);
        if(!cacheCargeInfo.getNovel_id().isEmpty()){

        }
    }

    @Transactional
    public void saveCacheChargeHis(CacheChargeHis cacheChargeHis){
        cacheChargeHisRepository.save(cacheChargeHis);
    }

}
