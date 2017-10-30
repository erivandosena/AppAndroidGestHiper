package br.com.erivando.gestanteautocuidadopa.util;

import android.content.Context;
import android.content.Intent;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 28 de Outubro de 2017 as 16:00h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public final class MediaUtil {

    private MediaUtil() {
    }

    public static Intent getPickImageIntent(final Context context) {
        final Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        return Intent.createChooser(intent, "Selecione");
    }
}
