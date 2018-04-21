package br.com.cursomc.services;

import br.com.cursomc.domain.Cidade;
import br.com.cursomc.repositories.CidadeRepository;
import br.com.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CidadeService {

    @Autowired // Injeção de dependência
    private CidadeRepository cidadeRepository;

    public Cidade buscar(Integer id) {
        // A partir da versão 2.x.x do Spring, o método findById substitui o método findOne
        // A finalidade de uso classe Optional é para null, quando o resultado não é encontrado
        Optional<Cidade> obj = cidadeRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: "+id+", Tipo: "+Cidade.class.getName()));
    }
}
