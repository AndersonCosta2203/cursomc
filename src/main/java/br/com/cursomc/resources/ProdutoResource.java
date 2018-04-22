package br.com.cursomc.resources;

import br.com.cursomc.domain.Produto;
import br.com.cursomc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController //Significa que a classe será um controlador Rest
@RequestMapping(value = "/produtos") //Nome do endpoint Rest
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Produto obj = produtoService.find(id);
        return ResponseEntity.ok().body(obj);
    }
}
