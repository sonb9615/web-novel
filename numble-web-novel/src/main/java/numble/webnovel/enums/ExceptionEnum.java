package numble.webnovel.enums;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ExceptionEnum {

    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "E0001"),
    ACCESS_DENIED_EXCEPTION(HttpStatus.UNAUTHORIZED, "E0002"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E0003"),
    MESSAGE_NOT_READABLE_EXCEPTION(HttpStatus.BAD_REQUEST, "E0004","올바른 형태의 파라미터인지 확인해주세요."),
    INVALID_FORMAT_EXCEPTION(HttpStatus.BAD_REQUEST,"E0005","올바른 형태의 값인지 확인해주세요."),
    RESULT_NOT_EXIST_EXCEPTION(HttpStatus.BAD_REQUEST,"CE0002","값이 존재하지 않습니다."),
    CHARGE_RANGE_EXCEPTION(HttpStatus.BAD_REQUEST, "ZE0001", "100캐시부터 충전 가능합니다."),
    DUPLICATE_CHARGE_EXCEPTION(HttpStatus.BAD_REQUEST, "DE0001", "중복 충전 건입니다."),
    CACHE_SHORTAGE_EXCEPTION(HttpStatus.BAD_REQUEST, "SE0001", "캐쉬가 부족합니다."),
    PARAM_NOT_EXIST_EXCEPTION(HttpStatus.BAD_REQUEST, "PE0001", "필수 파라미터 확인해주세요."),
    NOVEL_TICKET_NOT_EXIST_EXCEPTION(HttpStatus.BAD_REQUEST, "NE0001", "사용가능한 소설 이용권이 없습니다.");

    private final HttpStatus status;
    private final String code;
    private String message;

    ExceptionEnum(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    ExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
