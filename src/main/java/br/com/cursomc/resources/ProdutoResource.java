package br.com.cursomc.resources;

import br.com.cursomc.domain.Produto;
import br.com.cursomc.dto.ProdutoDTO;
import br.com.cursomc.resources.utils.URL;
import br.com.cursomc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Significa que a classe será um controlador Rest
@RequestMapping(value = "/produtos") //Nome do endpoint Rest
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> find(@PathVariable Integer id) {
        Produto obj = produtoService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value = "produto", defaultValue = "pr") String produto,
            @RequestParam(value = "categorias", defaultValue = "0") String categorias,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ) {
        String nomeDecoded = URL.decodeParam(produto);
        List<Integer> ids = URL.decodeIntList(categorias);

        Page<Produto> list = produtoService.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
        Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj));
        return ResponseEntity.ok().body(listDTO);
    }
}
