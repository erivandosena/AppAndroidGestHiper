package br.com.erivando.gestanteautocuidadopa;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;

/**
 * Classe SplashScreenActivity
 *
 * @author Erivando Sena
 * @version 1.0
 * @since Release 01 do aplicativo
 * Created by erivando on 28/12/2016.
 */

public class SplashScreenActivity extends Activity implements Runnable {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (android.os.Build.VERSION.SDK_INT > 15) {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        habilitaImmersiveMode();
        Handler handler=new Handler();
        handler.postDelayed(this, 3000);
    }

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
