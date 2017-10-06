package br.com.erivando.gestanteautocuidadopa.dao;

import android.content.ContentValues;

import java.util.HashMap;
import java.util.List;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 04 de Outubro de 2017 as 21:21h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public interface GenericDAO<T> {

    public HashMap<T, T> selecionar();

    public T buscar(int id);

    public List<T> buscarTodos();

    public long inserir(T t) throws Exception;

    public int atualizar(T t) throws Exception;

    public int excluir(T t) throws Exception;

    public ContentValues getValues(T t);
}
