package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TrocarTela troca = new TrocarTela();
    Activity activity= this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         TextView t =  (TextView)findViewById(R.id.textView2);

        ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
        TextView texto = (TextView) findViewById(R.id.texto);
        texto.setVisibility(texto.INVISIBLE);

        new MinhaTask( this, progress, texto).execute();
       // troca.trocar(c,Login_Activity.class);
        //finish();

    }

}
