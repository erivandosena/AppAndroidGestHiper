package br.com.erivando.gestanteautocuidadopa.mvp;

import java.util.ArrayList;
import java.util.List;

import br.com.erivando.gestanteautocuidadopa.dao.GestanteDAO;
import br.com.erivando.gestanteautocuidadopa.entity.Gestante;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 04 de Outubro de 2017 as 12:41h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class Presenter implements MainMVP.presenter {
    private final MainMVP.view view;
    private GestanteDAO gestanteDAO;

    public Presenter(MainMVP.view view) {
        this.view = view;
        gestanteDAO = new GestanteDAO(view.getContext());
    }

    public long cadastrarGestante(String nome, String menstruacao, String ultrasom, int semanas) {
        long codGestante = 0L;
        try{
            Gestante gestante = new Gestante();
            gestante.setNome(nome);
            gestante.setMenstruacao(menstruacao);
            gestante.setUltrasom(ultrasom);
            gestante.setSemanas(semanas);

            codGestante = gestanteDAO.inserir(gestante);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return codGestante;
    }

    public Gestante getGestante() {
        Gestante dadosGestante = new Gestante();
        try{
            dadosGestante = gestanteDAO.buscar(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dadosGestante;
    }

    public List getGestantes() {
        List<Gestante> dadosGestante = new ArrayList<>();
        try{
            dadosGestante = gestanteDAO.buscarTodos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dadosGestante;
    }


}
