package numble.webnovel.ranking.dto;

import lombok.Getter;
import numble.webnovel.novel.domain.Novel;
import numble.webnovel.novel.dto.NovelInfoResponse;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RankingForPaymentCnt {

    private List<NovelInfoResponse> novelList;

    public RankingForPaymentCnt(List<NovelInfoResponse> novelList) {
        this.novelList = novelList;
    }

    public static RankingForPaymentCnt toRankingForPaymentCnt(List<Novel> novels){
        List<NovelInfoResponse> responseList = novels.stream()
                .map(NovelInfoResponse::toNovelInfoResponse)
                .collect(Collectors.toList());
        return new RankingForPaymentCnt(responseList);
    }

}
