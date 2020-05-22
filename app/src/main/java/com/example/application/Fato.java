package com.example.application;

import android.os.Parcel;
import android.os.Parcelable;

public class Fato implements Parcelable {
    private String autor;
    private String tituto;
    private String descricao;
    private String resoluçao;
    private String datacriacao;
    private String dataresolucao;

    public Fato(String autor, String tituto, String descricao, String datacriacao) {
        this.autor = autor;
        this.tituto = tituto;
        this.descricao = descricao;
        this.resoluçao = null;
        this.datacriacao = datacriacao;
        this.dataresolucao = null;
    }

    protected Fato(Parcel in) {
        autor = in.readString();
        tituto = in.readString();
        descricao = in.readString();
        resoluçao = in.readString();
        datacriacao = in.readString();
        dataresolucao = in.readString();
    }

    public static final Creator<Fato> CREATOR = new Creator<Fato>() {
        @Override
        public Fato createFromParcel(Parcel in) {
            return new Fato(in);
        }

        @Override
        public Fato[] newArray(int size) {
            return new Fato[size];
        }
    };

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTituto() {
        return tituto;
    }

    public void setTituto(String tituto) {
        this.tituto = tituto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getResoluçao() {
        return resoluçao;
    }

    public void setResoluçao(String resoluçao) {
        this.resoluçao = resoluçao;
    }

    public String getDatacriacao() {
        return datacriacao;
    }

    public void setDatacriacao(String datacriacao) {
        this.datacriacao = datacriacao;
    }

    public String getDataresolucao() {
        return dataresolucao;
    }

    public void setDataresolucao(String dataresolucao) {
        this.dataresolucao = dataresolucao;
    }


    public String toStrings() {
        return "Fato{" +
                "autor='" + autor + '\'' +
                ",\n tituto='" + tituto + '\'' +
                ", \ndescricao='" + descricao + '\'' +
                ", \nresoluçao='" + resoluçao + '\'' +
                ",\n datacriacao='" + datacriacao + '\'' +
                ",\n dataresolucao='" + dataresolucao + '\'' +
                '}';
    }

    public String toString() {
        return  "\nTituto: " + tituto  +"        "+
                "\n\nAutor:  " + autor +
                "\n\nData Criação: " + datacriacao+ "\n"
                ;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(autor);
        dest.writeString(tituto);
        dest.writeString(descricao);
        dest.writeString(resoluçao);
        dest.writeString(datacriacao);
        dest.writeString(dataresolucao);
    }
}