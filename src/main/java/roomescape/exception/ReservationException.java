package roomescape.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ReservationException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String errorMessage;

    public enum Type {
        NOT_FOUND_RESERVATION(HttpStatus.BAD_REQUEST, "삭제할 예약이 없습니다.");

        private final HttpStatus httpStatus;
        private final String errorMessage;

        Type(HttpStatus httpStatus, String errorMessage) {
            this.httpStatus = httpStatus;
            this.errorMessage = errorMessage;
        }

        public HttpStatus getHttpStatus() {
            return httpStatus;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }

    public ReservationException(Type type) {
        super(type.getErrorMessage());
        this.httpStatus = type.getHttpStatus();
        this.errorMessage = type.getErrorMessage();
    }

}

