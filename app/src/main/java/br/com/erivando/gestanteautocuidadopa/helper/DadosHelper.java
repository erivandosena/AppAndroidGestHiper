package br.com.erivando.gestanteautocuidadopa.helper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 04 de Outubro de 2017 as 10:16h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class DadosHelper extends SQLiteOpenHelper {

    private static final String NOME_BD = "gestante_pa";
    private static final int VERSAO_BD = 1;
    public static final String TABELA_GESTANTE = "Gestante";
    public static final String TABELA_DIARIO = "Diario";
    public static final String TABELA_ALBUM = "Album";
    private final Context contexto;

    public DadosHelper(Context context) {
        super(context, NOME_BD, null, VERSAO_BD);
        this.contexto = context;
    }

    /**
     * Executa todos os comandos SQL passados no vetor String[]
     *
     * @param db  A base de dados onde os comandos serão executados
     * @param sql Um vetor de comandos SQL a serem executados
     */
    private void ExecutarComandosSQL(SQLiteDatabase db, String[] sql) {
        for (String s : sql)
            if (s.trim().length() > 0) {
                db.execSQL(s);
            }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTabGestante = "CREATE TABLE "+TABELA_GESTANTE+" (Id INTEGER PRIMARY KEY AUTOINCREMENT, Nome TEXT, Menstruacao TEXT, Ultrasom TEXT, Semanas TEXT);";
        String sqlTabDiario = "CREATE TABLE "+TABELA_DIARIO+" (Id INTEGER PRIMARY KEY AUTOINCREMENT, Sistolica TEXT, Diastolica TEXT, Data TEXT);";
        String sqlTabAlbum = "CREATE TABLE "+TABELA_ALBUM+" (Id INTEGER PRIMARY KEY AUTOINCREMENT, Foto BLOB, Descricao TEXT);";
        db.beginTransaction();
        try {
            ExecutarComandosSQL(db, new String[]{sqlTabGestante});
            ExecutarComandosSQL(db, new String[]{sqlTabDiario});
            ExecutarComandosSQL(db, new String[]{sqlTabAlbum});
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlTabGestante = "DROP TABLE IF EXISTS "+TABELA_GESTANTE+";";
        String sqlTabDiario = "DROP TABLE IF EXISTS "+TABELA_DIARIO+";";
        String sqlTabAlbum = "DROP TABLE IF EXISTS "+TABELA_ALBUM+";";
        db.beginTransaction();
        try {
            ExecutarComandosSQL(db, new String[]{sqlTabGestante});
            ExecutarComandosSQL(db, new String[]{sqlTabDiario});
            ExecutarComandosSQL(db, new String[]{sqlTabAlbum});
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
        onCreate(db);
    }

    public DadosCursor retornaCursor(String sql) {
        DadosCursor dCursor = null;
        SQLiteDatabase bd = getReadableDatabase();
        try {
            bd = getReadableDatabase();
            dCursor = (DadosCursor) bd.rawQueryWithFactory(new DadosFactory(), sql, null, null);
            dCursor.moveToFirst();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.close();
        }
        return dCursor;
    }
}
