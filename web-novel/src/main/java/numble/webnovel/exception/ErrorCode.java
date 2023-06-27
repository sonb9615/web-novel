package numble.webnovel.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //Security
    NOT_CORRECT_TOKEN(HttpStatus.BAD_REQUEST, "SECURITY_001","잘못된 JWT 서명입니다.")
    ,EXPIRED_TOKEN(HttpStatus.BAD_REQUEST, "SECURITY_002","만료된 JWT 토큰입니다.")
    ,NOT_SUPPORTED_TOKEN(HttpStatus.BAD_REQUEST, "SECURITY_003","지원되지 않는 JWT 토큰입니다.")
    ,NOT_VALID_TOKEN(HttpStatus.BAD_REQUEST, "SECURITY_004","유효하지 않은 JWT 토큰입니다.")
    //Member
    ,ALREADY_EXISTS_NICKNAME(HttpStatus.BAD_REQUEST, "MEMBER_001","이미 존재하는 닉네임입니다.")
    ,ALREADY_EXISTS_EMAIL(HttpStatus.BAD_REQUEST, "MEMBER_002","이미 존재하는 이메일입니다.")
    ,NO_EXISTS_MEMBER(HttpStatus.BAD_REQUEST, "MEMBER_003","존재하지 않는 회원입니다.")
    ,NOT_CORRECT_PASSWORD(HttpStatus.BAD_REQUEST, "MEMBER_004","비밀번호가 맞지 않습니다.")
    ,NO_ENOUGH_CACHE(HttpStatus.BAD_REQUEST, "MEMBER_005","보유한 캐시가 충분하지 않습니다.")
    //Novel
    ,NO_VALID_GENRE(HttpStatus.BAD_REQUEST, "NOVEL_001","올바르지 않은 장르입니다.")
    ,NO_VALID_NOVEL_STATUS(HttpStatus.BAD_REQUEST, "NOVEL_002","올바르지 않은 연재 상태 입니다.")
    ,NO_EXISTS_NOVEL(HttpStatus.BAD_REQUEST, "NOVEL_003","존재하지 않는 소설입니다.")
    //Episode
    ,NO_EXISTS_EPISODE(HttpStatus.BAD_REQUEST, "EPISODE_001","존재하지 않는 에피소드입니다.")
    //Lock
    ,NO_AVAILABLE_LOCK(HttpStatus.BAD_REQUEST, "LOCK_001","Lock 획득에 실패하였습니다.")
    //NovelTicket
    ,NO_ENOUGH_TICKET(HttpStatus.BAD_REQUEST, "TICKET_001","소설 이용권이 부족합니다.")
    //Library
    ,ALREADY_EXIST_EPISODE(HttpStatus.BAD_REQUEST, "LIBRARY_001","이미 소유한 에피소드 입니다.")
    ;

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;
}
