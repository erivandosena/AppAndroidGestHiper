package br.com.joaopaulo.gestanteautocuidadopa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Pag1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pag1);
    }

    /** Ir para página quando clicar no botão */
    public void irPag(View view) {
        Intent intent = new Intent(this, Pag2.class);
        startActivity(intent);
    }

}
