package com.assignment.players.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlayerNotFoundException extends Throwable {
    public PlayerNotFoundException(String message) {
        super(message);
    }
}
