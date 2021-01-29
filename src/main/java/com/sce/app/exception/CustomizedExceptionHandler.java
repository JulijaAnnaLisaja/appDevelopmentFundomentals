package com.sce.app.exception;

import com.sce.app.exception.status.NotExistException;
import com.sce.app.model.CustomizedExceptionModel;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

/**
 * Customized Exception handler class, handle exception parts.
 *
 * @author julija.anna.lisaja@accenture.com
 */
@ControllerAdvice
public class CustomizedExceptionHandler {

    @ExceptionHandler(value = NotExistException.class)
    public ResponseEntity<Object> empNotExists(NotExistException e) {
        return new ResponseEntity<>(requestType(e, "404", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    private CustomizedExceptionModel requestType(Exception e, String errorCode, HttpStatus httpStatus) {
        return new CustomizedExceptionModel(
                e.getMessage(),
                errorCode,
                httpStatus,
                ZonedDateTime.now());
    }

    /**
     * Custom message for all exceptions.
     *
     * @param entityName entity name where exception has been caused.
     * @param recordName field name, where exception caused.
     * @param recordValue value of the field.
     * @return custom exception message.
     */
    public static String customExceptionMessage(String entityName, String recordName, String recordValue) {
        return "No " + entityName + " was found with " + recordName + " : " + recordValue;
    }
}
