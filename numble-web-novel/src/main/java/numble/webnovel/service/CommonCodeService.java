package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.ChildCode;
import numble.webnovel.domain.ParentCode;
import numble.webnovel.enums.CommonExceptionEnum;
import numble.webnovel.exceptions.CommonException;
import numble.webnovel.repository.CommonCodeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommonCodeService {

    private final CommonCodeRepository commonCodeRepository;

    @Transactional
    public void saveParentCode(ParentCode parentCode){
        commonCodeRepository.saveParentCode(parentCode);
    }

    @Transactional
    public void saveChildCode(ChildCode childCode){
        ParentCode parentCode = commonCodeRepository.findByCode(childCode.getParentCode());
        if(parentCode == null){
            throw new CommonException(CommonExceptionEnum.RESULT_NOT_EXIST_EXCEPTION);
        }
        commonCodeRepository.saveChildCode(childCode);
    }
}
