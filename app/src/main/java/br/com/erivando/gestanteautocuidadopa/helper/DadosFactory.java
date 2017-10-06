package br.com.erivando.gestanteautocuidadopa.helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 04 de Outubro de 2017 as 13:36h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class DadosFactory implements SQLiteDatabase.CursorFactory {
    @Override
    public Cursor newCursor(SQLiteDatabase sqLiteDatabase, SQLiteCursorDriver sqLiteCursorDriver, String editTable, SQLiteQuery sqLiteQuery) {
        return new DadosCursor(sqLiteDatabase, sqLiteCursorDriver, editTable, sqLiteQuery);
    }
}
