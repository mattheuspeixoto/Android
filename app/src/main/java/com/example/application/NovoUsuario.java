package com.example.application;

        import androidx.appcompat.app.AppCompatActivity;

        import android.app.Activity;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.Toast;

        import java.util.ArrayList;

public class NovoUsuario extends AppCompatActivity {
    EditText nome,email,telefone;
    Button cadastrar,voltar;
    TrocarTela troca =new TrocarTela();
    Spinner ap;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter adaptador;
    Activity a = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);

        nome= findViewById(R.id.nc_nome);
        email= findViewById(R.id.nc_email);
        telefone= findViewById(R.id.nc_telefone);
        cadastrar= findViewById(R.id.bt_cad_conta);
        voltar =findViewById(R.id.bt_nc_voltar);
        ap= findViewById(R.id.spinner_novaconta);

        arrayList.add("Selecione seu Apartamento");
        for(int i=1;i<=20;i++){
            String s = Integer.toString(i);
            arrayList.add(s);
        }

        adaptador = new ArrayAdapter<>(NovoUsuario.this, android.R.layout.simple_list_item_1, arrayList);
        ap.setAdapter(adaptador);

        // Ações ao clicar no botao salvar
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = nome.getText().toString();
                String e = email.getText().toString();
                String t = telefone.getText().toString();

                // Testa se o campo nome esta vazio
                if (TextUtils.isEmpty(n)) {
                    nome.setError("Digite o seu nome: ");
                    Toast.makeText(getApplicationContext(), "Digite o seu nome: ", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Testa se o campo email esta vazio
                if (TextUtils.isEmpty(e)) {
                    email.setError("Digite seu email: ");
                    Toast.makeText(getApplicationContext(), "Digite o seu email: ", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Testa se o campo email é valido
                if(!isValidEmail(email.getText())){
                    Toast.makeText(getApplicationContext(), "Email is INVALID.", Toast.LENGTH_SHORT).show();
                    email.setError("Digite um email valido : ");
                    email.setText("");
                    return;
                }

                //Testa se o campo telefone esta vazio
                if (TextUtils.isEmpty(t)) {
                    telefone.setError("Digite o seu telefone: ");
                    Toast.makeText(getApplicationContext(), "Digite o seu telefone: ", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(ap == null || ap.getSelectedItemPosition()==0) {
                  Toast.makeText(getApplicationContext(), "Selecione seu Apartamento: ", Toast.LENGTH_SHORT).show();
                    return;
                }

                salvar();
                troca.trocar(a, Login_Activity.class);
                finish();

            }
        });


        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                troca.trocar(a,Login_Activity.class);
            }
        });


    }

    private void salvar() {
        Toast.makeText(getApplicationContext(), "Salvo com Sucesso", Toast.LENGTH_SHORT).show();
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();

    }


}
