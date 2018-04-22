package br.com.cursomc.resources;

import br.com.cursomc.domain.Pedido;
import br.com.cursomc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController //Significa que a classe será um controlador Rest
@RequestMapping(value = "/pedidos") //Nome do endpoint Rest
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pedido> find(@PathVariable Integer id) {
        Pedido obj = pedidoService.find(id);
        return ResponseEntity.ok().body(obj);
    }
}
