package br.com.erivando.gestanteautocuidadopa.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.erivando.gestanteautocuidadopa.R;
import br.com.erivando.gestanteautocuidadopa.activity.MainActivity;
import br.com.erivando.gestanteautocuidadopa.mvp.MainMVP;
import br.com.erivando.gestanteautocuidadopa.mvp.Presenter;
import br.com.erivando.gestanteautocuidadopa.util.ProcessaWebView;

import static br.com.erivando.gestanteautocuidadopa.util.CalculoGestacional.getInfoGestacao;

/**
 * Projeto: GestanteAutocuidadoPA
 * Criado por Erivando Sena
 * Data/Hora: 30 de Setembro de 2017 as 13:58h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class MainFragment extends Fragment implements MainMVP.view {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    private Class fragmentClass;
    private Presenter presenter;
    private String nomeGestante;
    private String dppGestante;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        fragmentManager = getFragmentManager();

        presenter = new Presenter(this);

        String textoIntroducao = getResources().getString(R.string.texto_introducao);
        ProcessaWebView processaWebView = new ProcessaWebView(rootView.getContext());
        processaWebView.processaHtml((WebView) rootView.findViewById(R.id.txt_apresentacao), textoIntroducao);

        TextView textoSaldacao = (TextView) rootView.findViewById(R.id.txt_saldacao);
        if (presenter.getGestantes().size() > 0) {
            nomeGestante = presenter.getGestantes().get(0).getNome();
            if (nomeGestante != null && nomeGestante.length() > 1) {
                textoSaldacao.setText(textoSaldacao.getText().toString().replace(getResources().getString(R.string.texto_saldacao_nome), " " + nomeGestante.toUpperCase()));
                ((MainActivity) getActivity()).nomeGestanteToolbar(nomeGestante.toUpperCase());
            }
            dppGestante = presenter.getGestantes().get(0).getMenstruacao();
            if (dppGestante != null && dppGestante.length() == 10) {
                ((MainActivity) getActivity()).dppGestanteToolbar(getInfoGestacao(dppGestante));
            }
        }

        ImageButton btProximoCadastro = rootView.findViewById(R.id.bt_prox_cad);
        btProximoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentTransaction = fragmentManager.beginTransaction();
                if (nomeGestante == null) {
                    fragmentClass = CadastroFragment.class;
                } else {
                    fragmentClass = MenuFragment.class;
                }
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentTransaction.replace(R.id.flContent, fragment);
                fragmentTransaction.commit();
            }
        });

        return rootView;
    }

}
