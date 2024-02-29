package br.com.peixoto.atacadista.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class AbstractMessageErrorCode {

    public static final String CATEGORIA_NAO_ENCONTRADA = "400.001";
    public static final String FIELD_REQUIRED = "400.002";
    public static final String FIELD_INVALID = "400.003";
    public static final String HEADER_REQUIRED = "400.004";
    public static final String CONTACT_SYSTEM_ADMIN = "422.005";

}
