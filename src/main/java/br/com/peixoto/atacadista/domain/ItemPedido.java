package br.com.peixoto.atacadista.domain;

import br.com.peixoto.atacadista.exception.AbstractMessageErrorCode;
import br.com.peixoto.atacadista.exception.BadRequestException;
import br.com.peixoto.atacadista.exception.ErrorMessage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ItemPedido {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantidade;
    private BigDecimal precoUnitario;

    private BigDecimal percentualDescontoConcedido;

    private BigDecimal valorDescontoConcedido;
    private BigDecimal precoTotal;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Produto produto;

    public void calcularPrecoTotal() {
        BigDecimal precoUnitario = this.getPrecoUnitario();
        Integer quantidade = this.getQuantidade();

        if (precoUnitario == null) {
            precoUnitario = BigDecimal.ZERO;
        }

        if (quantidade == null) {
            quantidade = 0;
        }

        final BigDecimal subTotal = precoUnitario.multiply(new BigDecimal(this.getQuantidade()));
        final BigDecimal percent = this.percentualDescontoConcedido.divide(new BigDecimal("100.00"));
        this.setValorDescontoConcedido(precoUnitario.multiply(percent).multiply(new BigDecimal(this.getQuantidade())));
        this.setPrecoTotal(subTotal.subtract(this.getValorDescontoConcedido()));

    }

    public void validadarItens() {
        BigDecimal precoUnitario = this.getPrecoUnitario();
        Integer quantidade = this.getQuantidade();
        final BigDecimal percentDescontoSolicitado = this.getPercentualDescontoConcedido();
        final BigDecimal percentDescontoProduto = this.getProduto().getPercentualMaximoDesconto();

        if (precoUnitario == null) {
            precoUnitario = BigDecimal.ZERO;
        }

        if (quantidade == null) {
            quantidade = 0;
        }


        if(percentDescontoSolicitado.compareTo(percentDescontoProduto) > 0){
            throw new BadRequestException(new ErrorMessage(AbstractMessageErrorCode.DESCONTO_ACIMA_DO_PERMITIDO_POR_PRODUTO,
                    this.getProduto().getId(), this.getProduto().getDescricao(),
                    percentDescontoSolicitado,percentDescontoProduto));
        }

        // (QTD X VL_UNIT) - DESC
        final BigDecimal subTotal = precoUnitario.multiply(new BigDecimal(this.getQuantidade()));
        final BigDecimal percent = percentDescontoSolicitado.divide(new BigDecimal("100.00"));
        this.setValorDescontoConcedido(precoUnitario.multiply(percent).multiply(new BigDecimal(this.getQuantidade())));
        this.setPrecoTotal(subTotal.subtract(this.getValorDescontoConcedido()));

    }

}
