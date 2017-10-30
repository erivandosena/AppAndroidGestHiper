package br.com.erivando.gestanteautocuidadopa.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import br.com.erivando.gestanteautocuidadopa.R;
import br.com.erivando.gestanteautocuidadopa.activity.MainActivity;
import br.com.erivando.gestanteautocuidadopa.mvp.MainMVP;
import br.com.erivando.gestanteautocuidadopa.mvp.Presenter;
import br.com.erivando.gestanteautocuidadopa.util.MascaraWatcher;
import br.com.erivando.gestanteautocuidadopa.util.Validador;

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
                boolean validacao_nome = false;
                boolean validacao_data1 = false;
                boolean validacao_data2 = false;

                validacao_nome = Validador.validaNotNull(nome, "Informe seu nome");

                if (menstruacao.getText().toString().length() > 0) {
                    validacao_data1 = Validador.validaCampoIncompleto(menstruacao, "Informe data completa de sua última menstruação");
                    if (validacao_data1)
                        validacao_data1 = Validador.validaData(menstruacao, "Informe uma data válida!");
                    if (!validacao_data1)
                        menstruacao.setText(null);
                }

                if (ultrasom.getText().toString().length() > 0) {
                    validacao_data2 = Validador.validaCampoIncompleto(ultrasom, "Informe data completa do primeiro exame de ultrassom");
                    if (validacao_data2)
                        validacao_data2 = Validador.validaData(ultrasom, "Informe uma data válida!");
                    if (!validacao_data2)
                        ultrasom.setText(null);
                }

                if (validacao_nome) {
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

}
