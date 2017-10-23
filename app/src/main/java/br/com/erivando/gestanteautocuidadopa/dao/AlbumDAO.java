package br.com.erivando.gestanteautocuidadopa.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.List;

import br.com.erivando.gestanteautocuidadopa.entity.Album;
import br.com.erivando.gestanteautocuidadopa.helper.DadosCursor;
import br.com.erivando.gestanteautocuidadopa.helper.DadosHelper;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 23 de Outubro de 2017 as 06:33h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class AlbumDAO implements GenericDAO<Album> {
    private SQLiteDatabase db;
    private DadosHelper helper;

    public AlbumDAO(Context context) {
        helper = new DadosHelper(context);
    }

    @Override
    public HashMap<Album, Album> selecionar() {
        return null;
    }

    @Override
    public Album buscar(int id) {
        return null;
    }

    @Override
    public List<Album> buscarTodos() {
        return null;
    }

    @Override
    public long inserir(Album album) throws Exception {
        return 0;
    }

    @Override
    public int atualizar(Album album) throws Exception {
        return 0;
    }

    @Override
    public int excluir(Album album) throws Exception {
        return 0;
    }

    @Override
    public ContentValues getValues(Album album) {
        return null;
    }

    @Override
    public Album getObjeto(DadosCursor cursor) {
        return null;
    }
}
