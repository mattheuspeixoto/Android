package com.example.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
public class MainActivity extends AppCompatActivity {
    private final String BASE_URL ="http://192.168.100.8/api/novos.php";
    List<Fato> ocorrencias;
    ArrayAdapter<Fato> adaptador;

     // Elementos da Tela
    ListView listagem ;
    Button criar,resolvidos;
    Fato f;
    String autor,titulo,descricao,datac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Faz o link com os Elementos da Tela
        listagem = (ListView)findViewById(R.id.list_ocorrencias);
        criar = (Button)findViewById(R.id.bt_novaocorr);
        resolvidos =(Button)findViewById(R.id.bt_resolvidos);

        ocorrencias = new ArrayList<Fato>(); // Cria a lista de fatos
        Toast.makeText(getApplicationContext(), "Carregando ", Toast.LENGTH_LONG).show(); // Mensagem ao usuario
        letsDoSomeNetworking(BASE_URL); // Metodo para receber o json

        //Açoes ao Clicar em um item da Lista
        listagem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                exibirOcorrencia(ocorrencias.get(position));  // passa o objeto da posicao selecionada
            }
        });

        //Açoes ao clicar no botao criar
        criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                novo();
            }
        });

        //Açoes ao clicar no botao resolvidos
        resolvidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exibirResolvido();
            }
        });


}

// Troca de tela e passa o objeto
    private void exibirOcorrencia(Fato fato) {
        Intent it = new Intent(this, Desc_Ocorrencia_Activity.class);
        it.putExtra("Ocorrencia",fato);
        startActivity(it);
        finish();
    }

// Chama a Tela de Cadastro de Ocorrencia
    public void novo(){
        Intent it = new Intent(this, NovaOcorrencia_Activity.class);
        startActivity(it);
        finish();
    }
//Chama a Tela dos Resolvidos
    private void exibirResolvido() {
        Intent it = new Intent(this, Resolvidos_Activity.class);
        startActivity(it);
        finish();
    }
// Adiciona os registros na lista da Tela
    void preencher(JSONArray array){
        Gson gson = new Gson();
         try{
            for(int i = 0; i < array.length(); i++){
                JSONObject ob = array.getJSONObject(i);     // Pega os objetos Json dentro do Array
                f = gson.fromJson(ob.toString(),Fato.class); // Cria um Objeto fato a partir do Json
                ocorrencias.add(f);
            }
        }catch (JSONException e) {
             e.printStackTrace();
         }
        adaptador = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,ocorrencias);
        listagem.setAdapter(adaptador);
    }

//Recebe os dados do Servidor
    private void letsDoSomeNetworking(String url) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                if (response == null || response.length() == 0)
                    Toast.makeText(getApplicationContext(), "Nenhuma Ocorrencia", Toast.LENGTH_LONG).show(); // Mensagem ao usuario
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
