package br.com.erivando.gestanteautocuidadopa.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.erivando.gestanteautocuidadopa.R;
import br.com.erivando.gestanteautocuidadopa.activity.MainActivity;
import br.com.erivando.gestanteautocuidadopa.mvp.MainMVP;
import br.com.erivando.gestanteautocuidadopa.mvp.Presenter;

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

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        fragmentManager = getFragmentManager();

        if (presenter == null)
            presenter = new Presenter(this);

        TextView textSaldacao = (TextView) rootView.findViewById(R.id.txt_saldacao);
        final String nomeGestante = presenter.getGestante().getNome();

        if (nomeGestante != null) {
            textSaldacao.setText(textSaldacao.getText().toString().replace("mamãe!", "\n" + nomeGestante.toUpperCase()));
            ((MainActivity)getActivity()).nomeGestanteToolbar(nomeGestante.toUpperCase());
        }

        ImageButton btProximoCadastro = (ImageButton) rootView.findViewById(R.id.bt_prox_cad);
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
