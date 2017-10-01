package br.com.joaopaulo.gestanteautocuidadopa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Pag2 extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "br.com.joaopaulo.gestanteautocuidadopa.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pag2);
    }

    /** Voltar página quando clicar no botão */
    public void voltarPag(View view) {
        Intent intent = new Intent(this, Pag1.class);
        startActivity(intent);
    }

    /** Ir para página e enviar dados quando clicar no botão */
    public void enviaMsg(View view) {
        Intent intent = new Intent(this, Pag3.class);
        EditText editText = (EditText) findViewById(R.id.editText21);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

}
