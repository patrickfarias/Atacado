package br.com.peixoto.atacadista.exception;

import java.io.Serializable;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 8038944089198332483L;
    private final HttpStatus status;
    private final ErrorMessage error;

    public BusinessException(ErrorMessage error) {
        this.status = HttpStatus.UNPROCESSABLE_ENTITY;
        this.error = error;
    }

}
