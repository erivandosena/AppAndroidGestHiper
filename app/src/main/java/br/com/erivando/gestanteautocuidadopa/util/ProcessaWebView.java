package br.com.erivando.gestanteautocuidadopa.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import br.com.erivando.gestanteautocuidadopa.R;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 17 de Outubro de 2017 as 00:12h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class ProcessaWebView {

    //private WebView webview;
    private ProgressDialog progressBar;
    private final Context contexto;

    public ProcessaWebView(Context context) {
        this.contexto = context;
    }

    public void processaHtml(WebView webview, String textoConteudo) {
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        final AlertDialog alertDialog = new AlertDialog.Builder(contexto).create();
        progressBar = ProgressDialog.show(contexto, null, "Carregando...");
        webview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            public void onPageFinished(WebView view, String url) {
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                alertDialog.setTitle(view.getResources().getString(R.string.texto_html_processamento));
                alertDialog.setMessage(description);
                alertDialog.setButton(view.getResources().getString(R.string.texto_html_botao), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                alertDialog.show();
            }
        });
        String textoHtml = "<!DOCTYPE html><head>" +
                "<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>" +
                "</head><body>" +
                "<p style='text-align: justify;'> %s </p>" +
                "</body></html>";
        webview.loadData(String.format(textoHtml, textoConteudo), "text/html;charset=utf-8", "UTF-8");
        webview.setBackgroundColor(Color.TRANSPARENT);
        if (Build.VERSION.SDK_INT <= 15){
            webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

}
