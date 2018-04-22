package br.com.cursomc.services;

import br.com.cursomc.domain.Estado;
import br.com.cursomc.repositories.EstadoRepository;
import br.com.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstadoService {

    @Autowired // Injeção de dependência
    private EstadoRepository estadoRepository;

    public Estado find(Integer id) {
        // A partir da versão 2.x.x do Spring, o método findById substitui o método findOne
        // A finalidade de uso classe Optional é para null, quando o resultado não é encontrado
        Optional<Estado> obj = estadoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: "+id+", Tipo: "+Estado.class.getName()));
    }
}
