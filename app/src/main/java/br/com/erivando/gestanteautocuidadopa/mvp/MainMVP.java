package br.com.erivando.gestanteautocuidadopa.mvp;

import android.content.Context;

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
    }
}
