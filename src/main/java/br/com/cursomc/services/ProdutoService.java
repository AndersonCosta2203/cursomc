package br.com.cursomc.services;

import br.com.cursomc.domain.Produto;
import br.com.cursomc.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired // Injeção de dependência
    private ProdutoRepository produtoRepository;

    public Produto buscar(Integer id) {
        // A partir da versão 2.x.x do Spring, o método findById substitui o método findOne
        // A finalidade de uso classe Optional é para null, quando o resultado não é encontrado
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElse(null);
    }
}
