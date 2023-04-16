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

    public boolean isDuplicatedCharge(String userNo) {
        return stringRedisTemplate.opsForValue().get(REDIS_KEY + userNo) != null;
    }

    public void saveCharge(String userNo, String value) {
        redisService.setStringValueOneSec(REDIS_KEY + userNo, value);
    }
}
