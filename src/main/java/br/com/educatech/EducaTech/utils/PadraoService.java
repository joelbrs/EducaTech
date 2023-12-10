package br.com.educatech.EducaTech.utils;

import java.util.List;

/**
 * Interface para padronizar os métodos das Camadas de Serviços de cada Entidade do sistema,
 * recebendo os tipos de Entrada e Saída que serão utilizados como parâmetros e/ou retornos dos métodos
 * */
public interface PadraoService<Entrada, Saida>  {
    /**
     * Método que busca todos os dados (sem filtros)
     * */
    List<Saida> buscarTodos();

    /**
     * Metodo que edita a Entidade através de seu identificador único
     * */
    Saida editar(Long id, Entrada dto) throws Exception;

    /**
     * Método que insere/cria uma Entidade de acordo com seus atributos
     * */
    Saida inserir(Entrada dto) throws Exception;

    /**
     * Método que deleta uma Entidade a partir de seu identificador único
     * */
    void delete(Long id) throws Exception;
}
