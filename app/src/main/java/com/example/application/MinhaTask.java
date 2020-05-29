package com.example.application;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MinhaTask extends AsyncTask<Object, Object, String> {

    private ProgressBar progressBar;
    private TextView texto;
    private Activity activity;
    private  ListView l ;
    TrocarTela troca = new TrocarTela();
    private Class<?> cls = null;
    private int total = 0;
    private static int PROGRESSO = 10; // Incremento da barra

    // Construtor para as telas de Ocorrencias novas e Resolvidas
    public MinhaTask(Activity context, ProgressBar progressBar, TextView texto, ListView l) {
        this.progressBar = progressBar;
        this.texto = texto;
        this.l = l;
        this.activity = context;
          }

   // Construtor para a Tela de Sair
    public MinhaTask(Activity context, ProgressBar progressBar, TextView texto,Class<?> cls) {
        this.progressBar = progressBar;
        this.texto = texto;
        this.l =null;
        this.activity = context;
        this.cls = cls;}


    // Construtor para a Tela de Login
    public MinhaTask(Activity context, ProgressBar progressBar, TextView texto) {
        this.progressBar = progressBar;
        this.texto = texto;
        this.l =null;
        this.activity = context;
        }

    @Override
    // estado inicial
    protected void onPreExecute() {
        texto.setText("0%");
        if(l!= null){
        l.setVisibility(View.INVISIBLE);}
        super.onPreExecute();
    }

    @SuppressLint("WrongThread")
    @Override
    // carregamento
    protected String doInBackground(Object... params) {
        try {

           for (int i=0;i<10;i++){
               Thread.sleep(100);
                publishProgress();
           }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    // Publish Progress chamado dentro do for
    protected void onProgressUpdate(Object... values) {
        total += PROGRESSO;
        progressBar.incrementProgressBy(PROGRESSO);
        texto.setText(total + "%");
        super.onProgressUpdate(values);
    }

    @Override
    // Depois que termina
    protected void onPostExecute(String result) {
        progressBar.setVisibility(ProgressBar.INVISIBLE);
        texto.setText("Tarefa concluída");
        texto.setGravity(Gravity.CENTER_HORIZONTAL);
        texto.setVisibility(texto.INVISIBLE);
        if(l == null) { // Se for nulo é a tela inicial ou a de sair

            if(cls!= null){  // tela de sair
                activity.finish();
            }else // Tela inicial
                troca.trocar(activity, Login_Activity.class);


        }else      // É a tela das Ocorrencias Novas ou a de Ocorrencias Resolvidas
            l.setVisibility(View.VISIBLE);
        super.onPostExecute(result);}

}