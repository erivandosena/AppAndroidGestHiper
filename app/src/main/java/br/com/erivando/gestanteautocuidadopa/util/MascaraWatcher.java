package br.com.erivando.gestanteautocuidadopa.util;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 09 de Outubro de 2017 as 23:11h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class MascaraWatcher implements TextWatcher {
    private final String mask;
    private boolean isRunning = false;
    private boolean isDeleting = false;

    public MascaraWatcher(String mask) {
        this.mask = mask;
    }

    public static MascaraWatcher buildData() {
        return new MascaraWatcher("##/##/####");
    }

    public static MascaraWatcher buildCpf() {
        return new MascaraWatcher("###.###.###-##");
    }

    public static MascaraWatcher buildCep() {
        return new MascaraWatcher("#####-###");
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        isDeleting = count > after;
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (isRunning || isDeleting) {
            return;
        }
        isRunning = true;

        int editableLength = editable.length();
        if (editableLength < mask.length()) {
            if (mask.charAt(editableLength) != '#') {
                editable.append(mask.charAt(editableLength));
            } else if (mask.charAt(editableLength - 1) != '#') {
                editable.insert(editableLength - 1, mask, editableLength - 1, editableLength);
            }
        }

        isRunning = false;
    }
}
