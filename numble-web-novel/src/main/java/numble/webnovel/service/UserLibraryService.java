package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.UserLibrary;
import numble.webnovel.repository.UserLibraryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLibraryService {

    private final UserLibraryRepository userLibraryRepository;

    @Transactional
    public void saveUserLibrary(UserLibrary userLibrary){
        userLibraryRepository.save(userLibrary);
    }

//    @Transactional
//    public List<UserLibrary> findUserLibrary(String userNo){
//        return userLibraryRepository.findListByUserNo(userNo);
//    }

}
