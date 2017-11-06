package br.com.erivando.gestanteautocuidadopa.mvp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.erivando.gestanteautocuidadopa.entity.Album;
import br.com.erivando.gestanteautocuidadopa.entity.Diario;
import br.com.erivando.gestanteautocuidadopa.entity.Gestante;

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
        long cadastrarGestante(String nome, String menstruacao, String ultrasom, int semanas);
        Gestante getGestante(int id);
        int atualizar(Gestante gestante);
        ArrayList<Gestante> getGestantes();
        long cadastrarDiario(String siatolica, String diastolica, String dataHora);
        ArrayList<Diario> getDiarios();
        long cadastrarAlbum(String foto, String descricao);
        List<Album> getAlbuns();
    }
}
