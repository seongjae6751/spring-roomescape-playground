package roomescape.exception;

import org.springframework.http.HttpStatus;

public class ParamException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final String errorMessage;

    public enum Type {
        MISSING_PARAMETER(HttpStatus.BAD_REQUEST, "모든 정보를 입력해야 예약할 수 있습니다");

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

    public ParamException(ParamException.Type type) {
        super(type.getErrorMessage());
        this.httpStatus = type.getHttpStatus();
        this.errorMessage = type.getErrorMessage();
    }

}
