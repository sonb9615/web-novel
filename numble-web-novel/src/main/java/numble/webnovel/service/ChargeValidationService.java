package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ChargeValidationService {

    private final StringRedisTemplate stringRedisTemplate;
    private static final String REDIS_KEY = "CHARGE_";
    private final RedisService redisService;

    public boolean isDuplicatedCharge(Long memberId) {
        return stringRedisTemplate.opsForValue().get(REDIS_KEY + memberId) != null;
    }

    public void saveCharge(Long memberId, String value) {
        redisService.setStringValueOneSec(REDIS_KEY + memberId, value);
    }
}
