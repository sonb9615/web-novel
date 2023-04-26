package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.*;
import numble.webnovel.enums.ExceptionEnum;
import numble.webnovel.exceptions.CommonException;
import numble.webnovel.repository.UserNovelTicketsRepository;
import numble.webnovel.repository.dto.response.NovelTicketsResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserNovelTicketsService {

    private final UserNovelTicketsRepository userNovelTicketsRepository;
    private final UserLibraryService userLibraryService;
    private final UUIDGeneration uuidGeneration;
    private final NovelEpisodeService novelEpisodeService;

    @Transactional
    public void useNovelTickets(String userNo, String novelId, String episodeId){
        List<UserNovelTickets> userNovelTicketsList = this.findUsableTickets(userNo, novelId);
        //이용권 하나 사용
        UserNovelTickets ticket = userNovelTicketsList.get(0);
        String ticketId = ticket.useTicket();
        //유저 라이브러리에 에피 저장
        NovelEpisode novelEpisode = novelEpisodeService.findNovelEpisodeById(episodeId);
        UserLibrary userLibrary = UserLibrary.createUserLibrary(uuidGeneration.getUUID(), novelEpisode,ticket.getUserInfo(), ticket);
        userLibraryService.saveUserLibrary(userLibrary);
    }

    @Transactional
    public void saveUserNovelTickets(UserNovelTickets userNovelTickets){
        userNovelTicketsRepository.save(userNovelTickets);
    }

    @Transactional
    public List<UserNovelTickets> findUsableTickets(String userNo, String novelId){
        List<UserNovelTickets> userNovelTicketsList
                = userNovelTicketsRepository.findAllTicketsByNovelIdUserId(userNo, novelId);
        if(userNovelTicketsList.size() == 0){
            throw new CommonException(ExceptionEnum.NOVEL_TICKET_NOT_EXIST_EXCEPTION);
        }
        return userNovelTicketsList;
    }

    public boolean validRequestParam(NovelTicketsRequest reqParam){
        return !reqParam.getUserNo().isEmpty() && !reqParam.getNovelId().isEmpty() && !reqParam.getEpisodeId().isEmpty();
    }
}
