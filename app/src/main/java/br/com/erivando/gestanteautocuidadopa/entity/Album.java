package br.com.erivando.gestanteautocuidadopa.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.GsonBuilder;

/*
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 23 de Outubro de 2017 as 06:27h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

/**
 * Implements Parcelable
 * http://www.parcelabler.com/
 */
public class Album implements Parcelable {
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Album> CREATOR = new Parcelable.Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };
    private int id;
    private String foto;
    private String descricao;

    public Album() {
    }

    public Album(int id, String foto, String descricao) {
        this.id = id;
        this.foto = foto;
        this.descricao = descricao;
    }

    protected Album(Parcel in) {
        id = in.readInt();
        foto = in.readString();
        descricao = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, Album.class);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(foto);
        dest.writeString(descricao);
    }
}
