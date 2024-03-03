package br.com.peixoto.atacadista.dto;

import br.com.peixoto.atacadista.domain.Cliente;
import br.com.peixoto.atacadista.domain.ItemPedido;
import br.com.peixoto.atacadista.enumeration.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequestDTO {

    @Schema(example = "Higiene Pessoal")
    @Valid
    @NotNull
    private String descricao;

    private StatusPedido status;

    private Cliente cliente;

    private List<ItemPedido> itens = new ArrayList<>();


}