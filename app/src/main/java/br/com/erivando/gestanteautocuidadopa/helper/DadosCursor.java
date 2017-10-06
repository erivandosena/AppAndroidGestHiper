package br.com.erivando.gestanteautocuidadopa.helper;

import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 04 de Outubro de 2017 as 13:25h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class DadosCursor extends SQLiteCursor {

    public DadosCursor(SQLiteDatabase db, SQLiteCursorDriver driver, String editTable, SQLiteQuery query) {
        super(db, driver, editTable, query);
    }

}
