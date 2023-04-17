package numble.webnovel.controller;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.CacheCargeRequest;
import numble.webnovel.domain.ChargeApiResponse;
import numble.webnovel.domain.ChargeInfoRequest;
import numble.webnovel.domain.NovelTicketChargeRequest;
import numble.webnovel.enums.ExceptionEnum;
import numble.webnovel.exceptions.CommonException;
import numble.webnovel.service.CacheChargeService;
import numble.webnovel.service.NovelTicketsChargeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChargeApiController {

    private final NovelTicketsChargeService novelTicketsChargeService;
    private final CacheChargeService cacheChargeService;

    @PostMapping("/charge/tickets")
    public ChargeApiResponse chargeTickets(@RequestBody @Validated NovelTicketChargeRequest novelTicketChargeRequest){
        if(novelTicketsChargeService.validRequestParam(novelTicketChargeRequest)){
            return novelTicketsChargeService.chargeTicket(novelTicketChargeRequest);
        }
        throw new CommonException(ExceptionEnum.PARAM_NOT_EXIST_EXCEPTION);
    }

    @PostMapping("/charge/cache")
    public ChargeApiResponse cacheCharge(@RequestBody @Validated CacheCargeRequest cacheCargeRequest) throws InterruptedException {
        if(cacheChargeService.validRequestParam(cacheCargeRequest)){
            return cacheChargeService.cacheCharge(cacheCargeRequest);
        }
        throw new CommonException(ExceptionEnum.PARAM_NOT_EXIST_EXCEPTION);
    }

    @PostMapping("/charge/validation")
    public boolean validCharge(@RequestBody @Validated ChargeInfoRequest chargeInfoRequest){

        return true;
    }
}
