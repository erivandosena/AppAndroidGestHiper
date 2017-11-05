package br.com.erivando.gestanteautocuidadopa.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.GsonBuilder;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 14 de Outubro de 2017 as 17:47h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class Diario implements Parcelable {

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Diario> CREATOR = new Parcelable.Creator<Diario>() {
        @Override
        public Diario createFromParcel(Parcel in) {
            return new Diario(in);
        }

        @Override
        public Diario[] newArray(int size) {
            return new Diario[size];
        }
    };
    private int id;
    private String data;
    private String pas;
    private String pad;

    public Diario() {
    }

    public Diario(int id, String data, String pas, String pad) {
        this.id = id;
        this.data = data;
        this.pas = pas;
        this.pad = pad;
    }

    protected Diario(Parcel in) {
        id = in.readInt();
        data = in.readString();
        pas = in.readString();
        pad = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, Diario.class);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(data);
        dest.writeString(pas);
        dest.writeString(pad);
    }
}