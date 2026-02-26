package com.smartContact.error;

import com.smartContact.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class RestResponseEntityExceptionHandling  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> userNotFoundException(UserNotFoundException exception, WebRequest request){

        ErrorMessage errorMessage=new ErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
