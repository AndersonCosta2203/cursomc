package br.com.cursomc.domain;

import br.com.cursomc.domain.enums.EstadoPagamento;

import javax.persistence.Entity;

@Entity
public class PagamentoComCartao extends Pagamento {

    private Integer numeroDeParcelas;

    public PagamentoComCartao(Integer id, Pedido pedido, EstadoPagamento estado, Integer numeroDeParcelas) {
        super(id, pedido, estado);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public PagamentoComCartao() {
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
