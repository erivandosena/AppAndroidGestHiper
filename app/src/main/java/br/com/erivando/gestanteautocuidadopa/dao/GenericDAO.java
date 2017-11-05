package br.com.erivando.gestanteautocuidadopa.dao;

import android.content.ContentValues;

import java.util.HashMap;
import java.util.List;

import br.com.erivando.gestanteautocuidadopa.helper.DadosCursor;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 04 de Outubro de 2017 as 21:21h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public interface GenericDAO<T> {

    HashMap<T, T> selecionar();

    T buscar(int id);

    List<T> buscarTodos();

    long inserir(T t) throws Exception;

    int atualizar(T t) throws Exception;

    int excluir(T t) throws Exception;

    ContentValues getValues(T t);

    T getObjeto(DadosCursor cursor);
}
