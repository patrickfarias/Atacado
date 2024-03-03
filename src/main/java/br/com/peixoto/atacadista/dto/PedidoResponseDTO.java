package br.com.peixoto.atacadista.dto;

import br.com.peixoto.atacadista.domain.ItemPedido;
import br.com.peixoto.atacadista.enumeration.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(example = "1499")
    private String id;

    private StatusPedido status;

    private ClienteResponseDTO cliente;

    private List<ItemPedido> itens = new ArrayList<>();

    @Schema(example = "2024-02-27T18:33:29.624557Z")
    private OffsetDateTime dataCriacao;

    @Schema(example = "2024-02-28T10:08:52.935113Z")
    private OffsetDateTime dataAlteracao;

    private int quantidadeTotalItens;

    private BigDecimal subtotal;

    private BigDecimal valorTotalDesconto;

    private BigDecimal valorTotal;

}