package br.com.erivando.gestanteautocuidadopa.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import br.com.erivando.gestanteautocuidadopa.R;
import br.com.erivando.gestanteautocuidadopa.activity.MainActivity;
import br.com.erivando.gestanteautocuidadopa.mvp.MainMVP;
import br.com.erivando.gestanteautocuidadopa.mvp.Presenter;
import br.com.erivando.gestanteautocuidadopa.util.MascaraWatcher;

/**
 * Projeto: GestanteAutocuidadoPA
 * Criado por Erivando Sena
 * Data/Hora: 30 de Setembro de 2017 as 13:01h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class CadastroFragment extends Fragment implements MainMVP.view {

    private Presenter presenter;

    private EditText nome;
    private EditText menstruacao;
    private EditText ultrasom;
    private EditText semanas;

    public CadastroFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_cadastro, container, false);

        presenter = new Presenter(this);

        nome = (EditText) rootView.findViewById(R.id.txt_nome);

        menstruacao = (EditText) rootView.findViewById(R.id.txt_data_menstruacao);

        menstruacao.addTextChangedListener(new MascaraWatcher("##/##/####"));

        ultrasom = (EditText) rootView.findViewById(R.id.txt_data_ultrasom);
        ultrasom.addTextChangedListener(new MascaraWatcher("##/##/####"));

        semanas = (EditText) rootView.findViewById(R.id.txt_numero_semanas);

        ImageButton btAnteriorMain = (ImageButton) rootView.findViewById(R.id.bt_ant_main);
        btAnteriorMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Fragment fragment = null;
                Class fragmentClass = MainFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                fragmentTransaction.replace(R.id.flContent, fragment);
                fragmentTransaction.commit();
            }
        });

        ImageButton btProximoMenu = (ImageButton) rootView.findViewById(R.id.bt_prox_menu);
        btProximoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nome.getText().length() < 4)
                    Toast.makeText(getActivity(), "Informe seu nome", Toast.LENGTH_SHORT).show();
                else {
//                    if (menstruacao.getText().toString().length() > 0)
//                        if (menstruacao.getText().length() < 10)
//                            Toast.makeText(getActivity(), "Informe a data de sua última menstruação", Toast.LENGTH_SHORT).show();
//                else
//                    if (ultrasom.getText().toString().length() > 0)
//                        if (ultrasom.getText().length() < 10)
//                            Toast.makeText(getActivity(), "Informe a data do primeiro exame de ultrasom", Toast.LENGTH_SHORT).show();
//                else {
                    if (semanas.getText().toString().length() == 0)
                        semanas.setText("0");

                    long status = 0L;
                    status = presenter.cadastrarGestante(nome.getText().toString(), menstruacao.getText().toString(), ultrasom.getText().toString(), Integer.valueOf(semanas.getText().toString()));
                    if (status == 1) {

                        Snackbar.make(v, "Cadastro finalizado!", Snackbar.LENGTH_LONG).show();
                        ((MainActivity) getActivity()).nomeGestanteToolbar(nome.getText().toString().toUpperCase());


                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        Fragment fragment = null;
                        Class fragmentClass = MenuFragment.class;
                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        fragmentTransaction.replace(R.id.flContent, fragment);
                        fragmentTransaction.commit();
                    } else {
                        Snackbar.make(v, "Problema ao salvar as informações!", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
        return rootView;
    }

    public static boolean validaNotNull(View pView, String pMessage) {
        if (pView instanceof EditText) {
            EditText edText = (EditText) pView;
            Editable text = edText.getText();
            if (text != null) {
                String strText = text.toString();
                if (!TextUtils.isEmpty(strText)) {
                    return true;
                }
            }
            // em qualquer outra condição é gerado um erro
            edText.setError(pMessage);
            edText.setFocusable(true);
            edText.requestFocus();
            return false;
        }
        return false;
    }
}
