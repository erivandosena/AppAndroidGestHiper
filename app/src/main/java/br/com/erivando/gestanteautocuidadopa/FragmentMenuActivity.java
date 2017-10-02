package br.com.erivando.gestanteautocuidadopa;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Projeto: GestanteAutocuidadoPA
 * Criado por Erivando Sena
 * Data/Hora: 01 de Outubro de 2017 as 12:30h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class FragmentMenuActivity extends Fragment {

    public FragmentMenuActivity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_menu, container, false);
        return  rootView;
    }

}
