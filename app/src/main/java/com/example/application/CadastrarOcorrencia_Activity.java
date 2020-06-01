package com.example.application;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

// Tela de Cadastrar uma nova Ocorrencia

public class CadastrarOcorrencia_Activity extends AppCompatActivity {
    EditText descricao,titulo;
    Button salvar,cancelar;
    Fato f;
    TrocarTela troca = new TrocarTela();
    Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novaocorrencia);

        // Faz o link com os elementos da Tela
        descricao = (EditText)findViewById(R.id.input_descriçao);
        salvar = (Button)findViewById(R.id.bt_votar_res);
        cancelar =(Button)findViewById(R.id.bt_cancelar);
        titulo = (EditText) findViewById(R.id.input_titulo) ;
        titulo.requestFocus();

        // Ações ao clicar no botao salvar
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String d =  descricao.getText().toString();
               String t =  titulo.getText().toString();

               // Testa se o campo titulo esta vazio
                if(TextUtils.isEmpty(t)){
                    titulo.setError("Digite o titulo da ocorrencia");
                    Toast.makeText(getApplicationContext(), "Digite o titulo da ocorrencia", Toast.LENGTH_LONG).show();
                    return;
                }
                //Testa se o campo descricao esta vazio
                if(TextUtils.isEmpty(d)){
                    descricao.setError("Digite a descrição da ocorrencia");
                    Toast.makeText(getApplicationContext(), "Digite a descrição da ocorrencia", Toast.LENGTH_LONG).show();
                    return;
                }

                new NovaOcorrencia(activity,descricao.getText().toString(),titulo.getText().toString(),"Mattheus Peixoto").execute();
            }
        });

        // Ações ao clicar no botao cancelar
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descricao.setText("");
                troca.trocar(activity, OcorrenciasNovas_Activity.class);
                finish();

            }
        });
    }}



