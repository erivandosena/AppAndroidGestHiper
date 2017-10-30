package br.com.erivando.gestanteautocuidadopa.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.erivando.gestanteautocuidadopa.R;
import br.com.erivando.gestanteautocuidadopa.adapter.PlanilhaAdapter;
import br.com.erivando.gestanteautocuidadopa.entity.Diario;
import br.com.erivando.gestanteautocuidadopa.mvp.MainMVP;
import br.com.erivando.gestanteautocuidadopa.mvp.Presenter;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 06 de Outubro de 2017 as 10:56h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class OpcaoOitoFragment extends Fragment implements MainMVP.view {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    private Class fragmentClass;
    private Presenter presenter;

    public OpcaoOitoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_opcao_oito, container, false);

        fragmentManager = getFragmentManager();

        if (presenter == null)
            presenter = new Presenter(this);

        try {
            ArrayList<Diario> listaDiario = (ArrayList<Diario>) presenter.getDiarios();
            ListView listViewPlanilha = (ListView) rootView.findViewById(R.id.lv_planilha_pa);
            listViewPlanilha.setAdapter(new PlanilhaAdapter(rootView.getContext(), listaDiario));
        } catch (Exception e) {
            e.fillInStackTrace();
        }

        Button btRegistrarAfericaoPa = (Button) rootView.findViewById(R.id.bt_registro_afericao_pa);
        btRegistrarAfericaoPa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentClass = AfericaoPAFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentTransaction.replace(R.id.flContent, fragment);
                fragmentTransaction.commit();
            }
        });

        ImageButton btAnteriorOpcaoSete = (ImageButton) rootView.findViewById(R.id.bt_ant_opcao_sete);
        btAnteriorOpcaoSete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentClass = OpcaoSeteFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentTransaction.replace(R.id.flContent, fragment);
                fragmentTransaction.commit();
            }
        });

        ImageButton btMenu = (ImageButton) rootView.findViewById(R.id.bt_menu);
        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentClass = MenuFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentTransaction.replace(R.id.flContent, fragment);
                fragmentTransaction.commit();
            }
        });

        ImageButton btProximoOpcaoNove = (ImageButton) rootView.findViewById(R.id.bt_prox_opcao_nove);
        btProximoOpcaoNove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentClass = OpcaoNoveFragment.class;
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
