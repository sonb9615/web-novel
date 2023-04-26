package numble.webnovel.controller;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.UserLibrary;
import numble.webnovel.domain.UserLibraryRequest;
import numble.webnovel.enums.ExceptionEnum;
import numble.webnovel.exceptions.CommonException;
import numble.webnovel.service.UserLibraryService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserLibraryApiController {

    private final UserLibraryService userLibraryService;

//    @PostMapping("/userLib/find")
//    public List<UserLibrary> findUserLibrary(@RequestBody @Validated UserLibraryRequest userLibraryRequest){
//        if(!userLibraryRequest.getUserNo().isEmpty()){
//            return userLibraryService.findUserLibrary(userLibraryRequest.getUserNo());
//        }
//        throw new CommonException(ExceptionEnum.PARAM_NOT_EXIST_EXCEPTION);
//    }
}
