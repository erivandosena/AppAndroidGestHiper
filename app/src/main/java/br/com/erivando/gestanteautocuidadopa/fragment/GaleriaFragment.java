package br.com.erivando.gestanteautocuidadopa.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.erivando.gestanteautocuidadopa.R;

/**
 * Projeto: GestanteAutocuidadoPA
 * Criado por Erivando Sena
 * Data/Hora: 22 de Outubro de 2017 as 22:38h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class GaleriaFragment extends Fragment {

    public GaleriaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_opcao_nove, container, false);
        return rootView;
    }

}
