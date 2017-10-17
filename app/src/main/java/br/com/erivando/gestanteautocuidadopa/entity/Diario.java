package br.com.erivando.gestanteautocuidadopa.entity;

import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 14 de Outubro de 2017 as 17:47h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class Diario implements Serializable {
    private static final long serialVersionUID=4260711945094777831L;

    private int Id;
    private String Data;
    private String pas;
    private String pad;
    private List historico;

    public Diario() {
    }

    public Diario(int id, String data, String pas, String pad) {
        Id = id;
        Data = data;
        this.pas = pas;
        this.pad = pad;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = pas;
    }

    public String getPad() {
        return pad;
    }

    public void setPad(String pad) {
        this.pad = pad;
    }

    public List getHistorico() {
        return historico;
    }

    public void setHistorico(List historico) {
        this.historico = historico;
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, Diario.class);
    }

}
