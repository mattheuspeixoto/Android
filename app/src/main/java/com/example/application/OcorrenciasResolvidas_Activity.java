package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import cz.msebera.android.httpclient.Header;

// Tela das Ocorrencias Ja finalizadas
public class OcorrenciasResolvidas_Activity extends AppCompatActivity {
    ListView resolvidos;
    ProgressBar progress;
    TextView texto;
    Button voltar;
    Fato f;
    List<Fato> ocorrencias;
    ArrayAdapter<Fato> adaptador;
    TrocarTela troca = new TrocarTela();
    Activity activity = this;
    private final String BASE_URL ="http://192.168.100.8/api/resolvidos.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resolvidos);

        //Link com os elementos da tela
        voltar=(Button)findViewById(R.id.bt_resolvidos_voltar);
        resolvidos=(ListView)findViewById(R.id.list_resolvidos);
        progress = (ProgressBar) findViewById(R.id.progress);
        texto = (TextView) findViewById(R.id.texto);


        ocorrencias = new ArrayList<Fato>();// Cria a uma lista de ocorrencias
        //Toast.makeText(getApplicationContext(), "Carregando ", Toast.LENGTH_LONG).show(); // Mensagem ao usuario
        letsDoSomeNetworking(BASE_URL); // Metodo para receber o json
        new MinhaTask(this,progress,texto,resolvidos).execute();


        //Quando Seleciana uma Ocorrencia da Lista
        resolvidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // exibirResumo(ocorrencias.get(position));  // passa o objeto da posicao selecionada
              troca.trocarPassandoObjeto(activity,Detalhes_Resolvidos_Activity.class,ocorrencias.get(position),"OcorrenciaResolvida");
                 finish();
                            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               troca.trocar(activity, OcorrenciasNovas_Activity.class);
               finish();


            }
        });
    }


//Mostra dados na Tela
    void preencher(JSONArray array){
        Gson gson = new Gson();
        try {
            for(int i = 0; i < array.length(); i++){
                JSONObject ob = array.getJSONObject(i); // Pega os objetos Json dentro do Array
                f = gson.fromJson(ob.toString(),Fato.class); // Cria um objeto Fato a partir do JSON
                ocorrencias.add(f);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            }
        adaptador = new ArrayAdapter<>(OcorrenciasResolvidas_Activity.this, android.R.layout.simple_list_item_1,ocorrencias);
        resolvidos.setAdapter(adaptador);
    }




//Recebe os dados do Servidor
    private void letsDoSomeNetworking(String url) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                if(response == null || response.length() ==0)
                    Toast.makeText(getApplicationContext(),"Nenhuma Ocorrencia" , Toast.LENGTH_LONG).show(); // Mensagem ao usuario
                 else
                    preencher(response);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response) {
                System.out.println( "Request fail! Status code: " + statusCode);
                System.out.println("Fail response: " + response);
                System.out.println("Ocorrencia"+ e.toString());
                Toast.makeText(getApplicationContext(), "Request fail! Status code: " + statusCode+"\nFail response: " + response+"\nOcorrencia"+ e.toString(), Toast.LENGTH_LONG).show(); // Mensagem ao usuario
            }
        });
    }



}
