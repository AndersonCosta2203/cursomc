package br.com.cursomc.repositories;

import br.com.cursomc.domain.Cliente;
import br.com.cursomc.domain.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    /*
     * Esta operação não necessita de ser envolvida como uma transação de banco de dados
     * Evita lock no gerenciamento de transacoes do banco de dados e fica mais rápido
     * */
    @Transactional(readOnly = true)
    Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);
}
