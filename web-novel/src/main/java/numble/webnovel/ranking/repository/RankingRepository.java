package numble.webnovel.ranking.repository;

import lombok.RequiredArgsConstructor;
import numble.webnovel.exception.WebNovelServiceException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static numble.webnovel.exception.ErrorCode.NO_EXISTS_RANKING_INFO;
import static numble.webnovel.ranking.enums.RankingEnum.FAVORITE_NOVEL_RANKING;
import static numble.webnovel.ranking.enums.RankingEnum.NOVEL_PAYMENT_RANKING;

@Service
@RequiredArgsConstructor
public class RankingRepository {

    private final StringRedisTemplate stringRedisTemplate;
    private final RedisTemplate<String, String> redisTemplate;

    public void increasePaymentCntScore(Long novelId, Long paymentCnt){
        redisTemplate.opsForZSet()
                .add(NOVEL_PAYMENT_RANKING.getValue(), String.valueOf(novelId), paymentCnt);
    }

    public void increaseFavoriteNovelCnt(Long novelId){
        redisTemplate.opsForHash()
                .increment(FAVORITE_NOVEL_RANKING.getValue(), String.valueOf(novelId), 1);
    }

    public void decreaseFavoriteNovelCnt(Long novelId){

    }

    public List<Long> findNovelIdByRankingForPaymentCnt(){
        String key = NOVEL_PAYMENT_RANKING.getValue();
        Set<String> typedTuples = redisTemplate.opsForZSet().reverseRangeByScore(key, 0, 10);
        if(typedTuples == null){
            throw new WebNovelServiceException(NO_EXISTS_RANKING_INFO);
        }
        return typedTuples.stream().map(Long::parseLong).toList();
    }
}
