package br.com.cursomc.domain;

import br.com.cursomc.domain.enums.EstadoPagamento;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class PagamentoComBoleto extends Pagamento {

    @Temporal(TemporalType.DATE)
    private Date dataVencimento;

    @Temporal(TemporalType.DATE)
    private Date dataPagamento;

    public PagamentoComBoleto(Integer id, Pedido pedido, EstadoPagamento estado, Date dataVencimento, Date dataPagamento) {
        super(id, pedido, estado);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }

    public PagamentoComBoleto() {
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

}
