package com.example.application;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

// Tela de Cadastrar uma nova Ocorrencia

public class Login_Activity extends AppCompatActivity {
    EditText senha;
    EditText login;
    Button entrar;
    Button cancelar;
    Fato f;
    TrocarTela troca = new TrocarTela();
    Activity activity = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Faz o link com os elementos da Tela
        senha = (EditText)findViewById(R.id.input_password_login);
        entrar = (Button)findViewById(R.id.bt_votar_res);
        cancelar =(Button)findViewById(R.id.bt_cancelar);
        login = (EditText) findViewById(R.id.input_user_login) ;
        login.requestFocus();

        Log.i("TesteActivity","Activity"+activity.toString());

        // Ações ao clicar no botao salvar
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String descricao = senha.getText().toString();
                String t = login.getText().toString();

                // Testa se o campo titulo esta vazio
                if (TextUtils.isEmpty(t)) {
                    login.setError("Digite o login");
                    Toast.makeText(getApplicationContext(), "Digite seu login", Toast.LENGTH_LONG).show();
                    return;
                }
                //Testa se o campo descricao esta vazio
                if (TextUtils.isEmpty(descricao)) {
                    senha.setError("Digite a sua senha");
                    Toast.makeText(getApplicationContext(), "Digite a sua senha", Toast.LENGTH_LONG).show();
                    return;
                }

                troca.trocar(activity, OcorrenciasNovas_Activity.class);
                finish();

            }
        });

        // Ações ao clicar no botao cancelar
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setText("");
                senha.setText("");
                finish();
            }
        });
    }

    }

