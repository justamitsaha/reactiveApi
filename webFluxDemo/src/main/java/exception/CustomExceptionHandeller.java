package exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.saha.amit.dto.ExceptionResponse;

@ControllerAdvice
public class CustomExceptionHandeller {
	
	@ExceptionHandler(InputValidationException.class)
	public ResponseEntity<ExceptionResponse> handelInputException(InputValidationException ex) {
		ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(ex.getErrorCode());
        response.setInput(ex.getInput());
        response.setMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
	}

}
