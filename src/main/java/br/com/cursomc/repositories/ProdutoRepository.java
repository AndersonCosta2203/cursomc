package br.com.cursomc.repositories;

import br.com.cursomc.domain.Categoria;
import br.com.cursomc.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
   /*
    * Este nome: findDistinctByNomeContainingAndCategoriasIn substitui toda a Query
    * Se existir o @Query, sobrepõe o nome da consulta que realiza o select da mesma maneira
    */
    @Transactional(readOnly = true)
    @Query("SELECT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
    Page<Produto> findDistinctByNomeContainingAndCategoriasIn(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
}
