package com.example.tcc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

public class Formulario extends AppCompatActivity {

    private String email, senha, nome, idade, peso, altura;
    private EditText  emailCad, senhaCad, nomeCad, idadeCad, pesoCad, alturaCad ;
    private TextView status;
    private String URL = "http://siad.net.br/app/cadastroPHP.php" ;
    private CheckBox msenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        msenha = findViewById(R.id.mostrar_cad);
        emailCad = findViewById(R.id.email_cad);
        senhaCad = findViewById(R.id.senha_cad);
        nomeCad = findViewById(R.id.nome_cad);
        idadeCad = findViewById(R.id.idade_cad);
        pesoCad = findViewById(R.id.peso_cad);
        alturaCad = findViewById(R.id.altura_cad);
        status = findViewById(R.id.resultCad);
        nome = email = senha = altura = peso = idade = "";
        mostrarsenha();
        verif();
    }
    public void verif(){
        //Verifica qual o tema escolhido pelo usuário
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SplashScreen.escolha = prefs.getInt("escolha", 0);
        if (SplashScreen.escolha == 0){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }else if (SplashScreen.escolha == 1){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }
    //  Metodo de cadastro do usuario
    public void Cadastrar(View view){
        nome = nomeCad.getText().toString().trim();
        email = emailCad.getText().toString().trim();
        senha = senhaCad.getText().toString().trim();
        altura = alturaCad.getText().toString().trim();
        peso = pesoCad.getText().toString().trim();
        idade = idadeCad.getText().toString().trim();
        if(nome.equals("")   ||
           email.equals("")  ||
           senha.equals("")  ||
           altura.equals("") ||
           peso.equals("")   ||
           idade.equals("")){
            Toast.makeText(this,"Preencha todos os campos", Toast.LENGTH_SHORT). show();
        }else{
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Toast.makeText(Formulario.this, "Cadastro efetuado!", Toast.LENGTH_SHORT).show();
                    } else if (response.equals("failure")) {
                        Toast.makeText(Formulario.this, "Cadastro não efetuado! ", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map <String, String> data = new HashMap<>();
                    data.put("nome", nome);
                    data.put("email", email);
                    data.put("senha", senha);
                    data.put("idade", idade);
                    data.put("peso", peso);
                    data.put("altura", altura);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }

    }

    private void mostrarsenha(){
        msenha.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (msenha.isChecked()){
                senhaCad.setInputType(0x00000091);
            }else {
                senhaCad.setInputType(0x00000081);
            }
        });
    }

    @Override
    public void onBackPressed() {
        MainActivity.redirectActivity(this,Login.class);
        finish();
    }

    public void pgLogin(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
}