package br.com.erivando.gestanteautocuidadopa.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 07 de Novembro de 2017 as 15:46h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class CalculoGestacional {

    public static String getDpp(String dum) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
        String dpp = null;
        try {
            Date dataDum = simpleDateFormat.parse(dum);
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(dataDum);
            calendario.add(calendario.DAY_OF_MONTH, +7);
            int mes = calendario.get(Calendar.MONTH);
            if (mes >= 1 || mes <= 3)
                calendario.add(calendario.MONTH, +9);
            else
                calendario.add(calendario.MONTH, -3);
            dpp = simpleDateFormat.format(calendario.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dpp;
    }

    public static int getSemanas(String dataDum) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
        int semanas = 0;
        try {
            Date dataInicio = simpleDateFormat.parse(dataDum);
            Calendar calInicio = Calendar.getInstance();
            calInicio.setTime(dataInicio);
            Calendar calFim = Calendar.getInstance();
            calInicio.set(Calendar.HOUR_OF_DAY, 0);
            calInicio.set(Calendar.MINUTE, 0);
            calInicio.set(Calendar.SECOND, 0);
            int inicio = (int) TimeUnit.MILLISECONDS.toDays(calInicio.getTimeInMillis()) - calInicio.get(Calendar.DAY_OF_WEEK);
            int fim = (int)TimeUnit.MILLISECONDS.toDays(calFim.getTimeInMillis());
            semanas = (inicio - fim) / 7;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return semanas;
    }

    public static String getInfoGestacao(String dataDum) {
        int quantSemanas = 0;
        int quantDias = 0;
        int quantMeses = 0;
        String gestacao = null;

        quantSemanas = getSemanas(dataDum);
        quantDias = quantSemanas * 7;
        quantMeses = quantSemanas / 4;

        gestacao = String.valueOf(
                "<font size=\"50%\" color=\"#5d353b\">" +
                        "Data provável do parto: </font>" +
                        "<br /><font size=\"40%\" color=\"#bb8484\"><b>" + getDpp(dataDum) + "</b></font>" +
                        "<br /><font size=\"50%\" color=\"#5d353b\">Idade gestacional: </font>" +
                        "<br /><font size=\"40%\" color=\"#bb8484\"><b>" + quantSemanas + " Semanas, " + String.valueOf(quantDias) + " dias, "+ quantMeses + "º Mês</b></font>");
        return gestacao;
    }

}
