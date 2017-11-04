package br.com.erivando.gestanteautocuidadopa.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 01 de Novembro de 2017 as 21:29h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class Utilitarios {
    /**
     * Converte de Bitmap para String de Base64
     * @param bitmap Bitmap
     * @return String Base64
     */
    public static String bitmapParaBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    /**
     * Converte de String Base64 para Bitmap
     * @param b64 String Base64
     * @return Bitmap Imagem Batimap
     */
    public static Bitmap base64ParaBitmap(String b64) {
        Bitmap bitmap;
        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
        bitmap = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream);
        return bitmap;
    }

    /**
     * Verifica se o armazenamento externo está disponível para leitura e gravação
     * @return boolean true ou false
     */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * Converte o BitmapDrawable do ImageView para Bitmpap
     * @param imagemView A imagem do ImageView
     * @return Bitmap Imagem bitmap
     */
    public static Bitmap imageViewBitmap(ImageView imagemView) {
        Bitmap bitmap;
        if (imagemView.getDrawable() instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) imagemView.getDrawable()).getBitmap();
        } else {
            Drawable draw = imagemView.getDrawable();
            bitmap = Bitmap.createBitmap(draw.getIntrinsicWidth(), draw.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            draw.draw(canvas);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, baos);
        }
        return bitmap;
    }

    /**
     *  Habilita modo tela cheia
     * @param context Context o contexto da activity
     */
    public static void habilitaImmersiveMode(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ((Activity)context).getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }
}
