package br.com.erivando.gestanteautocuidadopa.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.erivando.gestanteautocuidadopa.entity.Diario;
import br.com.erivando.gestanteautocuidadopa.helper.DadosCursor;
import br.com.erivando.gestanteautocuidadopa.helper.DadosHelper;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 14 de Outubro de 2017 as 19:53h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class DiarioDAO implements GenericDAO<Diario> {
    private SQLiteDatabase db;
    private DadosHelper helper;

    public DiarioDAO(Context context) {
        helper = DadosHelper.getInstance(context);
    }

    @Override
    public HashMap<Diario, Diario> selecionar() {
        return null;
    }

    @Override
    public Diario buscar(int id) {
        Diario diario = new Diario();
        String sql = "SELECT * FROM "+helper.TABELA_DIARIO+" WHERE Id = "+String.valueOf(id)+";";
        DadosCursor cursor = helper.retornaCursor(sql);
        if(cursor.moveToFirst()) {
            diario = getObjeto(cursor);
        }
        return diario;
    }

    @Override
    public List<Diario> buscarTodos() {
        List<Diario> lista = new ArrayList<>();
        String sql = "SELECT * FROM "+helper.TABELA_DIARIO+" ORDER BY Data DESC;";
        DadosCursor cursor = helper.retornaCursor(sql);
        if(cursor != null && cursor.getCount() > 0) {
            do {
                lista.add(getObjeto(cursor));
            } while (cursor.moveToNext());
        }
        return lista;
    }

    @Override
    public long inserir(Diario diario) throws Exception {
        long status = 0L;
        db = helper.getWritableDatabase();
        try {
            status = db.insert(helper.TABELA_DIARIO, null, getValues(diario));
        } finally {
        }
        return status;
    }

    @Override
    public int atualizar(Diario diario) throws Exception {
        int status = 0;
        db = helper.getWritableDatabase();
        try {
            status = db.update(helper.TABELA_DIARIO, getValues(diario), " Id = ?;", new String[]{String.valueOf(diario.getId())});
        } finally {
        }
        return status;
    }

    @Override
    public int excluir(Diario diario) throws Exception {
        int status = 0;
        db = helper.getWritableDatabase();
        try {
            status = db.delete(helper.TABELA_DIARIO, " Id = ?;", new String[]{String.valueOf(diario.getId())});
        } finally {
        }
        return status;
    }

    @Override
    public ContentValues getValues(Diario diario) {
        ContentValues values = new ContentValues();
        values.put("Sistolica", diario.getPas());
        values.put("Diastolica", diario.getPad());
        values.put("Data", diario.getData());
        return values;
    }

    @Override
    public Diario getObjeto(DadosCursor cursor) {
        Diario diario = new Diario();
        diario.setId(cursor.getInt(cursor.getColumnIndexOrThrow("Id")));
        diario.setPas(cursor.getString(cursor.getColumnIndexOrThrow("Sistolica")));
        diario.setPad(cursor.getString(cursor.getColumnIndexOrThrow("Diastolica")));
        diario.setData(cursor.getString(cursor.getColumnIndexOrThrow("Data")));
        return diario;
    }
}
