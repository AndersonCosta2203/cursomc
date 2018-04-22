package br.com.cursomc.domain;

import br.com.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) /* Para configurar o JPA, onde será gerada apenas uma tabela,
                                                    incluindo os atributos das subclasses*/
public abstract class Pagamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @JoinColumn(name = "pedido_id")
    @OneToOne
    @MapsId /* Para não criar um relacionamento bidirecional, onde pode haver um custo muito grande,
                utiliza-se esta notação para informar que o id do pedido será chave composta para pagamento
               Garante que o Id do pagamento, seja o mesmo do Pedido  */
    private Pedido pedido;

    private Integer estado;

    public Pagamento(Integer id, Pedido pedido, EstadoPagamento estado) {
        this.id = id;
        this.pedido = pedido;
        this.estado = estado == null ? null : estado.getCod();
    }

    public Pagamento() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public EstadoPagamento getEstado() {
        return EstadoPagamento.toEnum(this.estado);
    }

    public void setEstado(EstadoPagamento estado) {
        this.estado = estado.getCod();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
