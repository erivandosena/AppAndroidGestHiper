package br.com.erivando.gestanteautocuidadopa.entity;

import android.os.Parcel;
import android.os.Parcelable;

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

/**
 * Implements Parcelable
 * http://www.parcelabler.com/
 */
public class Album implements Parcelable {
    private int Id;
    private String foto;
    private String descricao;
    private List<Album> fotos;

    public Album() {
    }

    public Album(String foto, String descricao) {
        this.foto = foto;
        this.descricao = descricao;
    }

    public Album(int id, String foto, String descricao) {
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

    public List<Album> getFotos() {
        return fotos;
    }

    public void setFotos(List<Album> fotos) {
        this.fotos = fotos;
    }

    protected Album(Parcel in) {
        Id = in.readInt();
        foto = in.readString();
        descricao = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(foto);
        dest.writeString(descricao);
    }

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
}
