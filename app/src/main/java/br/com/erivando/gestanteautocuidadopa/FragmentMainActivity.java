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
 * Data/Hora: 30 de Setembro de 2017 as 13:58h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class FragmentMainActivity extends Fragment {

    public FragmentMainActivity() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_main, container, false);
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        ImageButton btProximoCadastro = (ImageButton) rootView.findViewById(R.id.bt_prox_cad);
        btProximoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Fragment fragment = null;
                Class fragmentClass = FragmentCadastroActivity.class;
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
