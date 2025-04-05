package com.SpringbootAcademy.pos2.advisor;


import com.SpringbootAcademy.pos2.exception.NotFoundException;
import com.SpringbootAcademy.pos2.util.mappers.StandardResponse;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException e) {

        return  new ResponseEntity<StandardResponse>(
                new StandardResponse(404,"Not-Found",e.getMessage()), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<StandardResponse> handleDuplicateKeyException(DuplicateKeyException e) {
        return  new ResponseEntity<StandardResponse>(
                new StandardResponse(409,"Duplicate Key",e.getMessage()), HttpStatus.CONFLICT
        );
    }
}
