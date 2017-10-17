package br.com.erivando.gestanteautocuidadopa.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import br.com.erivando.gestanteautocuidadopa.R;
import br.com.erivando.gestanteautocuidadopa.activity.MainActivity;
import br.com.erivando.gestanteautocuidadopa.mvp.MainMVP;
import br.com.erivando.gestanteautocuidadopa.mvp.Presenter;
import br.com.erivando.gestanteautocuidadopa.util.ProcessaWebView;

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
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        fragmentManager = getFragmentManager();

        if (presenter == null)
            presenter = new Presenter(this);

//        webview = (WebView)rootView.findViewById(R.id.txt_apresentacao);
//        WebSettings settings = webview.getSettings();
//        settings.setJavaScriptEnabled(true);
//        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
//        final AlertDialog alertDialog = new AlertDialog.Builder(rootView.getContext()).create();
//        progressBar = ProgressDialog.show(rootView.getContext(), null, "Carregando...");
//        webview.setWebViewClient(new WebViewClient() {
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//            public void onPageFinished(WebView view, String url) {
//                if (progressBar.isShowing()) {
//                    progressBar.dismiss();
//                }
//            }
//            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//                alertDialog.setTitle("Error");
//                alertDialog.setMessage(description);
//                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        return;
//                    }
//                });
//                alertDialog.show();
//            }
//        });
//        String textoIntroducao = getResources().getString(R.string.texto_introducao);
//        String textoHtml = "<!DOCTYPE html><head>" +
//                "<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>" +
//                "</head><body>" +
//                "<p style='text-align: justify;'> %s </p>" +
//                "</body></html>";
//        webview.loadData(String.format(textoHtml, textoIntroducao), "text/html;charset=utf-8", "UTF-8");
//        webview.setBackgroundColor(0x00000000);

        TextView textoSaldacao = (TextView) rootView.findViewById(R.id.txt_saldacao);
        final String nomeGestante = presenter.getGestante().getNome();
        if (nomeGestante != null) {
            textoSaldacao.setText(textoSaldacao.getText().toString().replace("mamãe!", "\n" + nomeGestante.toUpperCase()));
            ((MainActivity)getActivity()).nomeGestanteToolbar(nomeGestante.toUpperCase());
        }

        String textoIntroducao = getResources().getString(R.string.texto_introducao);
        ProcessaWebView processaWebView = new ProcessaWebView(rootView.getContext());
        processaWebView.processaHtml((WebView)rootView.findViewById(R.id.txt_apresentacao), textoIntroducao);

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

    private String readTextFromResource(int resourceID)
    {
        InputStream raw = getResources().openRawResource(resourceID);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        int i;
        try
        {
            i = raw.read();
            while (i != -1)
            {
                stream.write(i);
                i = raw.read();
            }
            raw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return stream.toString();
    }
}
