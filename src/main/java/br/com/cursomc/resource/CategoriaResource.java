package br.com.cursomc.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController //Significa que a classe será um controlador Rest
@RequestMapping(value = "/categorias") //Nome do endpoint Rest
public class CategoriaResource {

    @RequestMapping(method = RequestMethod.GET)
    public String listar() {
        return "REST está funcionando";
    }
}
