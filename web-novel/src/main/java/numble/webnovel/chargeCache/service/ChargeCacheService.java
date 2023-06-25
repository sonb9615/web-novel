package numble.webnovel.chargeCache.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.chargeCache.domain.ChargeCacheHis;
import numble.webnovel.chargeCache.dto.ChargeCacheRequest;
import numble.webnovel.chargeCache.repository.ChargeCacheHisRepository;
import numble.webnovel.exception.WebNovelServiceException;
import numble.webnovel.member.domain.Member;
import numble.webnovel.member.repository.MemberRepository;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static numble.webnovel.exception.ErrorCode.NO_AVAILABLE_LOCK;
import static numble.webnovel.exception.ErrorCode.NO_EXISTS_MEMBER;

@Service
@RequiredArgsConstructor
public class ChargeCacheService {

    private final RedissonClient redissonClient;
    private final MemberRepository memberRepository;
    private final ChargeCacheHisRepository chargeCacheHisRepository;
    private static final String CACHE_LOCK_NAME = "CACHE_CHARGE";

    public void chargeCache(Member cuurentMember, ChargeCacheRequest request){
        String lockName = CACHE_LOCK_NAME + ":lock";
        RLock lock = redissonClient.getLock(lockName);

        try {

            if(!lock.tryLock(1,3, TimeUnit.SECONDS)){
                throw new WebNovelServiceException(NO_AVAILABLE_LOCK);
            }

            Member member = memberRepository.findByNickname(cuurentMember.getNickname())
                    .orElseThrow(() -> new WebNovelServiceException(NO_EXISTS_MEMBER));
            member.chargeCache(request.getCache());

            ChargeCacheHis chargeCacheHis = request.toChargeCacheHis(member);
            chargeCacheHisRepository.save(chargeCacheHis);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }
}
