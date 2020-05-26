package com.example.application;

import android.os.Parcel;
import android.os.Parcelable;


public class Fato implements Parcelable {
    private int id_ocorrencia;
    private String Titulo;
    private String Descricao;
    private String Data_Criacao;
    private String Soluçao;
    private String Data_Solucao;
    private String Autor;

    public Fato(String Titulo, String Descricao,String Autor) {
        this.Titulo = Titulo;
        this.Descricao = Descricao;
        this.Autor = Autor;
    }

    protected Fato(Parcel in) {
        id_ocorrencia = in.readInt();
        Autor = in.readString();
        Titulo = in.readString();
        Descricao = in.readString();
        Soluçao = in.readString();
        Data_Criacao = in.readString();
        Data_Solucao = in.readString();
    }

    public String toString() {
        return  "\nTitulo: " + Titulo  +"        "+
                "\n\nAutor:  " + Autor +
                "\n\nData Criação: " + Data_Criacao+ "\n"
                ;
    }

    public String toStrings() {
        return "Fato{" +
                "id=" + id_ocorrencia +
                ", autor='" + Autor + '\'' +
                ", Titulo='" + Titulo + '\'' +
                ", descricao='" + Descricao + '\'' +
                ", resoluçao='" + Soluçao + '\'' +
                ", Data_Criacao='" + Data_Criacao + '\'' +
                ", Data_Solucao='" + Data_Solucao + '\'' +
                '}';
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_ocorrencia);
        dest.writeString(Autor);
        dest.writeString(Titulo);
        dest.writeString(Descricao);
        dest.writeString(Soluçao);
        dest.writeString(Data_Criacao);
        dest.writeString(Data_Solucao);
    }

    public int getId() {
        return id_ocorrencia;
    }

    public void setId(int id) {
        this.id_ocorrencia = id;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        this.Autor = autor;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        this.Descricao = descricao;
    }

    public String getResoluçao() {
        return Soluçao;
    }

    public void setResoluçao(String resoluçao) {
        this.Soluçao = resoluçao;
    }

    public String getDatacriacao() {
        return Data_Criacao;
    }

    public void setDatacriacao(String datacriacao) {
        this.Data_Criacao = datacriacao;
    }

    public String getDataresolucao() {
        return Data_Solucao;
    }

    public void setDataresolucao(String dataresolucao) {
        this.Data_Solucao = dataresolucao;
    }
}