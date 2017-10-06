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
    public static final String NOME_TABELA = "Gestante";
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
                Log.d("execSQL", s.toString());
                db.execSQL(s);
            }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+NOME_TABELA+" (Id INTEGER PRIMARY KEY AUTOINCREMENT, Nome TEXT, Menstruacao TEXT, Ultrasom TEXT, Semanas TEXT);";
        db.beginTransaction();
        try {
            ExecutarComandosSQL(db, new String[]{sql});
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.d("onCreate", e.toString());
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "+NOME_TABELA+";";
        db.beginTransaction();
        try {
            ExecutarComandosSQL(db, new String[]{sql});
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.d("onUpgrade", e.toString());
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
            Log.d("DadosCursor", e.toString());
            e.printStackTrace();
        } finally {
            bd.close();
        }
        return dCursor;
    }

//    public HashMap<String, String> selecionaGestante() {
//        HashMap<String, String> user = new HashMap<String, String>();
//        Cursor cursor = retornaCursor();
//        try {
////            for( int i=0; i < cursor.getCount(); i++)
////            {
////                cursor.moveToPosition(i);
////
////                user.put("Id", cursor.getString(1));
////                user.put("Nome", cursor.getString(2));
////                user.put("Menstruacao", cursor.getString(3));
////                user.put("Ultrasom", cursor.getString(4));
////                user.put("Semanas", cursor.getString(5));
////            }
//            if (cursor.getCount() > 0) {
//                user.put("Id", cursor.getString(1));
//                user.put("Nome", cursor.getString(2));
//                user.put("Menstruacao", cursor.getString(3));
//                user.put("Ultrasom", cursor.getString(4));
//                user.put("Semanas", cursor.getString(5));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            cursor.close();
//        }
//        return user;
//    }
//
//
//    public long insereGestante(String nome, String menstruacao, String ultrasom, String semanas) {
//        SQLiteDatabase db = getReadableDatabase();
//        long status = 0;
//        try {
//            ContentValues initialValues = new ContentValues();
//            initialValues.put("Nome", nome);
//            initialValues.put("Menstruacao", menstruacao);
//            initialValues.put("Ultrasom", ultrasom);
//            initialValues.put("Semanas", semanas);
//            status = db.insert("Gestante", null, initialValues);
//        } finally {
//            db.close();
//        }
//        return status;
//    }
//
//    public int atualizaGestante(String id, String nome, String menstruacao, String ultrasom, String semanas) {
//        SQLiteDatabase db = getReadableDatabase();
//        int status = 0;
//        try {
//            ContentValues initialValues = new ContentValues();
//            initialValues.put("Nome", nome);
//            initialValues.put("Menstruacao", menstruacao);
//            initialValues.put("Ultrasom", ultrasom);
//            initialValues.put("Semanas", semanas);
//            status = db.update("Gestante", initialValues, "uid = ?", new String[]{id});
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            db.close();
//        }
//        return status;
//    }
//
//    public int excluiGestante() {
//        SQLiteDatabase db = getReadableDatabase();
//        int status = 0;
//        try {
//            status = db.delete("Gestante", null, null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            db.close();
//        }
//        return status;
//    }

//    @Override
//    public HashMap<String, String> seleciona() {
//        HashMap<String, String> user = new HashMap<String, String>();
//        Cursor cursor = retornaCursor();
//        try {
//            if (cursor.getCount() > 0) {
//                user.put("Id", cursor.getString(1));
//                user.put("Nome", cursor.getString(2));
//                user.put("Menstruacao", cursor.getString(3));
//                user.put("Ultrasom", cursor.getString(4));
//                user.put("Semanas", cursor.getString(5));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            cursor.close();
//        }
//        return user;
//    }
//
//    @Override
//    public long insere(String nome, String menstruacao, String ultrasom, String semanas) {
//        SQLiteDatabase db = getReadableDatabase();
//        long status = 0;
//        try {
//            ContentValues initialValues = new ContentValues();
//            initialValues.put("Nome", nome);
//            initialValues.put("Menstruacao", menstruacao);
//            initialValues.put("Ultrasom", ultrasom);
//            initialValues.put("Semanas", semanas);
//            status = db.insert("Gestante", null, initialValues);
//        } finally {
//            db.close();
//        }
//        return status;
//    }
//
//    @Override
//    public int atualiza(String id, String nome, String menstruacao, String ultrasom, String semanas) {
//        SQLiteDatabase db = getReadableDatabase();
//        int status = 0;
//        try {
//            ContentValues initialValues = new ContentValues();
//            initialValues.put("Nome", nome);
//            initialValues.put("Menstruacao", menstruacao);
//            initialValues.put("Ultrasom", ultrasom);
//            initialValues.put("Semanas", semanas);
//            status = db.update("Gestante", initialValues, "uid = ?", new String[]{id});
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            db.close();
//        }
//        return status;
//    }
//
//    @Override
//    public int exclui() {
//        SQLiteDatabase db = getReadableDatabase();
//        int status = 0;
//        try {
//            status = db.delete("Gestante", null, null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            db.close();
//        }
//        return status;
//    }
}
