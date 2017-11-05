package br.com.erivando.gestanteautocuidadopa.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    private FragmentManager fragmentManager;
    private Fragment fragment;
    private Class fragmentClass;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_menu, container, false);

        fragmentManager = getFragmentManager();

        /* ######################### BOTOES DE NAVEGACAO ######################### */

        ImageButton btAnteriorMain = rootView.findViewById(R.id.bt_ant_main);
        btAnteriorMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentClass = MainFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            }
        });

        ImageButton btProximoOpcoes = rootView.findViewById(R.id.bt_prox_opcoes);
        btProximoOpcoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentClass = OpcaoUmFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            }
        });

        /* ########################### BOTOES DE OPCOES ########################## */

        Button btOpcaoUm = rootView.findViewById(R.id.bt_saber_mais);
        btOpcaoUm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentClass = OpcaoUmFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            }
        });

        Button btOpcaoDois = rootView.findViewById(R.id.bt_hipertensao_hipertensiva);
        btOpcaoDois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentClass = OpcaoDoisFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            }
        });

        Button btOpcaoTres = rootView.findViewById(R.id.bt_deve_saber);
        btOpcaoTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentClass = OpcaoTresFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            }
        });

        Button btOpcaoQuatro = rootView.findViewById(R.id.bt_como_se_preparar);
        btOpcaoQuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentClass = OpcaoQuatroFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            }
        });

        Button btOpcaoCinco = rootView.findViewById(R.id.bt_observar_afericao);
        btOpcaoCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentClass = OpcaoCincoFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            }
        });

        Button btOpcaoSeis = rootView.findViewById(R.id.bt_como_aferir_pressao);
        btOpcaoSeis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentClass = OpcaoSeisFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            }
        });

        Button btOpcaoSete = rootView.findViewById(R.id.bt_diario_pa);
        btOpcaoSete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentClass = OpcaoSeteFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            }
        });

        Button btOpcaoOito = rootView.findViewById(R.id.bt_planilha_pas);
        btOpcaoOito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentClass = OpcaoOitoFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            }
        });

        Button btOpcaoNove = rootView.findViewById(R.id.bt_exercicio_gravidez);
        btOpcaoNove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentClass = OpcaoNoveFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            }
        });

        Button btOpcaoDez = rootView.findViewById(R.id.bt_alimentacao_gravidez);
        btOpcaoDez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentClass = OpcaoDezFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            }
        });

        Button btOpcaoOnze = rootView.findViewById(R.id.bt_galeria_fotos);
        btOpcaoOnze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentClass = OpcaoOnzeFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            }
        });

        return rootView;
    }

}
