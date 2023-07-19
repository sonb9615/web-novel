package numble.webnovel.ranking.dto;

import lombok.Getter;
import numble.webnovel.novel.domain.Novel;
import numble.webnovel.novel.dto.NovelInfoResponse;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RankingForFavoriteCnt {

    private List<NovelInfoResponse> novelList;

    public RankingForFavoriteCnt(List<NovelInfoResponse> novelList) {
        this.novelList = novelList;
    }

    public static RankingForFavoriteCnt toRankingForFavoriteCnt(List<Novel> novels){
        List<NovelInfoResponse> responseList = novels.stream()
                .map(NovelInfoResponse::toNovelInfoResponse)
                .collect(Collectors.toList());
        return new RankingForFavoriteCnt(responseList);
    }
}
