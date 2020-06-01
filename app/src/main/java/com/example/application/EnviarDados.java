package com.example.application;


import android.app.Activity;
import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.List;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

public class EnviarDados extends AsyncTask<String, String, Void> {

    private String solucao;
    private String id;
    TrocarTela troca = new TrocarTela();
    private Activity activity;

   public EnviarDados(Activity activity,String s, int id ) {
        this.solucao = s;
        this.id= Integer.toString(id);
        this.activity = activity;

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... strings) {
         try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://192.168.100.8/api/atualiza.php");
                    // Add your data
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                    nameValuePairs.add(new BasicNameValuePair("ID", id ));
                    nameValuePairs.add(new BasicNameValuePair("Solucao", solucao));
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
            troca.trocar(activity, OcorrenciasNovas_Activity.class);

        }
}



