package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.ParentCode;
import numble.webnovel.repository.ParentCodeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommonCodeService {

    private final ParentCodeRepository parentCodeRepository;

    @Transactional
    public void saveParentCode(ParentCode parentCode){
        parentCodeRepository.save(parentCode);
    }

//    @Transactional
//    public void saveChildCode(ChildCode childCode){
//        ParentCode parentCode = commonCodeRepository.findByCode(childCode.getParentCode());
//        if(parentCode == null){
//            throw new CommonException(ExceptionEnum.RESULT_NOT_EXIST_EXCEPTION);
//        }
//        commonCodeRepository.saveChildCode(childCode);
//    }
}
