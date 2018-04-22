package br.com.cursomc.resources;

import br.com.cursomc.domain.Cliente;
import br.com.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController //Significa que a classe será um controlador Rest
@RequestMapping(value = "/clientes") //Nome do endpoint Rest
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Integer id) {
        Cliente obj = clienteService.find(id);
        return ResponseEntity.ok().body(obj);
    }
}
