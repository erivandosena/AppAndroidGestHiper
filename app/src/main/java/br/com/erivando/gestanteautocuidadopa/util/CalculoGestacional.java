package br.com.erivando.gestanteautocuidadopa.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 07 de Novembro de 2017 as 15:46h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class CalculoGestacional {

    public static String calculaDpp(String dataDum) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
        int quantSemanas = 0;
        int quantDias = 0;
        String ddp = null;
        String idadeGestacional = null;
        try {
            Date dum = simpleDateFormat.parse(dataDum);
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(dum);
            quantSemanas = calendario.get(Calendar.WEEK_OF_YEAR);
            quantDias = calendario.get(Calendar.DAY_OF_YEAR);

            calendario.add(calendario.DAY_OF_MONTH, +7);
            int mes = calendario.get(Calendar.MONTH);
            if (mes >= 1 || mes <= 3)
                calendario.add(calendario.MONTH, +9);
            else
                calendario.add(calendario.MONTH, -3);
            ddp = simpleDateFormat.format(calendario.getTime());

            idadeGestacional = String.valueOf(
                    "<font size=\"50%\" color=\"#e129a7\">" +
                            "Data provável do parto: </font>" +
                            "<br /><font size=\"40%\" color=\"#FFFFFFFF\"><b>" + ddp + "</b></font>" +
                            "<br /><font size=\"50%\" color=\"#e129a7\">Idade gestacional: </font>" +
                            "<br /><font size=\"40%\" color=\"#FFFFFFFF\"><b>" + quantSemanas + " Semanas (" + String.valueOf(quantDias) + " dias)</b></font>");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return idadeGestacional;
    }

    public static int calculaSemanasDum(String dataDum) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
        int quantSemanas = 0;
        try {
            Date dum = simpleDateFormat.parse(dataDum);
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(dum);
            quantSemanas = calendario.get(Calendar.WEEK_OF_YEAR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quantSemanas;
    }

}
