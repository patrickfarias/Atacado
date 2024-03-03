package br.com.peixoto.atacadista.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class AbstractMessageErrorCode {

    // 400
    public static final String CATEGORIA_NAO_ENCONTRADA = "400.001";
    public static final String PRODUTO_NAO_ENCONTRADO = "400.002";
    public static final String PEDIDO_NAO_ENCONTRADO = "400.003";
    public static final String PEDIDO_NAO_PODE_SER_ALTERADO = "400.004";
    public static final String DESCONTO_ACIMA_DO_PERMITIDO_POR_PRODUTO = "400.005";
    public static final String CLIENTE_NAO_ENCONTRADO = "400.006";
    public static final String LIMITE_POR_PEDIDO_EXCEDIDO = "400.007";
    public static final String ITEM_COM_QUANTIDADE_ZERADA = "400.008";
    public static final String LIMITE_MINIMO_ITEM_ATINGIDO = "400.009";
    public static final String FIELD_REQUIRED = "400.10";
    public static final String FIELD_INVALID = "400.11";
    public static final String HEADER_REQUIRED = "400.12";

    // 422
    public static final String CONTACT_SYSTEM_ADMIN = "422.005";

}
