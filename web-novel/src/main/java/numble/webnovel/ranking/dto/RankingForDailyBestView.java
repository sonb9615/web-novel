package numble.webnovel.ranking.dto;

import lombok.Getter;
import numble.webnovel.novel.domain.Novel;
import numble.webnovel.novel.dto.NovelInfoResponse;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RankingForDailyBestView {

    private List<NovelInfoResponse> novelList;

    public RankingForDailyBestView(List<NovelInfoResponse> novelList) {
        this.novelList = novelList;
    }

    public static RankingForDailyBestView toRankingForDailyBestView(List<Novel> novels){
        List<NovelInfoResponse> responseList = novels.stream()
                .map(NovelInfoResponse::toNovelInfoResponse)
                .collect(Collectors.toList());
        return new RankingForDailyBestView(responseList);
    }

}
