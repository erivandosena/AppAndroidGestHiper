package br.com.erivando.gestanteautocuidadopa.util;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 10 de Outubro de 2017 as 09:13h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class Validador {
    public static boolean validaNotNull(View pView, String pMessage) {
        if (pView instanceof EditText) {
            EditText edText = (EditText) pView;
            Editable text = edText.getText();
            if (text != null) {
                String strText = text.toString();
                if (!TextUtils.isEmpty(strText)) {
                    return true;
                }
            }
            /* em qualquer outra condição é gerado um erro */
            edText.setError(pMessage);
            edText.setFocusable(true);
            edText.requestFocus();
            return false;
        }
        return false;
    }

    public static boolean validaCampoIncompleto(View pView, String pMessage) {
        if (pView instanceof EditText) {
            EditText edText = (EditText) pView;
            Editable text = edText.getText();
            if (text != null) {
                String strText = text.toString();
                if (strText.length() == 10) {
                    return true;
                }
            }
            edText.setError(pMessage);
            edText.setFocusable(true);
            edText.requestFocus();
            return false;
        }
        return false;
    }

    public static boolean validaValorPA(View pView, String pMessage, String pTipo) {
        if (pView instanceof EditText) {
            EditText edText = (EditText) pView;
            Editable text = edText.getText();
            if (text != null) {
                String strText = text.toString();
                if ("sistolica".equals(pTipo)) {
                    if (strText.length() == 3) {
                        return true;
                    }
                } else if ("distolica".equals(pTipo)) {
                    if (strText.length() == 2) {
                        return true;
                    }
                }
            }
            edText.setError(pMessage);
            edText.setFocusable(true);
            edText.requestFocus();
            return false;
        }
        return false;
    }

    public static boolean validaCPF(String CPF) {
        CPF = Mascara.unmask(CPF);
        if (CPF.equals("00000000000") || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")) {
            return false;
        }
        char dig10, dig11;
        int sm, i, r, num, peso;
        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = CPF.charAt(i) - 48;
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char) (r + 48);
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = CPF.charAt(i) - 48;
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char) (r + 48);
            return (dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10));
        } catch (Exception erro) {
            return (false);
        }
    }

    public static boolean validaData(View pView, String pMessage) {
        if (pView instanceof EditText) {
            EditText edText = (EditText) pView;
            Editable text = edText.getText();
            try {
                if (text != null) {
                    String strData = text.toString();
                    String pattern = "dd/MM/yyyy";
                    SimpleDateFormat sdf = new SimpleDateFormat(pattern, new Locale("pt", "BR"));
                    sdf.setLenient(false);
                    Date date = sdf.parse(strData);
                }
            } catch (Exception e) {
                e.printStackTrace();
                edText.setError(pMessage);
                edText.setFocusable(true);
                edText.requestFocus();
                return false;
            }
        }
        return true;
    }

    public static boolean validaEmail(String txtEmail) {
        return !TextUtils.isEmpty(txtEmail) && android.util.Patterns.EMAIL_ADDRESS.matcher(txtEmail).matches();
    }
}
