package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.UserNovelTickets;
import numble.webnovel.enums.CommonExceptionEnum;
import numble.webnovel.exceptions.CommonException;
import numble.webnovel.repository.UserNovelTicketsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserNovelTicketsService {

    private final UserNovelTicketsRepository userNovelTicketsRepository;

    @Transactional
    public void saveUserNovelTickets(UserNovelTickets userNovelTickets){
        userNovelTicketsRepository.save(userNovelTickets);
    }

    @Transactional
    public List<UserNovelTickets> findUsableTicketsByNovelId(String userNo, String novelId){
        List<UserNovelTickets> userNovelTicketsList
                = userNovelTicketsRepository.findUsableTicketsByNovelIdUserId(userNo, novelId);
        if(userNovelTicketsList.size() == 0){
            throw new CommonException(CommonExceptionEnum.RESULT_NOT_EXIST_EXCEPTION);
        }
        return userNovelTicketsList;
    }
}
