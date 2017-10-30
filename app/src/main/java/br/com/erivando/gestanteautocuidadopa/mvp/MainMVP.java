package br.com.erivando.gestanteautocuidadopa.mvp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import java.util.List;

import br.com.erivando.gestanteautocuidadopa.entity.Album;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 04 de Outubro de 2017 as 12:33h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public interface MainMVP {
    interface view {
        Context getContext();
    }

    interface presenter {
        //void setView(View view);
        List<Album> getAlbuns();
        //void onActivityResult(int requestCode, int resultCode, Intent data);
        //void selecionarFotoAlbum();
        //void startActivityForResult(android.content.Intent intent, int requestCode);
        //String getRealPathFromURI(Uri contentUri);
    }
}
