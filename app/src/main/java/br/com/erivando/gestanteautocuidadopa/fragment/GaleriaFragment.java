package br.com.erivando.gestanteautocuidadopa.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import br.com.erivando.gestanteautocuidadopa.R;
import br.com.erivando.gestanteautocuidadopa.util.SliderShowPagerAdapter;
import br.com.erivando.gestanteautocuidadopa.util.ViewPagerPagerAdapter;
import me.relex.circleindicator.CircleIndicator;

/**
 * Projeto: GestanteAutocuidadoPA
 * Criado por Erivando Sena
 * Data/Hora: 22 de Outubro de 2017 as 22:38h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class GaleriaFragment extends Fragment {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    private Class fragmentClass;


    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] XMEN= {R.drawable.ic_gestante_alimentacao,
            R.drawable.ic_pa_aparelho_digital,
            R.drawable.ic_gestante_exercicio,
            R.drawable.ic_obs_pa_profissional,
            R.drawable.ic_gestante_repouso,
            R.drawable.ic_medidor_pa
    };
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();

    public GaleriaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_galeria, container, false);

        fragmentManager = getFragmentManager();

//        ViewPagerPagerAdapter vpPagerAdapter = new ViewPagerPagerAdapter(rootView.getContext());
//        ViewPager mViewPager = (ViewPager) rootView.findViewById(R.id.pager);
//        mViewPager.setAdapter(vpPagerAdapter);


        for(int i=0;i<XMEN.length;i++)
            XMENArray.add(XMEN[i]);

        mPager = (ViewPager) rootView.findViewById(R.id.pager);
        mPager.setAdapter(new SliderShowPagerAdapter(rootView.getContext(),XMENArray));
        CircleIndicator indicator = (CircleIndicator) rootView.findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);


        ImageButton btAnteriorOpcaoOnze = (ImageButton) rootView.findViewById(R.id.bt_ant_opcao_onze);
        btAnteriorOpcaoOnze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentClass = OpcaoOnzeFragment.class;
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

        return rootView;
    }

}
