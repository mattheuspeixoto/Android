package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Resolvidos_Activity extends AppCompatActivity {

    ListView resolvidos;
    Button voltar;
    String y;
    Fato f;
    List<Fato> ocorrencias;
    ArrayAdapter<Fato> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resolvidos);

        //Link com os elementos da tela
        voltar=(Button)findViewById(R.id.bt_resolvidos_voltar);
        resolvidos=(ListView)findViewById(R.id.list_resolvidos);

        // Cria a uma lista de ocorrencias
        ocorrencias = new ArrayList<Fato>();

        // Foreach para receber os dados do servidor e colocar na listagem
        /* for (:) {
                  f = new Fato();
                 ocorrencias.add(f);
            }*/


// ----------------------------T I R A R --- Q U A N D O ---- C O N E C T A R --- C O M --- O --- S E R V I D O R -------------------------------------------------------
        SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
        y = (String) out.format(new Date());
        f= getIntent().getExtras().getParcelable("Resolvido");
        f.setResoluçao("Foi realizado a substituiçao da lampada");
        f.setDataresolucao(y);
        ocorrencias.add(f);
// ------------------------------------------------------------------------------------------------------------------------------------------------

      adaptador = new ArrayAdapter<>(Resolvidos_Activity.this, android.R.layout.simple_list_item_1,ocorrencias);
      resolvidos.setAdapter(adaptador);
      resolvidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                exibirResumo(ocorrencias.get(position));  // passa o objeto da posicao selecionada
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltar();
            }
        });
    }

    private void voltar() {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
        finish();
    }

    private void exibirResumo(Fato fato) {
        Intent it = new Intent(this, Detalhes_Activity.class);
        it.putExtra("Resumo",fato);
        startActivity(it);
        finish();
    }
}
