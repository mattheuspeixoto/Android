package com.example.application;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

public class NovaOcorrencia extends AsyncTask<String, String, Void> {

    private String descricao, titulo, autor;
    TrocarTela troca = new TrocarTela();
    private Activity activity;

    public NovaOcorrencia(Activity activity,String descricao, String titulo,String autor) {
        this.descricao = descricao;
        this.titulo= titulo;
        this.activity = activity;
        this.autor = autor;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected Void doInBackground(String... strings) {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://192.168.100.8/api/inserir.php");

            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
            nameValuePairs.add(new BasicNameValuePair("Titulo", titulo ));
            nameValuePairs.add(new BasicNameValuePair("Descricao", descricao));
            nameValuePairs.add(new BasicNameValuePair("Autor", autor));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void s) {
        super.onPostExecute(s);
        Toast.makeText(activity, "Ocorrencia salva com sucesso", Toast.LENGTH_SHORT).show(); // Mensagem ao usuario
        troca.trocar(activity, OcorrenciasNovas_Activity.class);

    }
}

