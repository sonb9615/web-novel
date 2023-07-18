package numble.webnovel.ranking.controller;

import lombok.RequiredArgsConstructor;
import numble.webnovel.common.CommonResponse;
import numble.webnovel.ranking.dto.RankingForPaymentCnt;
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

    @GetMapping("/payment-cnt")
    public ResponseEntity<CommonResponse<RankingForPaymentCnt>> showRankingForPaymentCnt(){
        RankingForPaymentCnt response = rankingService.showRankingForPaymentCnt();
        return new ResponseEntity<>(new CommonResponse<>("소설 구매수 랭킹 리스트 조회 성공", response), HttpStatus.OK);
    }
}
