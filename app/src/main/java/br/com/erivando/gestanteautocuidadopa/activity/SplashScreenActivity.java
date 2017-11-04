package br.com.erivando.gestanteautocuidadopa.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.widget.TextView;

import br.com.erivando.gestanteautocuidadopa.R;

import static br.com.erivando.gestanteautocuidadopa.util.Utilitarios.habilitaImmersiveMode;

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

        habilitaImmersiveMode(this);

        Handler handler = new Handler();
        handler.postDelayed(this, 5*1000);

        TextView versionTextView = (TextView) findViewById(R.id.texto_versao_app);
        versionTextView.setText(getResources().getString(R.string.texto_versao_app)+getApplicationVersionName());
    }

    @Override
    public void run() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    /**
     * Obter programaticamente o némro atual da versão.
     * @return String versão
     */
    private String getApplicationVersionName() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            return packageInfo.versionName;
        } catch(Exception ignored){}
        return "";
    }

}
