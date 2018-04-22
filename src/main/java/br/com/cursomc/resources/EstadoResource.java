package br.com.cursomc.resources;

import br.com.cursomc.domain.Estado;
import br.com.cursomc.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController //Significa que a classe será um controlador Rest
@RequestMapping(value = "/estados") //Nome do endpoint Rest
public class EstadoResource {

    @Autowired
    private EstadoService estadoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Estado> find(@PathVariable Integer id) {
        Estado obj = estadoService.find(id);
        return ResponseEntity.ok().body(obj);
    }
}
