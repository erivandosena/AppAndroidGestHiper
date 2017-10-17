package br.com.erivando.gestanteautocuidadopa.helper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
        String sql = "CREATE TABLE "+TABELA_GESTANTE+" (Id INTEGER PRIMARY KEY AUTOINCREMENT, Nome TEXT, Menstruacao TEXT, Ultrasom TEXT, Semanas TEXT);";
        sql.concat("\n");
        sql.concat("CREATE TABLE "+TABELA_DIARIO+" (Id INTEGER PRIMARY KEY AUTOINCREMENT, Data TEXT, Sistolica TEXT, Diastolica TEXT);");
        db.beginTransaction();
        try {
            ExecutarComandosSQL(db, new String[]{sql});
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "+TABELA_GESTANTE+";";
        sql.concat("\n");
        sql.concat("DROP TABLE IF EXISTS "+TABELA_DIARIO+";");
        db.beginTransaction();
        try {
            ExecutarComandosSQL(db, new String[]{sql});
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
