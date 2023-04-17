package numble.webnovel.exceptions;

import lombok.Getter;
import lombok.Setter;
import numble.webnovel.enums.ExceptionEnum;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionResponse {

    private HttpStatus status;
    private String code;
    private String message;

    public ExceptionResponse(ExceptionEnum exceptionEnum) {
        this.status = exceptionEnum.getStatus();
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
    }
}
