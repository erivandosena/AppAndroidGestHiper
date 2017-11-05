package br.com.erivando.gestanteautocuidadopa.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.GsonBuilder;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 04 de Outubro de 2017 as 12:57h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class Gestante implements Parcelable {

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Gestante> CREATOR = new Parcelable.Creator<Gestante>() {
        @Override
        public Gestante createFromParcel(Parcel in) {
            return new Gestante(in);
        }

        @Override
        public Gestante[] newArray(int size) {
            return new Gestante[size];
        }
    };
    private int id;
    private String nome;
    private String menstruacao;
    private String ultrassom;
    private String semanas;

    public Gestante() {
    }

    public Gestante(String nome, String menstruacao, String ultrassom, String semanas) {
        this.nome = nome;
        this.menstruacao = menstruacao;
        this.ultrassom = ultrassom;
        this.semanas = semanas;
    }

    protected Gestante(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        menstruacao = in.readString();
        ultrassom = in.readString();
        semanas = in.readString();
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, Gestante.class);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMenstruacao() {
        return menstruacao;
    }

    public void setMenstruacao(String menstruacao) {
        this.menstruacao = menstruacao;
    }

    public String getUltrassom() {
        return ultrassom;
    }

    public void setUltrassom(String ultrassom) {
        this.ultrassom = ultrassom;
    }

    public String getSemanas() {
        return semanas;
    }

    public void setSemanas(String semanas) {
        this.semanas = semanas;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
        dest.writeString(menstruacao);
        dest.writeString(ultrassom);
    }
}