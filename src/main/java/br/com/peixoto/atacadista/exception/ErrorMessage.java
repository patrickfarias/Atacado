package br.com.peixoto.atacadista.exception;

import br.com.peixoto.atacadista.configuration.MessageConfig;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(name = "ErrorMessage")
public class ErrorMessage implements Serializable {

    private static final long serialVersionUID = 5034724137320635682L;

    @Schema(example = "400")
    private String code;

    @Schema(example = "A data inicial não pode ser maior que a data final.")
    private String message;

    public ErrorMessage(String code, Object... args) {
        this.code = code;
        this.message = new MessageConfig().getMessage(code, args);
    }

}
