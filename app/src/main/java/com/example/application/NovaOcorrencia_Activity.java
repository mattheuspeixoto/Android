package com.example.application;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

// Tela de Cadastrar uma nova Ocorrencia

public class NovaOcorrencia_Activity extends AppCompatActivity {
    EditText desc;
    EditText titulo;
    Button salvar;
    Button cancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova);

        // Faz o link com os elementos da Tela
        desc = (EditText)findViewById(R.id.input_descriçao);
        salvar = (Button)findViewById(R.id.bt_votar_res);
        cancelar =(Button)findViewById(R.id.bt_cancelar);
        titulo = (EditText) findViewById(R.id.input_titulo) ;

        // Ações ao clicar no botao salvar
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String descricao = desc.getText().toString();
               String t = titulo.getText().toString();

               // Testa se o campo titulo esta vazio
                if(TextUtils.isEmpty(t)){
                    titulo.setError("Digite o titulo da ocorrencia");
                    Toast.makeText(getApplicationContext(), "Vazio", Toast.LENGTH_LONG).show();
                    return;
                }
                //Testa se o campo descricao esta vazio
                if(TextUtils.isEmpty(descricao)){
                    desc.setError("Digite a descrição da ocorrencia");
                    Toast.makeText(getApplicationContext(), "Vazio", Toast.LENGTH_LONG).show();
                    return;
                }

                salvar();
            }
        });

        // Ações ao clicar no botao cancelar
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desc.setText("");
                trocar();
            }
        });
    }
    private void salvar() {
        SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String datac = (String) out.format(new Date());
        Fato f = new Fato("Mattheus",titulo.getText().toString(),desc.getText().toString(),datac); // Cria um objeto fato
        Toast.makeText(getApplicationContext(), "Ocorrencia salva com sucesso", Toast.LENGTH_LONG).show(); // Mensagem ao usuario
        //trocar(f); // Passa o objeto para o metodo de trocar tela
        Log.d("Teste",f.toString());
    }

    //Troca a tela e passa o objeto
    public void trocar(Fato f) {
        Intent it = new Intent(this, MainActivity.class);
        it.putExtra("Novo", f); // Passa o objeto para outra tela
        startActivity(it);
        finish();
    }

    //Troca a tela
    public void trocar(){
        Intent it = new Intent(this,MainActivity.class);
        startActivity(it);
        finish();

        }
    }

