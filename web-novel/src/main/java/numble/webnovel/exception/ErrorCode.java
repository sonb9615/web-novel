package numble.webnovel.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    ALREADY_EXISTS_NICKNAME(HttpStatus.BAD_REQUEST, "MEMBER_001","이미 존재하는 닉네임입니다.")
    ,ALREADY_EXISTS_EMAIL(HttpStatus.BAD_REQUEST, "MEMBER_002","이미 존재하는 이메일입니다.")
    ,NO_EXISTS_MEMBER(HttpStatus.BAD_REQUEST, "MEMBER_003","존재하지 않는 회원입니다.")
    ,NOT_CORRECT_PASSWORD(HttpStatus.BAD_REQUEST, "MEMBER_004","비밀번호가 맞지 않습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;
}