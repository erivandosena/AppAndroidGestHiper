package br.com.erivando.gestanteautocuidadopa.mvp;

import java.util.ArrayList;
import java.util.List;

import br.com.erivando.gestanteautocuidadopa.dao.DiarioDAO;
import br.com.erivando.gestanteautocuidadopa.dao.GestanteDAO;
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
    private final MainMVP.view view;
    private GestanteDAO gestanteDAO;
    private DiarioDAO diarioDAO;

    public Presenter(MainMVP.view view) {
        this.view = view;
        gestanteDAO = new GestanteDAO(view.getContext());
        diarioDAO = new DiarioDAO(view.getContext());
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

    public long cadastrarDiario(String siatolica, String diastolica, String dataHora) {
        long codDiario = 0L;
        try{
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

    public Diario getDiario(int id) {
        Diario dadosDiario = new Diario();
        try{
            dadosDiario = diarioDAO.buscar(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dadosDiario;
    }

    public List getDiarios() {
        List<Diario> dadosDiario = new ArrayList<>();
        try{
            dadosDiario = diarioDAO.buscarTodos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dadosDiario;
    }

}
