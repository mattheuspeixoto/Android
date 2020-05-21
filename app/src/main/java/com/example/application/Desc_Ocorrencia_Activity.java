package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Desc_Ocorrencia_Activity extends AppCompatActivity {
   TextView autor;
   TextView descricao;
   TextView data_criacao;
   TextView titulo;
   Button btvoltar;
   Button btresolver;
   Fato f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocorrencia);

        //Faz o link com os elementos da Tela
        autor= (TextView) findViewById(R.id.tvautor);
        descricao=(TextView)findViewById(R.id.tvdesc);
        titulo= (TextView) findViewById(R.id.tv_titulo);
        data_criacao=(TextView)findViewById(R.id.tv_dtcriacao);
        btvoltar = (Button)findViewById(R.id.bt_voltar);
        btresolver = (Button)findViewById(R.id.bt_resolver);


        // Recebe o objeto passado pela outra tela
         f= getIntent().getExtras().getParcelable("Fato");
        Log.d("Teste", f.toString());

        // System.out.println(f.toString());

        // Formatacao da Data
      //  SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
      //  String result = (String) out.format(new Date());


        // Seta os valores nos campos da tela
        descricao.setText(f.getDescricao());
        autor.setText(f.getAutor());
        titulo.setText(f.getTituto());
        data_criacao.setText(f.getDatacriacao());


        // Açoes ao Clicar no Botão Voltar
        btvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltar();
            }
        });

        // Açoes ao Clicar no Botão Resolver
        btresolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolver();

            }
        });
    }

    //Cria uma Intent, e faz a troca da tela
    private void voltar() {
        Intent it = new Intent(this,MainActivity.class);
        startActivity(it);
        finish();
    }

    //Cria uma Intent, e faz a troca da tela passando o objeto
    private void resolver() {
        Intent it = new Intent(this,Resolucao.class);
        it.putExtra("Resolver", f);
        startActivity(it);
        finish();
    }
}
