package numble.webnovel.ranking.controller;

import lombok.RequiredArgsConstructor;
import numble.webnovel.common.CommonResponse;
import numble.webnovel.ranking.dto.RankingForFavoriteCnt;
import numble.webnovel.ranking.dto.RankingForDailyBestView;
import numble.webnovel.ranking.service.RankingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/ranking")
public class RankingController {

    private final RankingService rankingService;

    @GetMapping("/daily-best-view")
    public ResponseEntity<CommonResponse<RankingForDailyBestView>> showRankingForDailyBestView(){
        RankingForDailyBestView response = rankingService.showRankingForDailyBestView();
        return new ResponseEntity<>(new CommonResponse<>("일일 베스트 조회 수 리스트 조회 성공", response), HttpStatus.OK);
    }

    @GetMapping("/favorite-cnt")
    public ResponseEntity<CommonResponse<RankingForFavoriteCnt>> showRankingFavoriteCnt(){
        RankingForFavoriteCnt response = rankingService.showRankingForFavoriteCnt();
        return new ResponseEntity<>(new CommonResponse<>("선호작 랭킹 리스트 조회 성공", response), HttpStatus.OK);
    }
}
