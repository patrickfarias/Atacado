package br.com.peixoto.atacadista.exception.handler;


import static br.com.peixoto.atacadista.exception.AbstractMessageErrorCode.CONTACT_SYSTEM_ADMIN;
import static br.com.peixoto.atacadista.exception.AbstractMessageErrorCode.FIELD_INVALID;
import static br.com.peixoto.atacadista.exception.AbstractMessageErrorCode.HEADER_REQUIRED;
import static java.lang.String.format;

import br.com.peixoto.atacadista.exception.ErrorMessage;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
@Order(value = 2)
public class AtacadistaExternalControllerAdvice extends ResponseEntityExceptionHandler {

    private static final String DETAIL = "%s - Detail: %s";
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
        final ErrorMessage error = new ErrorMessage(FIELD_INVALID, ex.getName());

        log.error(format(DETAIL, error, ex.getMessage()), ex);
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        final List<ErrorMessage> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> errors.add(createError(error)));

        log.error(format(DETAIL, errors, ex.getMessage()), ex);
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
                                                                          HttpHeaders headers, HttpStatus status,
                                                                          WebRequest request) {
        if (ex instanceof MissingRequestHeaderException) {

            final MissingRequestHeaderException missingRequestHeaderException = (MissingRequestHeaderException) ex;
            final ErrorMessage error = new ErrorMessage(HEADER_REQUIRED,
                    missingRequestHeaderException.getHeaderName());
            final List<ErrorMessage> errorMessageList = new ArrayList<>();
            errorMessageList.add(error);

            log.error(format(DETAIL, errorMessageList, ex.getMessage()), ex);
            return handleExceptionInternal(ex, errorMessageList, headers, status, request);
        }

        log.error(ex.getMessage(), ex);
        return handleServletRequestBindingException(ex, headers, status, request);
    }

    protected ErrorMessage createError(ObjectError error) {
        String field = "";
        if (error instanceof FieldError) {

            final FieldError fieldError = (FieldError) error;
            field = fieldError.getField();
        }

        return new ErrorMessage(error.getDefaultMessage(), field);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        final ErrorMessage error = new ErrorMessage(CONTACT_SYSTEM_ADMIN);

        log.error(format(DETAIL, error, ex.getMessage()), ex);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

}
