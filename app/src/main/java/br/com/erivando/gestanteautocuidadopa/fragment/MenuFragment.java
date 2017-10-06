package br.com.erivando.gestanteautocuidadopa.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import br.com.erivando.gestanteautocuidadopa.R;

/**
 * Projeto: GestanteAutocuidadoPA
 * Criado por Erivando Sena
 * Data/Hora: 01 de Outubro de 2017 as 12:30h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class MenuFragment extends Fragment {

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_menu, container, false);

        ImageButton btAnteriorMain = (ImageButton) rootView.findViewById(R.id.bt_ant_main);
        btAnteriorMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Fragment fragment = null;
                Class fragmentClass = null;

//                if (nomeGestante == null) {
//                    fragmentClass = CadastroFragment.class;
//                } else {
//                    fragmentClass = MenuFragment.class;
//                }

                try {
                    fragmentClass = MainFragment.class;
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                fragmentTransaction.replace(R.id.flContent, fragment);
                fragmentTransaction.commit();
            }
        });

        ImageButton btProximoOpcoes = (ImageButton) rootView.findViewById(R.id.bt_prox_opcoes);
        btProximoOpcoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Fragment fragment = null;
                Class fragmentClass = null;

//                if (nomeGestante == null) {
//                    fragmentClass = CadastroFragment.class;
//                } else {
//                    fragmentClass = MenuFragment.class;
//                }

                try {
                    fragmentClass = OpcaoUmFragment.class;
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                fragmentTransaction.replace(R.id.flContent, fragment);
                fragmentTransaction.commit();
            }
        });

        return  rootView;
    }

}
