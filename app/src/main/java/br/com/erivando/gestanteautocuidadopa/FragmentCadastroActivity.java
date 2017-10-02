package br.com.erivando.gestanteautocuidadopa;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Projeto: GestanteAutocuidadoPA
 * Criado por Erivando Sena
 * Data/Hora: 30 de Setembro de 2017 as 13:01h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class FragmentCadastroActivity extends Fragment {

    public FragmentCadastroActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_cadastro, container, false);

        ImageButton btAnteriorMain = (ImageButton) rootView.findViewById(R.id.bt_ant_main);
        btAnteriorMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Fragment fragment = null;
                Class fragmentClass = FragmentMainActivity.class;
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

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Fragment fragment = null;
                Class fragmentClass = FragmentMenuActivity.class;
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
