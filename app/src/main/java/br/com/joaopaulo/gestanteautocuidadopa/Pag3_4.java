package br.com.joaopaulo.gestanteautocuidadopa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Pag3_4 extends AppCompatActivity {

    private EditText edittext342;
    private EditText edittext343;
    private Button button341;
    private TextView textview346;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pag3_4);

        edittext342 = (EditText) findViewById(R.id.editText342);
        edittext343 = (EditText) findViewById(R.id.editText343);
        button341 = (Button) findViewById(R.id.button341);
        textview346 = (TextView) findViewById(R.id.textView346);
        button341.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                int pas = Integer.parseInt(edittext342.getText().toString());
                int pad = Integer.parseInt(edittext343.getText().toString());

                if ((pas>180)||(pad>110))textview346.setText("Alerta! Você está com a pressão arterial extremamente elevada!");
                else if (((pas>160)&&(pas<=180))||((pad>100)&&(pad<=110)))textview346.setText("Alerta! Você está com a pressão arterial muito acima dos valores indicados.");
                else if (((pas>140)&&(pas<=160))||((pad>90)&&(pad<=100)))textview346.setText("Alerta! Você está com a pressão arterial acima do normal.");
                else if (((pas>120)&&(pas<=140))||((pad>80)&&(pad<=90)))textview346.setText("Alerta! Sua pressão arterial está acima dos níveis indicados neste momento.");
                else textview346.setText("Parabéns! Você está normotensa neste momento, ou seja, sua pressão está dentro dos valores normais e esperados.");
            }
        });

    }

    /** Chamar página 3 quando clicar no botão */
    public void voltarPag(View view) {
        Intent intent = new Intent(this, Pag3.class);
        startActivity(intent);
    }

}
