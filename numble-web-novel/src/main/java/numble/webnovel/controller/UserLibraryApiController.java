package numble.webnovel.controller;

import lombok.RequiredArgsConstructor;
import numble.webnovel.service.LibraryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserLibraryApiController {

    private final LibraryService libraryService;

//    @PostMapping("/userLib/find")
//    public List<UserLibrary> findUserLibrary(@RequestBody @Validated UserLibraryRequest userLibraryRequest){
//        if(!userLibraryRequest.getUserNo().isEmpty()){
//            return userLibraryService.findUserLibrary(userLibraryRequest.getUserNo());
//        }
//        throw new CommonException(ExceptionEnum.PARAM_NOT_EXIST_EXCEPTION);
//    }
}
