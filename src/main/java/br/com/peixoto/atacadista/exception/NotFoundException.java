package br.com.peixoto.atacadista.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends RuntimeException {

    private final HttpStatus status;

    public NotFoundException() {
        this.status = HttpStatus.NOT_FOUND;
    }

    public NotFoundException(Throwable throwable) {
        this.status = HttpStatus.NOT_FOUND;
    }

}
