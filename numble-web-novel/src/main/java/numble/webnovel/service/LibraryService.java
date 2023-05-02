package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.Library;
import numble.webnovel.repository.LibraryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final LibraryRepository libraryRepository;

    @Transactional
    public void saveUserLibrary(Library library){
        libraryRepository.save(library);
    }

//    @Transactional
//    public List<UserLibrary> findUserLibrary(String userNo){
//        return userLibraryRepository.findListByUserNo(userNo);
//    }

}
