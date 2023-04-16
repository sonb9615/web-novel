package numble.webnovel.controller;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.CacheCargeInfo;
import numble.webnovel.domain.ChargeApiResponse;
import numble.webnovel.domain.ChargeInfoRequest;
import numble.webnovel.domain.NovelTicketChargeInfo;
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
    public ChargeApiResponse chargeTickets(@RequestBody @Validated NovelTicketChargeInfo novelTicketChargeInfo){
        return novelTicketsChargeService.chargeTicket(novelTicketChargeInfo);
    }

    @PostMapping("/charge/cache")
    public ChargeApiResponse cacheCharge(@RequestBody @Validated CacheCargeInfo cacheCargeInfo) throws InterruptedException {
        return cacheChargeService.cacheCharge(cacheCargeInfo);
    }

    @PostMapping("/charge/validation")
    public boolean validCharge(@RequestBody @Validated ChargeInfoRequest chargeInfoRequest){

        return true;
    }
}
