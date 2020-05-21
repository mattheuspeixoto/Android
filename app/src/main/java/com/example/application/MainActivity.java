package com.example.application;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Tela Principal
public class MainActivity extends AppCompatActivity {
    ListView listagem ;
    List<String> ocorrencias;
    ArrayAdapter<String> adaptador;
    //List<Fato> ocorrencia;
    Button criar;
    Fato f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Faz o link com os Elementos da Tela
        listagem = (ListView)findViewById(R.id.list_oc);
        criar = (Button)findViewById(R.id.bt_novo);


        /* // Foreach para receber os dados do servidor e colocar na listagem
        for (:) {
                      }        */


       // Preenche o array com as ocorrencias
        ocorrencias = new ArrayList<String>();
        ocorrencias.add("Ocorrencia 1");
        ocorrencias.add("Ocorrencia 2");
        ocorrencias.add("Ocorrencia 3");
        ocorrencias.add("Ocorrencia 4");
        ocorrencias.add("Ocorrencia 5");
        ocorrencias.add("Sobre");

        SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String datac = (String) out.format(new Date());


        f = new Fato("Eu","Agua","Vazamento na pia do banheiro da suite apt 400",datac);


        adaptador = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,ocorrencias);
        listagem.setAdapter(adaptador);

        //Açoes ao Clicar em um item da Lista
        listagem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                       exibirOcorrencia(f);
                       break;
                    case 1:
                        // exibirOcorrencia();
                        //Toast.makeText(getApplicationContext(), data.toString(), Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        // exibirOcorrencia();
                        Toast.makeText(getApplicationContext(), ocorrencias.get(position), Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        // exibirOcorrencia();
                        Toast.makeText(getApplicationContext(), ocorrencias.get(position), Toast.LENGTH_LONG).show();;
                    case 4:
                        // exibirOcorrencia();
                        Toast.makeText(getApplicationContext(), ocorrencias.get(position), Toast.LENGTH_LONG).show();                        break;
                    case 5:
                        exibirSobre();
                        break;
                }

            }
        });

        criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                novo();
            }
        });
}

// Troca de tela e passa o objeto
    private void exibirOcorrencia(Fato f) {
        Intent it = new Intent(this, Desc_Ocorrencia_Activity.class);
        it.putExtra("Fato",f);
        startActivity(it);
        finish();
    }

    // Troca de tela
    private void exibirSobre() {
        Intent it = new Intent(this, SobreActivity.class);
        startActivity(it);
        finish();
    }

    // Troca de tela
    public void novo(){
        Intent it = new Intent(this, NovaOcorrencia_Activity.class);
        startActivity(it);
        finish();
    }
}
