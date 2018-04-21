package br.com.cursomc;

import br.com.cursomc.domain.Categoria;
import br.com.cursomc.domain.Cidade;
import br.com.cursomc.domain.Estado;
import br.com.cursomc.domain.Produto;
import br.com.cursomc.repositories.CategoriaRepository;
import br.com.cursomc.repositories.CidadeRepository;
import br.com.cursomc.repositories.EstadoRepository;
import br.com.cursomc.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CursoMcApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
    ProdutoRepository produtoRepository;

	@Autowired
    CidadeRepository cidadeRepository;

	@Autowired
    EstadoRepository estadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursoMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p1.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p1.getCategorias().addAll(Arrays.asList(cat1));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

        Estado e1 = new Estado(null, "Minas Gerais");
        Estado e2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlândia", e1);
        Cidade c2 = new Cidade(null, "São Paulo", e2);
        Cidade c3 = new Cidade(null, "Campinas", e2);

        e1.getCidades().addAll(Arrays.asList(c1));
        e2.getCidades().addAll(Arrays.asList(c2, c3));

        estadoRepository.saveAll(Arrays.asList(e1, e2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
    }
}
