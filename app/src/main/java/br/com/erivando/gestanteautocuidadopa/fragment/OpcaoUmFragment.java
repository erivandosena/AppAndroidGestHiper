package br.com.erivando.gestanteautocuidadopa.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.bluejamesbond.text.DocumentView;

import br.com.erivando.gestanteautocuidadopa.R;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 06 de Outubro de 2017 as 00:42h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class OpcaoUmFragment extends Fragment {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    private Class fragmentClass;
    private MediaPlayer mediaPlayer;

    public OpcaoUmFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_opcao_um, container, false);

        fragmentManager = getFragmentManager();

        mediaPlayer = MediaPlayer.create(rootView.getContext(), R.raw.ic_batimentos_coracao);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        DocumentView documentViewUm = (DocumentView) rootView.findViewById(R.id.txt_opcao_um);
        documentViewUm.setText(Html.fromHtml(String.valueOf(documentViewUm.getText())));

        DocumentView documentViewUmDois = (DocumentView) rootView.findViewById(R.id.txt_opcao_um_dois);
        documentViewUmDois.setText(Html.fromHtml(String.valueOf(documentViewUmDois.getText())));

        DocumentView documentViewUmTres = (DocumentView) rootView.findViewById(R.id.txt_opcao_um_tres);
        documentViewUmTres.setText(Html.fromHtml(String.valueOf(documentViewUmTres.getText())));

        ImageButton btAnteriorMenu = (ImageButton) rootView.findViewById(R.id.bt_ant_menu);
        btAnteriorMenu.setOnClickListener(new View.OnClickListener() {
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

        ImageButton btProximoOpcaoDois = (ImageButton) rootView.findViewById(R.id.bt_prox_opcao_dois);
        btProximoOpcaoDois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentClass = OpcaoDoisFragment.class;
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();

    }

}
