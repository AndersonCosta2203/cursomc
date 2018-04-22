package br.com.cursomc.repositories;

import br.com.cursomc.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    /*
    * Esta operação não necessita de ser envolvida como uma transação de banco de dados
    * Evita lock no gerenciamento de transacoes do banco de dados e fica mais rápido
    * */
    @Transactional(readOnly = true)
    Cliente findByEmail(String email);
}
