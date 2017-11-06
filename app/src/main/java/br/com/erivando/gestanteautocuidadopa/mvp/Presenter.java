package br.com.erivando.gestanteautocuidadopa.mvp;

import java.util.ArrayList;
import java.util.List;

import br.com.erivando.gestanteautocuidadopa.dao.AlbumDAO;
import br.com.erivando.gestanteautocuidadopa.dao.DiarioDAO;
import br.com.erivando.gestanteautocuidadopa.dao.GestanteDAO;
import br.com.erivando.gestanteautocuidadopa.entity.Album;
import br.com.erivando.gestanteautocuidadopa.entity.Diario;
import br.com.erivando.gestanteautocuidadopa.entity.Gestante;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 04 de Outubro de 2017 as 12:41h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class Presenter implements MainMVP.presenter {
    private MainMVP.view view;
    private GestanteDAO gestanteDAO;
    private DiarioDAO diarioDAO;
    private AlbumDAO albumDAO;
    public Gestante gestante;

    public Presenter(MainMVP.view view) {
        this.view = view;
        gestanteDAO = new GestanteDAO(view.getContext());
        diarioDAO = new DiarioDAO(view.getContext());
        albumDAO = new AlbumDAO(view.getContext());
        gestante = new Gestante();
    }


    @Override
    public long cadastrarGestante(String nome, String menstruacao, String ultrasom, int semanas) {
        long codGestante = 0L;
        try {
            Gestante gestante = new Gestante();
            gestante.setNome(nome);
            gestante.setMenstruacao(menstruacao);
            gestante.setUltrassom(ultrasom);
            gestante.setSemanas(String.valueOf(semanas));

            codGestante = gestanteDAO.inserir(gestante);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return codGestante;
    }

    @Override
    public Gestante getGestante(int id) {
        Gestante dadosGestante = new Gestante();
        try {
            dadosGestante = gestanteDAO.buscar(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dadosGestante;
    }

    @Override
    public int atualizar(Gestante gestante) {
        int codGestante = 0;
        try {
            codGestante = gestanteDAO.atualizar(gestante);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codGestante;
    }

    @Override
    public ArrayList<Gestante> getGestantes() {
        ArrayList<Gestante> dadosGestante = new ArrayList<>();
        try {
            dadosGestante = gestanteDAO.buscarTodos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dadosGestante;
    }

    @Override
    public long cadastrarDiario(String siatolica, String diastolica, String dataHora) {
        long codDiario = 0L;
        try {
            Diario diario = new Diario();
            diario.setPas(siatolica);
            diario.setPad(diastolica);
            diario.setData(dataHora);

            codDiario = diarioDAO.inserir(diario);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return codDiario;
    }

    @Override
    public ArrayList<Diario> getDiarios() {
        ArrayList<Diario> dadosDiario = new ArrayList<>();
        try {
            dadosDiario = diarioDAO.buscarTodos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dadosDiario;
    }

    @Override
    public long cadastrarAlbum(String foto, String descricao) {
        long codAlbum = 0L;
        try {
            Album album = new Album();
            album.setFoto(foto);
            album.setDescricao(descricao);

            codAlbum = albumDAO.inserir(album);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return codAlbum;
    }

    @Override
    public List<Album> getAlbuns() {
        List<Album> dadosAlbum = new ArrayList<>();
        try {
            dadosAlbum = albumDAO.buscarTodos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dadosAlbum;
    }
}
