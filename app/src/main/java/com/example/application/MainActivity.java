package com.example.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Tela Principal
public class MainActivity extends AppCompatActivity {
    List<Fato> ocorrencias;
    ArrayAdapter<Fato> adaptador;

    // Elementos da Tela
    ListView listagem ;
    Button criar,resolvidos;
    Fato f;

    // R E T I R A R   A O   C O N E C T A R   C O M   O   S E R V I D O R
    Fato f1,f2,f3,x;
    String datac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Faz o link com os Elementos da Tela
        listagem = (ListView)findViewById(R.id.list_ocorrencias);
        criar = (Button)findViewById(R.id.bt_novaocorr);
        resolvidos =(Button)findViewById(R.id.bt_resolvidos);

        //Cria uma lista
        ocorrencias = new ArrayList<Fato>();

        //Formata a Data
        SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
        datac = (String) out.format(new Date());


        // Foreach para receber os dados do servidor e colocar na listagem
        /*for (:) {
              f = new Fato();
              ocorrencias.add(f);
         }
         */

// ----------------------------T I R A R --- Q U A N D O ---- C O N E C T A R --- C O M --- O --- S E R V I D O R -------------------------------------------------------
        f = new Fato("Mattheus.Peixoto","Infiltração","Quando chove esta molhando a parede da escada ",datac);
        f1 = new Fato("Eu","Barulho","Vizinho do apt 400 com som alto",datac);
        f2 = new Fato("Eu","Lixo","O Lixo do bloco não foi retirado ",datac);
        f3 = new Fato("Eu","Agua","Vazamento na pia do banheiro da suite apt 400",datac);
        ocorrencias.add(f);
        ocorrencias.add(f1);
        ocorrencias.add(f2);
        ocorrencias.add(f3);
//-------------------------------------------------------------------------------------------------------------------------------------------


        adaptador = new ArrayAdapter<Fato>(MainActivity.this, android.R.layout.simple_list_item_1,ocorrencias);
        listagem.setAdapter(adaptador);

        //Açoes ao Clicar em um item da Lista
        listagem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                exibirOcorrencia(ocorrencias.get(position));  // passa o objeto da posicao selecionada
            }
        });

        //Açoes ao clicar no botao criar
        criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                novo();
            }
        });

        //Açoes ao clicar no botao resolvidos
        resolvidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exibirResolvido();
            }
        });


}

// Troca de tela e passa o objeto
    private void exibirOcorrencia(Fato fato) {
        Intent it = new Intent(this, Desc_Ocorrencia_Activity.class);
        it.putExtra("Ocorrencia",fato);
        startActivity(it);
        finish();
    }

// Chama a Tela de Cadastro de Ocorrencia
    public void novo(){
        Intent it = new Intent(this, NovaOcorrencia_Activity.class);
        startActivity(it);
        finish();
    }

//Chama a Tela dos Resolvidos
    private void exibirResolvido() {
        Intent it = new Intent(this, Resolvidos_Activity.class);
// ----------------------------T I R A R---Q U A N D O----C O N E C T A R---C O M---O---S E R V I D O R -------------------------------------------------------
       x= new Fato("Mattheus Peixoto","Lampada Queimada","A lampada do Hall do 2 Andar do Bloco A  esta queimada desde ontem",datac);
       it.putExtra("Resolvido",x);
// -------------------------------------------------------------------------------------------------------------------------------
       startActivity(it);
       finish();
    }

}
