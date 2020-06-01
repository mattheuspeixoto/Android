package com.example.application;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Detalhes_Novos_Activity extends AppCompatActivity {
    TextView autor, descricao,data_criacao,titulo;
    Button btvoltar,btresolver;
    Fato f;
    TrocarTela troca = new TrocarTela();
    Activity c = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocorrencia);

        //Faz o link com os elementos da Tela
        autor = (TextView) findViewById(R.id.tvautor);
        descricao = (TextView) findViewById(R.id.tvdesc);
        titulo = (TextView) findViewById(R.id.tv_titulo);
        data_criacao = (TextView) findViewById(R.id.dtcriacao_res);
        btvoltar = (Button) findViewById(R.id.bt_voltar);
        btresolver = (Button) findViewById(R.id.bt_resolver);


        // Recebe o objeto passado pela outra tela
        f = getIntent().getExtras().getParcelable("Ocorrencia");

        // Seta os valores nos campos da tela
        descricao.setText(f.getDescricao());
        autor.setText(f.getAutor());
        titulo.setText(f.getTitulo());
        data_criacao.setText(f.getDatacriacao());


        // Açoes ao Clicar no Botão Voltar
        btvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                troca.trocar(c, OcorrenciasNovas_Activity.class);
                finish();
            }
        });

        // Açoes ao Clicar no Botão Resolver
        btresolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                troca.trocarPassandoObjeto(c, Resolver_Activity.class, f, "Resolver");
                finish();

            }
        });
    }

}
