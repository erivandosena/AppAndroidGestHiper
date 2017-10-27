package br.com.erivando.gestanteautocuidadopa.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.erivando.gestanteautocuidadopa.entity.Gestante;
import br.com.erivando.gestanteautocuidadopa.helper.DadosCursor;
import br.com.erivando.gestanteautocuidadopa.helper.DadosHelper;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 04 de Outubro de 2017 as 23:01h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class GestanteDAO implements GenericDAO<Gestante> {
    private SQLiteDatabase db;
    private DadosHelper helper;

    public GestanteDAO(Context context) {
        helper = new DadosHelper(context);
    }

    @Override
    public HashMap<Gestante, Gestante> selecionar() {
        return null;
    }

    /**
     * Busca unico objeto por Id.
     * @param id O id do objeto.
     * @return Gestante O objeto buscado.
     */
    @Override
    public Gestante buscar(int id) {
        Gestante gestante = new Gestante();
        String sql = "SELECT * FROM "+helper.TABELA_GESTANTE+" WHERE Id = "+String.valueOf(id)+";";
        DadosCursor cursor = helper.retornaCursor(sql);
        if(cursor.moveToFirst()) {
//            gestante.setId(cursor.getInt(cursor.getColumnIndexOrThrow("Id")));
//            gestante.setNome(cursor.getString(cursor.getColumnIndexOrThrow("Nome")));
//            gestante.setMenstruacao(cursor.getString(cursor.getColumnIndexOrThrow("Menstruacao")));
//            gestante.setUltrasom(cursor.getString(cursor.getColumnIndexOrThrow("Ultrasom")));
//            gestante.setSemanas(cursor.getInt(cursor.getColumnIndexOrThrow("Semanas")));
            gestante = getObjeto(cursor);
        }
        return gestante;
    }

    /**
     * Busca todos os objetos
     * @return List A lista dos objetos.
     */
    @Override
    public List<Gestante> buscarTodos() {
        List<Gestante> listaGestante = new ArrayList<>();
        String sql = "SELECT * FROM "+helper.TABELA_GESTANTE+";";
        DadosCursor cursor = helper.retornaCursor(sql);
        if(cursor.getCount() > 0) {
            do {
//                Gestante gestante = new Gestante();
//                gestante.setId(cursor.getInt(cursor.getColumnIndexOrThrow("Id")));
//                gestante.setNome(cursor.getString(cursor.getColumnIndexOrThrow("Nome")));
//                gestante.setMenstruacao(cursor.getString(cursor.getColumnIndexOrThrow("Menstruacao")));
//                gestante.setUltrasom(cursor.getString(cursor.getColumnIndexOrThrow("Ultrasom")));
//                gestante.setSemanas(cursor.getInt(cursor.getColumnIndexOrThrow("Semanas")));
//                listaGestante.add(gestante);
                listaGestante.add(getObjeto(cursor));
            } while (cursor.moveToNext());
        }
        return listaGestante;
    }

    /**
     * Adiciona novo objeto.
     * @param gestante O objeto Gestante.
     * @return long O retorno do insert.
     * @throws Exception A exceção decorrente do processo.
     */
    @Override
    public long inserir(Gestante gestante) throws Exception {
        long status = 0L;
        db = helper.getWritableDatabase();
        try {
            status = db.insert(helper.TABELA_GESTANTE, null, getValues(gestante));
        } finally {
            db.close();
        }
        return status;
    }

    /**
     * Altera o objeto.
     * @param gestante O objeto Gestante.
     * @return int O retorno do update.
     * @throws Exception A exceção decorrente do processo.
     */
    @Override
    public int atualizar(Gestante gestante) throws Exception {
        int status = 0;
        db = helper.getWritableDatabase();
        try {
            status = db.update(helper.TABELA_GESTANTE, getValues(gestante), "Id = ?;", new String[]{String.valueOf(gestante.getId())});
        } finally {
            db.close();
        }
        return status;
    }

    /**
     * Deleta o objeto.
     * @param gestante O objeto Gestante.
     * @return int O retorno do update.
     * @throws Exception A exceção decorrente do processo.
     */
    @Override
    public int excluir(Gestante gestante) throws Exception {
        int status = 0;
        db = helper.getWritableDatabase();
        try {
            status = db.delete(helper.TABELA_GESTANTE, "Id = ?;", new String[]{String.valueOf(gestante.getId())});
        } finally {
            db.close();
        }
        return status;
    }

    /**
     * Valores dos objeto.
     * @param gestante O objeto Gestante.
     * @return ContentValues Os valores dos campos.
     */
    @Override
    public ContentValues getValues(Gestante gestante) {
        ContentValues values = new ContentValues();
        values.put("Nome", gestante.getNome());
        values.put("Menstruacao", gestante.getMenstruacao());
        values.put("Ultrasom", gestante.getUltrasom());
        values.put("Semanas", gestante.getSemanas());
        return values;
    }

    @Override
    public Gestante getObjeto(DadosCursor cursor) {
        Gestante gestante = new Gestante();
        gestante.setId(cursor.getInt(cursor.getColumnIndexOrThrow("Id")));
        gestante.setNome(cursor.getString(cursor.getColumnIndexOrThrow("Nome")));
        gestante.setMenstruacao(cursor.getString(cursor.getColumnIndexOrThrow("Menstruacao")));
        gestante.setUltrasom(cursor.getString(cursor.getColumnIndexOrThrow("Ultrasom")));
        gestante.setSemanas(cursor.getInt(cursor.getColumnIndexOrThrow("Semanas")));
        return gestante;
    }

}
