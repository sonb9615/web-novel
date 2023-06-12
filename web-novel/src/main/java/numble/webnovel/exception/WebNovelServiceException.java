package numble.webnovel.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WebNovelServiceException extends RuntimeException{

    private final ErrorCode errorCode;
}
