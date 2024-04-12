package org.example.controller;

import com.sun.jdi.request.InvalidRequestStateException;
import jakarta.persistence.EntityNotFoundException;
import org.example.dto.ApiErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class ExceptionBotApiHandler {
    public static final String CODE_400 = "400";
    public static final String CODE_400_DESCRIPTION = "Bad request";

    public static final String CODE_404 = "404";
    public static final String CODE_404_DESCRIPTION = "object with this number not exist";
    public static final String CODE_500 = "500";
    public static final String CODE_500_DESCRIPTION = "Server error";

    //todo Заменить отправку стректрейса на фронт(что небезопасно и мало читаемо) на что-то адекватное
    @ExceptionHandler(InvalidRequestStateException.class)
    public ResponseEntity<?> handleInvalidRequestStateException(InvalidRequestStateException e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(CODE_400_DESCRIPTION, CODE_400,
                e.getClass().getSimpleName(), e.getMessage(), List.of(Arrays.toString(e.getStackTrace())));
        return ResponseEntity.badRequest().body(apiErrorResponse);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(CODE_404_DESCRIPTION, CODE_404,
                e.getClass().getSimpleName(), e.getMessage(), List.of(Arrays.toString(e.getStackTrace())));
        return ResponseEntity.badRequest().body(apiErrorResponse);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception e) {

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(CODE_500_DESCRIPTION, CODE_500,
                e.getClass().getSimpleName(), e.getMessage(), List.of(Arrays.toString(e.getStackTrace())));
        return ResponseEntity.internalServerError().body(apiErrorResponse);
    }
}
