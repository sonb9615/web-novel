package numble.webnovel.controller;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.CacheChargeRequest;
import numble.webnovel.domain.NovelTicketChargeRequest;
import numble.webnovel.enums.ExceptionEnum;
import numble.webnovel.exceptions.CommonException;
import numble.webnovel.repository.dto.response.ChargeCacheResponse;
import numble.webnovel.repository.dto.response.ChargeTicketsResponse;
import numble.webnovel.service.CacheChargeService;
import numble.webnovel.service.NovelTicketsChargeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ChargeApiController {

    private final NovelTicketsChargeService novelTicketsChargeService;
    private final CacheChargeService cacheChargeService;

    @PostMapping("/charge/cache")
    public ChargeCacheResponse chargeCache(@RequestBody @Validated CacheChargeRequest request) throws InterruptedException {
        if(cacheChargeService.validRequestParam(request)){
            return ChargeCacheResponse.createChargeCacheResponse("SUCCESS"
                    ,cacheChargeService.cacheCharge(request.getUserNo(), request.getMoney()));
        }
        throw new CommonException(ExceptionEnum.PARAM_NOT_EXIST_EXCEPTION);
    }

    @PostMapping("/charge/tickets")
    public ChargeTicketsResponse chargeTickets(@RequestBody @Validated NovelTicketChargeRequest request){
        if(novelTicketsChargeService.validRequestParam(request)){
            return ChargeTicketsResponse.createChargeTicketsResponse("SUCCESS"
                    , novelTicketsChargeService.chargeTicket(request.getUserNo(), request.getNovelId(), request.getChargeTicketsCnt()));
        }
        throw new CommonException(ExceptionEnum.PARAM_NOT_EXIST_EXCEPTION);
    }

}
