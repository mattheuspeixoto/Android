package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Resolucao extends AppCompatActivity {

    TextView titulo;
    TextView data;
    Button salvar;
    Button volta;
    TextView soluçao;
    Fato f;
    SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resolucao);

        //Faz o link com os elementos da Tela
        titulo = (TextView) findViewById(R.id.tv_res_titulo);
        data = (TextView) findViewById(R.id.tv_res_data);
        salvar= (Button)findViewById(R.id.bt_res_salvar);
        volta = (Button)findViewById(R.id.bt_res_voltar);
        soluçao = (TextView)findViewById(R.id.input_solucao_res2);

        //Recebe o objeto passado pela outra tela
        f= getIntent().getExtras().getParcelable("Resolver");

        // Passa os dados para a tela
        titulo.setText(f.getTituto());
        data.setText(f.getDatacriacao());

        // Ações ao clicar no botao salvar
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String descricao = soluçao.getText().toString();
                // Testa se o campo esta vazio
                if (TextUtils.isEmpty(descricao)) {
                    soluçao.setError("Digite a solucao da ocorrencia");
                    Toast.makeText(getApplicationContext(), "Vazio", Toast.LENGTH_LONG).show();
                    return;
                }
                // Passa a resolucao e a data para o objeto
                String y = (String) out.format(new Date());
                f.setResoluçao(descricao);
                f.setDataresolucao(y);
                Log.d("Teste", f.toString());
            }});

// Ações ao clicar no botao voltar
     volta.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
         voltar();}
         });
    }

    // Faz a troca das telas
    public void voltar(){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
        finish();
    }
}
