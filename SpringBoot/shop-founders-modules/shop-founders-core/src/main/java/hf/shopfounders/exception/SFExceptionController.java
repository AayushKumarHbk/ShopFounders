package hf.shopfounders.exception;


import hf.shopfounders.model.SFExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SFExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<SFExceptionResponse> invalidParameter(MethodArgumentNotValidException ex) {
        final SFExceptionResponse response = new SFExceptionResponse();
        response.setMessage(BaseErrorCode.getString(BaseErrorCode.CODE1000));
        response.setStatus(false);
        return new ResponseEntity<SFExceptionResponse>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}