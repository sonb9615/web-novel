package numble.webnovel.chargeCache.controller;

import lombok.RequiredArgsConstructor;
import numble.webnovel.chargeCache.dto.ChargeCacheRequest;
import numble.webnovel.chargeCache.service.ChargeCacheService;
import numble.webnovel.common.CommonResponse;
import numble.webnovel.util.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/chargeCache")
public class ChargeCacheController {

    private final ChargeCacheService chargeCacheService;

    @PostMapping
    public ResponseEntity<CommonResponse<Void>> cacheCharge(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody ChargeCacheRequest request){
        chargeCacheService.chargeCache(userDetails.getMember(), request);
        return new ResponseEntity<>(new CommonResponse<>("캐쉬 충전 완료", null), HttpStatus.OK);
    }
}
