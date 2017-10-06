package br.com.erivando.gestanteautocuidadopa.entity;

import com.google.gson.GsonBuilder;

import java.io.Serializable;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 04 de Outubro de 2017 as 12:57h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class Gestante implements Serializable {
    private static final long serialVersionUID=4260711945094777831L;

    private int Id;
    private String Nome;
    private String Menstruacao;
    private String Ultrasom;
    private int Semanas;

    public Gestante() {
    }

    public Gestante(int id, String nome, String menstruacao, String ultrasom, int semanas) {
        this.Id = id;
        this.Nome = nome;
        this.Menstruacao = menstruacao;
        this.Ultrasom = ultrasom;
        this.Semanas = semanas;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getMenstruacao() {
        return Menstruacao;
    }

    public void setMenstruacao(String menstruacao) {
        Menstruacao = menstruacao;
    }

    public String getUltrasom() {
        return Ultrasom;
    }

    public void setUltrasom(String ultrasom) {
        Ultrasom = ultrasom;
    }

    public int getSemanas() {
        return Semanas;
    }

    public void setSemanas(int semanas) {
        Semanas = semanas;
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, Gestante.class);
    }
}
