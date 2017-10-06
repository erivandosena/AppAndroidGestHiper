package br.com.erivando.gestanteautocuidadopa.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;

import br.com.erivando.gestanteautocuidadopa.R;

/**
 * Projeto: GestanteAutocuidadoPA
 * Criado por Erivando Sena
 * Data/Hora: 25 de Setembro de 2017 as 21:50h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class SplashScreenActivity extends Activity implements Runnable {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (android.os.Build.VERSION.SDK_INT > 15) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        habilitaImmersiveMode();
        Handler handler = new Handler();
        handler.postDelayed(this, 5*1000);

//        Thread background = new Thread() {
//            public void run() {
//                try {
//                    sleep(5*1000);
//                    Intent i=new Intent(SplashScreenActivity.this, MainActivity.class);
//                    startActivity(i);
//                    finish();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        background.start();
    }

    @Override
    public void run() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void habilitaImmersiveMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }

}
