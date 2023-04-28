package numble.webnovel.controller;

import lombok.RequiredArgsConstructor;
import numble.webnovel.repository.dto.request.NovelSaveRequest;
import numble.webnovel.repository.dto.response.NovelTicketsResponse;
import numble.webnovel.repository.dto.request.NovelTicketsRequest;
import numble.webnovel.enums.ExceptionEnum;
import numble.webnovel.exceptions.CommonException;
import numble.webnovel.service.NovelService;
import numble.webnovel.service.UserNovelTicketsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NovelApiController {

    private final UserNovelTicketsService userNovelTicketsService;
    private final NovelService novelService;

    @PostMapping("/novel/save")
    public void saveNovel(@RequestBody @Validated NovelSaveRequest request){
        if(novelService.validateRequestParam(request)){
            novelService.saveNovel(request);
        }
        throw new CommonException(ExceptionEnum.PARAM_NOT_EXIST_EXCEPTION);
    }

    @PostMapping("/novel/useTicket")
    public NovelTicketsResponse useNovelTicket(@RequestBody @Validated NovelTicketsRequest request){
        if(userNovelTicketsService.validRequestParam(request)){
            userNovelTicketsService.useNovelTickets(request.getUserNo(), request.getNovelId(), request.getEpisodeId());
            return NovelTicketsResponse.createNovelTicketsResponse(request.getNovelId(), request.getEpisodeId());
        }
        throw new CommonException(ExceptionEnum.PARAM_NOT_EXIST_EXCEPTION);
    }
}
