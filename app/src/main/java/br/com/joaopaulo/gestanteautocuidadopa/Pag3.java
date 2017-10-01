package br.com.joaopaulo.gestanteautocuidadopa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Pag3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pag3);

    Intent intent = getIntent();
    String message = intent.getStringExtra(Pag2.EXTRA_MESSAGE);
    TextView textView31 = new TextView(this);
    /**textView31.setTextSize(20);*/
        textView31.setText("Bem vinda " + message);

    ViewGroup layout = (ViewGroup) findViewById(R.id.activity_Pag3);
        layout.addView(textView31);
    }


    /** Chamar página quando clicar no botão */
    public void pag4_1(View view) {
        Intent intent = new Intent(this, Pag3_1.class);
        startActivity(intent);
    }
    public void pag4_2(View view) {
        Intent intent = new Intent(this, Pag3_2.class);
        startActivity(intent);
    }
    public void pag4_3(View view) {
        Intent intent = new Intent(this, Pag3_3.class);
        startActivity(intent);
    }
    public void pag4_4(View view) {
        Intent intent = new Intent(this, Pag3_4.class);
        startActivity(intent);
    }
    public void pag4_5(View view) {
        Intent intent = new Intent(this, Pag3_5.class);
        startActivity(intent);
    }
    public void pag4_6(View view) {
        Intent intent = new Intent(this, Pag3_6.class);
        startActivity(intent);
    }
    public void pag4_7(View view) {
        Intent intent = new Intent(this, Pag3_7.class);
        startActivity(intent);
    }

    /** Chamar página anterior quando clicar no botão */
    public void voltarPag(View view) {
        Intent intent = new Intent(this, Pag2.class);
        startActivity(intent);
    }

}
