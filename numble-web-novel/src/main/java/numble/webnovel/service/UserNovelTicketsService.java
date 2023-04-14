package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.UserNovelTickets;
import numble.webnovel.repository.UserNovelTicketsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserNovelTicketsService {

    private final UserNovelTicketsRepository userNovelTicketsRepository;

    @Transactional
    public void saveUserNovelTickets(UserNovelTickets userNovelTickets){
        userNovelTicketsRepository.save(userNovelTickets);
    }
}
