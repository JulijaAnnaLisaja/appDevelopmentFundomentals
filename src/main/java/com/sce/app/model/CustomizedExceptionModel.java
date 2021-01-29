package com.sce.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
@Getter
public class CustomizedExceptionModel {

    private final String message;
    private final String errorCode;
    private final HttpStatus httpStatus;

    @JsonFormat(pattern="MM/dd/yyyy HH:mm:ss")
    private final ZonedDateTime timestamp;
}
