package com.example.application;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

//Detalhes da Ocorrencia
public class Detalhes_Resolvidos_Activity extends AppCompatActivity {
    TextView titulo,autor,data_criacao,data_resolucao,descricao,solucao;
    Button btvoltar;
    Fato f;
    TrocarTela troca = new TrocarTela();
    Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        //Faz os links com os elementos da tela
        titulo = (TextView) findViewById(R.id.tv_titulo);
        autor = (TextView) findViewById(R.id.tvautor);
        data_criacao = (TextView) findViewById(R.id.dtcriacao_res);
        data_resolucao = (TextView) findViewById(R.id.dtresol_res);
        descricao = (TextView) findViewById(R.id.tvdesc);
        solucao = (TextView) findViewById(R.id.tv_sol_res);
        btvoltar = (Button) findViewById(R.id.bt_voltar);

        //Recebe o objeto passado por outras telas
        f = getIntent().getExtras().getParcelable("OcorrenciaResolvida");

        //Seta os atributos do objeto nos elementos da tela
        titulo.setText(f.getTitulo());
        autor.setText(f.getAutor());
        data_criacao.setText(f.getDatacriacao());
        data_resolucao.setText(f.getDataresolucao());
        descricao.setText(f.getDescricao());
        solucao.setText(f.getReSolucao());

        //Ação do botao voltar
        btvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              troca.trocar(activity, OcorrenciasResolvidas_Activity.class);
              finish();
            }
        });
    }


}