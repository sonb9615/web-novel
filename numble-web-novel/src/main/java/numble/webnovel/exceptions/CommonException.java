package numble.webnovel.exceptions;

import lombok.Getter;
import numble.webnovel.enums.ExceptionEnum;

@Getter
public class CommonException extends RuntimeException{

    private ExceptionEnum error;

    public CommonException(ExceptionEnum e) {
        super((e.getMessage()));
        this.error = e;
    }
}
