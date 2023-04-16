package numble.webnovel.controller;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.CacheCargeInfo;
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
public class ApiController {

    private final NovelTicketsChargeService novelTicketsChargeService;
    private final CacheChargeService cacheChargeService;

    @PostMapping("/api/chargeTickets")
    public void chargeTickets(@RequestBody @Validated NovelTicketChargeInfo novelTicketChargeInfo){
        novelTicketsChargeService.chargeTicket(novelTicketChargeInfo);
    }

    @PostMapping("api/cacheCharge")
    public void cacheCharge(@RequestBody @Validated CacheCargeInfo cacheCargeInfo) throws InterruptedException {
        cacheChargeService.cacheCharge(cacheCargeInfo);
    }

    @PostMapping("api/validCharge")
    public boolean validCharge(@RequestBody @Validated ChargeInfoRequest chargeInfoRequest){

        return true;
    }
}
