package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Resolver_Activity extends AppCompatActivity {

    TextView titulo, data,soluçao;;
    Button btsalvar_res, btvoltar_res;
    TrocarTela troca = new TrocarTela();
    Activity activity = this;
    Fato f;
    SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resolucao);

        //Faz o link com os elementos da Tela
        titulo = (TextView) findViewById(R.id.tv_res_titulo);
        data = (TextView) findViewById(R.id.tv_res_data);
        btsalvar_res= (Button)findViewById(R.id.bt_res_salvar);
        btvoltar_res = (Button)findViewById(R.id.bt_res_voltar);
        soluçao = (TextView)findViewById(R.id.input_solucao_res2);

        //Recebe o objeto passado pela outra tela
        f= getIntent().getExtras().getParcelable("Resolver");

        // Passa os dados para a tela
        titulo.setText(f.getTitulo());
        data.setText(f.getDatacriacao());

        // Ações ao clicar no botao salvar
        btsalvar_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Testa se o campo esta vazio
                String descricao = soluçao.getText().toString();
                if (TextUtils.isEmpty(descricao)) {
                    soluçao.setError("Digite a solução da ocorrencia");
                    Toast.makeText(getApplicationContext(), "Digite a solução da ocorrencia", Toast.LENGTH_LONG).show();
                    return;
                }
                // Passa a resolucao e a data para o objeto
                f.setReSolucao(descricao);
                String y = (String) out.format(new Date());
                f.setDataresolucao(y);
                salvar();
                troca.trocar(activity, OcorrenciasNovas_Activity.class);
                finish();
            }});


        // Ações ao clicar no botao voltar
      btvoltar_res.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            troca.trocar(activity, OcorrenciasNovas_Activity.class);
            finish();

        }
      });
    }


// ----------------------------M U D A R---Q U A N D O----C O N E C T A R---C O M---O---S E R V I D O R -------------------------------------------------------
    public void salvar() {
        Toast.makeText(getApplicationContext(), "Solução salva com sucesso", Toast.LENGTH_LONG).show(); // Mensagem ao usuario
        Log.d("Teste", f.toStrings());
    }
// --------------------------------------------------------------------------------------------------------------------------------------------------

}
