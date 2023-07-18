package numble.webnovel.ranking.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.novel.domain.Novel;
import numble.webnovel.novel.repository.NovelRepository;
import numble.webnovel.ranking.dto.RankingForPaymentCnt;
import numble.webnovel.ranking.repository.RankingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final RankingRepository rankingRepository;
    private final NovelRepository novelRepository;

    public RankingForPaymentCnt showRankingForPaymentCnt(){
        List<Long> novelIds = rankingRepository.findNovelIdByRankingForPaymentCnt();
        List<Novel> novels =  novelRepository.findByNovelIdIn(novelIds);
        return RankingForPaymentCnt.toRankingForPaymentCnt(novels);
    }



}
