package br.com.erivando.gestanteautocuidadopa.util;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ImageSpan;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 15 de Outubro de 2017 as 01:26h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class TextoJustificado {
    public static void justifica(final TextView textView) {
        // A tag foi processada porque os retornos de chamada do borne continuam a disparar após o processamento
        final AtomicBoolean isJustify = new AtomicBoolean(false);

        // Pre-processando a corda original
        final String textString = textView.getText().toString();

        // Use para medir a largura do texto e calcular a largura dos espaços alinhados dispersos
        final TextPaint textPaint = textView.getPaint();
        CharSequence textViewText = textView.getText();

        // Distribuir texto alinhado
        final Spannable builder = textViewText instanceof Spannable ?
                (Spannable) textViewText :
                new SpannableString(textString);

        // Executar após TextView completa desenho de medição
        textView.post(new Runnable() {

            @Override
            public void run() {
                // Determine se ele foi manipulado
                if (!isJustify.get()) {

                    // Obter o número total de linhas de layout originais
                    final int lineCount = textView.getLineCount();

                    // Obter a largura do TextView
                    final int textViewWidth = textView.getWidth();

                    for (int i = 0; i < lineCount; i++) {
                        // Obtém a posição de caractere de linha e a posição de caractere de fim de linha para interceptar o texto de cada linha
                        int lineStart = textView.getLayout().getLineStart(i);
                        int lineEnd = textView.getLayout().getLineEnd(i);

                        String lineString = textString.substring(lineStart, lineEnd);

                        // Última linha não é processada
                        if (i == lineCount - 1) {
                            break;
                        }

                        // Remova o espaço no final da linha para garantir o efeito de alinhamento após o processamento
                        String trimSpaceText = lineString.trim();
                        String removeSpaceText = lineString.replaceAll(" ", "");
                        float removeSpaceWidth = textPaint.measureText(removeSpaceText);
                        float spaceCount = trimSpaceText.length() - removeSpaceText.length();

                        // A largura do recálculo de cada espaço quando justificada
                        float eachSpaceWidth = (textViewWidth - removeSpaceWidth) / spaceCount;

                        // Os espaços de dois lados precisam ser manuseados separadamente
                        Set<Integer> endsSpace = spacePositionInEnds(lineString);
                        for (int j = 0; j < lineString.length(); j++) {
                            char c = lineString.charAt(j);

                            // Use transparente Drawable para preencher a seção de espaço
                            Drawable drawable = new ColorDrawable(0x00ffffff);
                            if (c == ' ') {
                                if (endsSpace.contains(j)) {

                                    // Se é um espaço em ambas as extremidades, a largura é 0
                                    drawable.setBounds(0, 0, 0, 0);
                                } else {
                                    drawable.setBounds(0, 0, (int) eachSpaceWidth, 0);
                                }
                                ImageSpan span = new ImageSpan(drawable);
                                builder.setSpan(span, lineStart + j, lineStart + j + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            }

                        }

                    }
                    textView.setText(builder);

                    // Processamento de marca concluído
                    isJustify.set(true);
                }
            }
        });
    }

    /**
     * Retorna as coordenadas de espaço em ambas as extremidades, como a seqüência de caracteres
     * "ABC " (precedida por um espaço, seguido por dois espaços), retorna [0, 5, 6]
     */
    private static Set<Integer> spacePositionInEnds(String string) {
        Set<Integer> result = new HashSet<>();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == ' ') {
                result.add(i);
            } else {
                break;
            }
        }

        if (result.size() == string.length()) {
            return result;
        }

        for (int i = string.length() - 1; i > 0; i--) {
            char c = string.charAt(i);
            if (c == ' ') {
                result.add(i);
            } else {
                break;
            }
        }
        return result;
    }
}
