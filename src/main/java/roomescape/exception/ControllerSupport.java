package roomescape.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerSupport {
    @ExceptionHandler(ReservationException.class)
    public ResponseEntity<String> handleException (ReservationException e){
        return ResponseEntity.status(ReservationException.Type.NOT_FOUND_RESERVATION.getHttpStatus())
                .body(ReservationException.Type.NOT_FOUND_RESERVATION.getErrorMessage());
    }

    @ExceptionHandler(ParamException.class)
    public ResponseEntity<String> handleException (ParamException e){
        return ResponseEntity.status(ParamException.Type.MISSING_PARAMETER.getHttpStatus())
                .body(ParamException.Type.MISSING_PARAMETER.getErrorMessage());
    }

}
