package br.com.cursomc.services;

import br.com.cursomc.domain.Categoria;
import br.com.cursomc.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired // Injeção de dependência
    private CategoriaRepository categoriaRepository;

    public Categoria buscar(Integer id) {
        // A partir da versão 2.x.x do Spring, o método findById substitui o método findOne
        // A finalidade de uso classe Optional é para null, quando o resultado não é encontrado
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElse(null);
    }
}