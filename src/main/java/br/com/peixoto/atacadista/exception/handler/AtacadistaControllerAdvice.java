package br.com.peixoto.atacadista.exception.handler;

import static br.com.peixoto.atacadista.exception.AbstractMessageErrorCode.FIELD_INVALID;
import static br.com.peixoto.atacadista.exception.AbstractMessageErrorCode.FIELD_REQUIRED;
import static java.lang.String.format;

import br.com.peixoto.atacadista.exception.ApiManagerException;
import br.com.peixoto.atacadista.exception.BadRequestException;
import br.com.peixoto.atacadista.exception.BusinessException;
import br.com.peixoto.atacadista.exception.ErrorMessage;
import br.com.peixoto.atacadista.exception.NotFoundException;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
@Order(value = 1)
public class AtacadistaControllerAdvice extends ResponseEntityExceptionHandler {

    private static final String DETAIL = "%s - Detail: %s";

    @ExceptionHandler(value = ApiManagerException.class)
    protected ResponseEntity<Object> handleApiManagerException(ApiManagerException exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        return handleExceptionInternal(exception, exception.getError(), new HttpHeaders(), exception.getStatus(), request);
    }

    @ExceptionHandler(value = BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequestException(BadRequestException exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        return handleExceptionInternal(exception, exception.getErrors(), new HttpHeaders(), exception.getStatus(), request);
    }

    @ExceptionHandler(value = BusinessException.class)
    protected ResponseEntity<Object> handleBusinessException(BusinessException exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        return handleExceptionInternal(exception, exception.getError(), new HttpHeaders(), exception.getStatus(), request);
    }

    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        return handleExceptionInternal(exception, null, new HttpHeaders(), exception.getStatus(), request);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
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



    protected ErrorMessage createError(ObjectError error) {
        String field = "";
        if (error instanceof FieldError) {

            final FieldError fieldError = (FieldError) error;
            field = fieldError.getField();
        }

        return new ErrorMessage(error.getDefaultMessage(), field);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {


        if (ex.getCause() instanceof JsonMappingException) {

            final JsonMappingException cause = (JsonMappingException) ex.getCause();

            final String field = cause.getPath().stream()
                    .map(JsonMappingException.Reference::getFieldName)
                    .filter(StringUtils::isNotBlank)
                    .collect(Collectors.joining("."));

            final ErrorMessage error = new ErrorMessage(FIELD_INVALID, field);

            log.error(format(DETAIL, error, ex.getMessage()), ex);
            return handleExceptionInternal(ex, error, headers, HttpStatus.BAD_REQUEST, request);
        }

        log.error(ex.getMessage(), ex);
        return handleExceptionInternal(ex, null, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers,
                                                                          HttpStatus status,
                                                                          WebRequest request) {
        final ErrorMessage error = new ErrorMessage(FIELD_REQUIRED, ex.getParameterName());

        log.error(format(DETAIL, error, ex.getMessage()), ex);
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
