package numble.webnovel.novelTicket.controller;

import lombok.RequiredArgsConstructor;
import numble.webnovel.common.CommonResponse;
import numble.webnovel.novelTicket.dto.ChargeNovelTicketRequest;
import numble.webnovel.novelTicket.service.NovelTicketService;
import numble.webnovel.util.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/novelTicket")
public class NovelTicketController {

    private final NovelTicketService novelTicketService;

    @PostMapping
    public ResponseEntity<CommonResponse<Void>> chargeNovelTicket(@AuthenticationPrincipal UserDetailsImpl userDetails
            , @RequestParam Long novelId, @RequestBody ChargeNovelTicketRequest request){
        novelTicketService.chargeNovelTicket(userDetails.getMember(), novelId, request);
        return new ResponseEntity<>(new CommonResponse<>("소설 이용권이 충전되었습니다.", null), HttpStatus.OK);
    }
}
