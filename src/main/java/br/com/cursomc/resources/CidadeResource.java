package br.com.cursomc.resources;

import br.com.cursomc.domain.Cidade;
import br.com.cursomc.services.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController //Significa que a classe será um controlador Rest
@RequestMapping(value = "/cidades") //Nome do endpoint Rest
public class CidadeResource {

    @Autowired
    private CidadeService cidadeService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {

        Cidade obj = cidadeService.buscar(id);

        return ResponseEntity.ok().body(obj);
    }
}
