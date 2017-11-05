package br.com.erivando.gestanteautocuidadopa.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

import static br.com.erivando.gestanteautocuidadopa.util.Utilitarios.removeCaracteres;

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
    private EditText ultrassom;
    private EditText semanas;
    private long status;
    private int idGestante;

    public CadastroFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_cadastro, container, false);

        presenter = new Presenter(this);

        nome = rootView.findViewById(R.id.txt_nome);

        menstruacao = rootView.findViewById(R.id.txt_data_menstruacao);
        menstruacao.addTextChangedListener(new MascaraWatcher("##/##/####"));

        ultrassom = rootView.findViewById(R.id.txt_data_ultrasom);
        ultrassom.addTextChangedListener(new MascaraWatcher("##/##/####"));

        semanas = rootView.findViewById(R.id.txt_numero_semanas);

        ImageButton btAnteriorMain = rootView.findViewById(R.id.bt_ant_main);
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

        ImageButton btProximoMenu = rootView.findViewById(R.id.bt_prox_menu);
        btProximoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validacao_nome = false;
                boolean validacao_data1 = false;
                boolean validacao_data2 = false;

                validacao_nome = Validador.validaNotNull(nome, getResources().getString(R.string.texto_cadastro_valida_nome));

                if (menstruacao.getText().toString().length() > 0) {
                    validacao_data1 = Validador.validaCampoIncompleto(menstruacao, getResources().getString(R.string.texto_cadastro_valida_menstruacao));
                    if (validacao_data1)
                        validacao_data1 = Validador.validaData(menstruacao, getResources().getString(R.string.texto_cadastro_valida_data));
                    if (!validacao_data1)
                        menstruacao.setText(null);
                }

                if (ultrassom.getText().toString().length() > 0) {
                    validacao_data2 = Validador.validaCampoIncompleto(ultrassom, getResources().getString(R.string.texto_cadastro_valida_ultrassom));
                    if (validacao_data2)
                        validacao_data2 = Validador.validaData(ultrassom, getResources().getString(R.string.texto_cadastro_valida_data));
                    if (!validacao_data2)
                        ultrassom.setText(null);
                }

                if (validacao_nome) {
                    if (semanas.getText().toString().length() == 0)
                        semanas.setText("0");
                    //long status = 0L;
                    status = presenter.cadastrarGestante(nome.getText().toString(), menstruacao.getText().toString(), ultrassom.getText().toString(), Integer.valueOf(semanas.getText().toString()));
                    if (status == 1) {

                        Snackbar.make(v, getResources().getString(R.string.texto_sucesso_cadastro), Snackbar.LENGTH_LONG).show();
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
                        Snackbar.make(v, getResources().getString(R.string.texto_erro_cadastro), Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (semanas.getText().toString().length() == 0)
                    semanas.setText("0");
                presenter.gestante = presenter.getGestante(presenter.getGestantes().get(0).getId());
                presenter.gestante.setNome(nome.getText().toString());
                presenter.gestante.setMenstruacao( menstruacao.getText().toString());
                presenter.gestante.setUltrassom(ultrassom.getText().toString());
                presenter.gestante.setSemanas(semanas.getText().toString());
                int status = 0;
                status = presenter.atualizar(presenter.gestante);
                if (status > 0) {
                    Snackbar.make(view, getResources().getString(R.string.texto_sucesso_cadastro), Snackbar.LENGTH_LONG).show();
                }
            }
        });

        status = presenter.getGestantes().size();
        if(status > 0) {
            presenter.gestante = presenter.getGestante(presenter.getGestantes().get(0).getId());

            idGestante = presenter.gestante.getId();
            String objNome = presenter.gestante.getNome();
            String objMenstruacao = presenter.gestante.getMenstruacao();
            String objUltrassom = presenter.gestante.getUltrassom();
            String objSemanas = presenter.gestante.getSemanas();

            if(objNome != null)
                nome.setText(objNome);
            if(objMenstruacao != null && objMenstruacao.length() >= 8)
                menstruacao.setText(removeCaracteres(objMenstruacao));
            if(objUltrassom != null && objUltrassom.length() >= 8)
                ultrassom.setText(removeCaracteres(objUltrassom));
            if(objSemanas != null && objSemanas.length() > 0)
                semanas.setText(objSemanas);
        }

        if(status == 0) {
            fab.hide();
            if (!btAnteriorMain.isShown() && !btProximoMenu.isShown()) {
                btProximoMenu.setVisibility(View.VISIBLE);
            }
        } else {
            if (!fab.isShown())
                fab.show();
            btProximoMenu.setVisibility(View.INVISIBLE);
        }
        return rootView;
    }

}
