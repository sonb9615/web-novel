package numble.webnovel.ranking.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.novel.domain.Novel;
import numble.webnovel.novel.repository.NovelRepository;
import numble.webnovel.ranking.dto.RankingForFavoriteCnt;
import numble.webnovel.ranking.dto.RankingForDailyBestView;
import numble.webnovel.ranking.repository.RankingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final RankingRepository rankingRepository;
    private final NovelRepository novelRepository;

    public RankingForDailyBestView showRankingForDailyBestView(){
        List<Long> novelIds = rankingRepository.findNovelIdByRankingForDailyBestView();
        List<Novel> novels =  novelRepository.findByNovelIdIn(novelIds);
        return RankingForDailyBestView.toRankingForDailyBestView(novels);
    }

    public RankingForFavoriteCnt showRankingForFavoriteCnt() {
        List<Long> novelIds = rankingRepository.findNovelIdByRankingForFavoriteCnt();
        List<Novel> novels = novelRepository.findByNovelIdIn(novelIds);
        return RankingForFavoriteCnt.toRankingForFavoriteCnt(novels);
    }

}
