package com.recruitflow.api.handlers;

import org.springframework.http.HttpStatus;

public class CandidaturaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CandidaturaException(String message) {
        super(message);
    }
}
