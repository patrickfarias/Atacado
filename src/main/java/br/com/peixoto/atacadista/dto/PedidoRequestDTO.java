package br.com.peixoto.atacadista.dto;

import br.com.peixoto.atacadista.domain.Cliente;
import br.com.peixoto.atacadista.domain.ItemPedido;
import java.util.ArrayList;
import java.util.List;
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

    private Cliente cliente;

    private List<ItemPedido> itens = new ArrayList<>();


}