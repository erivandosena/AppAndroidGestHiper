package br.com.erivando.gestanteautocuidadopa.entity;

import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.BitSet;
import java.util.List;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 23 de Outubro de 2017 as 06:27h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class Album implements Serializable {
    private static final long serialVersionUID=4260711945094777831L;

    private int Id;
    private byte[] foto;
    private String descricao;
    private List fotos;

    public Album() {
    }

    public Album(int id, byte[] foto, String descricao) {
        Id = id;
        this.foto = foto;
        this.descricao = descricao;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List getFotos() {
        return fotos;
    }

    public void setFotos(List fotos) {
        this.fotos = fotos;
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, Album.class);
    }
}
