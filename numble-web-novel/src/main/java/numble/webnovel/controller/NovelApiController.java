package numble.webnovel.controller;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.NovelTicketsResponse;
import numble.webnovel.domain.NovelTicketsRequest;
import numble.webnovel.enums.ExceptionEnum;
import numble.webnovel.exceptions.CommonException;
import numble.webnovel.service.UserNovelTicketsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NovelApiController {

    private final UserNovelTicketsService userNovelTicketsService;

    @PostMapping("/novel/useTicket")
    public NovelTicketsResponse useNovelTicket(@RequestBody @Validated NovelTicketsRequest novelTicketsRequest){
        if(userNovelTicketsService.validRequestParam(novelTicketsRequest)){
            return userNovelTicketsService.useNovelTickets(novelTicketsRequest);
        }
        throw new CommonException(ExceptionEnum.PARAM_NOT_EXIST_EXCEPTION);
    }
}
