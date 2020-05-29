package com.example.application;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import cz.msebera.android.httpclient.Header;

//Tela Principal
public class OcorrenciasNovas_Activity extends AppCompatActivity {
    private final String BASE_URL = "http://192.168.100.8/api/novos.php";
    List<Fato> ocorrencias;
    ArrayAdapter<Fato> adaptador;

    // Elementos da Tela
    ListView listagem;
    Button criar, resolvidos,sair;
    Fato f;
    ProgressBar progress;
    TextView texto;
    TrocarTela troca = new TrocarTela();
    Activity activity = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novos);

        //Faz o link com os Elementos da Tela
        listagem = (ListView) findViewById(R.id.list_ocorrencias);
        criar = (Button) findViewById(R.id.bt_novaocorr);
        resolvidos = (Button) findViewById(R.id.bt_resolvidos);
        progress = (ProgressBar) findViewById(R.id.progress);
        texto = (TextView) findViewById(R.id.texto);
        sair = (Button) findViewById(R.id.bt_sair);

        ocorrencias = new ArrayList<Fato>(); // Cria a lista de fatos
       // Toast.makeText(getApplicationContext(), "Carregando ", Toast.LENGTH_LONG).show(); // Mensagem ao usuario
        listagem.setVisibility(View.INVISIBLE);
        letsDoSomeNetworking(BASE_URL);
        new MinhaTask(this,progress,texto,listagem).execute();



        //Açoes ao Clicar em um item da Lista
        listagem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // passa o objeto da posicao selecionada
                troca.trocarPassandoObjeto(activity, Detalhes_Novos_Activity.class,ocorrencias.get(position),"Ocorrencia");
                finish();

            }
        });

        //Açoes ao clicar no botao criar
        criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                troca.trocar(activity, CadastrarOcorrencia_Activity.class);
                finish();
            }
        });

        //Açoes ao clicar no botao resolvidos
        resolvidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                troca.trocar(activity, OcorrenciasResolvidas_Activity.class);
                finish();
            }
        });

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                troca.trocar(activity,SairActivity.class);
            }
        });
    }



// Adiciona os registros na lista da Tela
    void preencher(JSONArray array) {
        try {
            Gson gson = new Gson();
            for (int i = 0; i < array.length(); i++) {
                JSONObject ob = array.getJSONObject(i);     // Pega os objetos Json dentro do Array
                f = gson.fromJson(ob.toString(), Fato.class); // Cria um Objeto fato a partir do Json
                ocorrencias.add(f);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adaptador = new ArrayAdapter<>(OcorrenciasNovas_Activity.this, android.R.layout.simple_list_item_1, ocorrencias);
        listagem.setAdapter(adaptador);
    }



//Recebe os dados do Servidor
    public void  letsDoSomeNetworking(String url) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {

            // Se der Certo
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                if (response == null || response.length() == 0)
                    Toast.makeText(getApplicationContext(), "Nenhuma Ocorrencia", Toast.LENGTH_LONG).show(); // Mensagem ao usuario
                else
                    preencher(response);
            }
            @Override // Se der Errado
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response) {
                System.out.println("Request fail! Status code: " + statusCode);
                System.out.println("Fail response: " + response);
                System.out.println("Ocorrencia" + e.toString());
                Toast.makeText(getApplicationContext(), "Request fail! Status code: " + statusCode + "\nFail response: " + response + "\nOcorrencia" + e.toString(), Toast.LENGTH_LONG).show(); // Mensagem ao usuario
                }
        });
    }
}

