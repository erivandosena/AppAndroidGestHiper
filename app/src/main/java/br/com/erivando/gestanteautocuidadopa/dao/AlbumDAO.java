package br.com.erivando.gestanteautocuidadopa.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
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
        helper = DadosHelper.getInstance(context);
    }

    @Override
    public HashMap<Album, Album> selecionar() {
        return null;
    }

    @Override
    public Album buscar(int id) {
        Album album = new Album();
        String sql = "SELECT * FROM " + DadosHelper.TABELA_ALBUM + " WHERE Id = " + String.valueOf(id) + ";";
        DadosCursor cursor = helper.retornaCursor(sql);
        if (cursor.moveToFirst()) {
            album = getObjeto(cursor);
        }
        return album;
    }

    @Override
    public List<Album> buscarTodos() {
        List<Album> lista = new ArrayList<Album>();
        String sql = "SELECT * FROM " + DadosHelper.TABELA_ALBUM + " ORDER BY Id DESC;";
        DadosCursor cursor = helper.retornaCursor(sql);
        if (cursor != null && cursor.getCount() > 0) {
            do {
                lista.add(getObjeto(cursor));
            } while (cursor.moveToNext());
        }
        return lista;
    }

    @Override
    public long inserir(Album album) throws Exception {
        long status = 0L;
        db = helper.getWritableDatabase();
        try {
            status = db.insert(DadosHelper.TABELA_ALBUM, null, getValues(album));
        } finally {
        }
        return status;
    }

    @Override
    public int atualizar(Album album) throws Exception {
        int status = 0;
        db = helper.getWritableDatabase();
        try {
            status = db.update(DadosHelper.TABELA_ALBUM, getValues(album), " Id = ?;", new String[]{String.valueOf(album.getId())});
        } finally {
        }
        return status;
    }

    @Override
    public int excluir(Album album) throws Exception {
        int status = 0;
        db = helper.getWritableDatabase();
        try {
            status = db.delete(DadosHelper.TABELA_ALBUM, " Id = ?;", new String[]{String.valueOf(album.getId())});
        } finally {
        }
        return status;
    }

    @Override
    public ContentValues getValues(Album album) {
        ContentValues values = new ContentValues();
        values.put("Foto", album.getFoto());
        values.put("Descricao", album.getDescricao());
        return values;
    }

    @Override
    public Album getObjeto(DadosCursor cursor) {
        Album album = new Album();
        album.setId(cursor.getInt(cursor.getColumnIndexOrThrow("Id")));
        album.setFoto(cursor.getString(cursor.getColumnIndexOrThrow("Foto")));
        album.setDescricao(cursor.getString(cursor.getColumnIndexOrThrow("Descricao")));
        return album;
    }
}
