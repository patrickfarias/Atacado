package br.com.peixoto.atacadista.exception;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 4467542256555217207L;
    private final HttpStatus status;
    private final Set<ErrorMessage> errors;

    public BadRequestException(Set<ErrorMessage> errors) {
        this.status = HttpStatus.BAD_REQUEST;
        this.errors = errors;
    }

    public BadRequestException(ErrorMessage error) {

        final Set<ErrorMessage> errorMessage = new HashSet<>();
        errorMessage.add(error);

        throw new BadRequestException(errorMessage);

    }

}
