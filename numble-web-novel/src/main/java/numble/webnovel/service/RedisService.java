package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final StringRedisTemplate stringRedisTemplate;

    public void setStringValueOneSec(String key, String value){
        stringRedisTemplate.opsForValue().set(key, value, 1, TimeUnit.SECONDS);
    }

    public void setStringValue(String key, String value){
        redisTemplate.opsForValue().set(key, value);
    }

    public String getStringValue(String key){
        return (String)redisTemplate.opsForValue().get(key);
    }

    public void deleteByKey(String key){
        redisTemplate.delete(key);
    }
}
