package numble.webnovel.exceptions;

import lombok.Getter;
import numble.webnovel.enums.CommonExceptionEnum;

@Getter
public class CommonException extends RuntimeException{

    private CommonExceptionEnum error;

    public CommonException(CommonExceptionEnum e) {
        super((e.getMessage()));
        this.error = e;
    }
}
