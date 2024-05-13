package com.example.user.exceptions;

import com.example.shoppingclient.errorDTO.ErrorDTO;
import com.example.shoppingclient.errorDTO.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestControllerAdvice
public class UserControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorDTO handleUserNotFoundException(UserNotFoundException ex) {
        return new ErrorDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage(), new Date());
    }

}
