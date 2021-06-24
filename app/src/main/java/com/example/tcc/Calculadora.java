package com.example.tcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.DecimalFormat;

public class Calculadora extends AppCompatActivity {

    double v1 = 20, v2 = 25, v3 = 30, v4 = 40, v5 = 43;

    //Iniciar variavel
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        //Estanciar variavel
        drawerLayout = findViewById(R.id.drawer_layout);
        EditText ida = findViewById(R.id.idade);
        EditText alt = findViewById(R.id.altura);
        EditText pes = findViewById(R.id.peso);
        TextView res = findViewById(R.id.resultado);
        Button calc = findViewById(R.id.button);
        ToggleButton gen = findViewById(R.id.genero);

        gen.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                gen.setText("Mulher");
                Context context = getApplicationContext();
                CharSequence text = "Sexo alterado para mulher";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                v1 = 19;
                v2 = 24;
                v3 = 29;
                v4 = 39;
                v5 = 39;
            }else{
                gen.setText("Homem");
                Context context = getApplicationContext();
                CharSequence text = "Sexo alterado para homem";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                v1 = 20;
                v2 = 25;
                v3 = 30;
                v4 = 40;
                v5 = 43;
            }
        });
        calc.setOnClickListener(v -> {
            if (!ida.getText().toString().equals("") && !alt.getText().toString().equals("") && !pes.getText().toString().equals("")) {
                if (!alt.getText().toString().equals(".") && !pes.getText().toString().equals(".")){
                    int idade1 = Integer.parseInt(ida.getText().toString());
                    double altura1 = Double.parseDouble(alt.getText().toString());
                    double peso1 = Double.parseDouble(pes.getText().toString());
                    boolean genero1;
                    double resultado1;
                    String status = null;
                    resultado1 = peso1 / (altura1 * altura1);
                    if (resultado1 < v1) {
                        status = "Abaixo do peso";
                    } else if (resultado1 >= v1 && resultado1 < v2) {
                        status = "Na média";
                    } else if (resultado1 >= v2 && resultado1 < v3) {
                        status = "Sobrepeso";
                    } else if (resultado1 >= v3 && resultado1 < v4) {
                        status = "Obesidade I";
                    } else if (resultado1 >= v4 && resultado1 < v5) {
                        status = "Obesidade II";
                    } else if (resultado1 >= v5) {
                        status = "Obesidade III";
                    }

                    DecimalFormat df = new DecimalFormat("0.0");

                    res.setText("Resultado: " + (df.format(resultado1)) + " " + status);

                }else {
                    Context context = getApplicationContext();
                    CharSequence text = "Erro digite valores válidos!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }else {
                Context context = getApplicationContext();
                CharSequence text = "Erro preencha todos os campos!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
        MainActivity.nome = findViewById(R.id.nome_toolbar);
        if (!Login.keep){
            MainActivity.nome.setText("Olá" + " " + Login.email1);
        }else{
            MainActivity.nome.setText("Olá" + " " + Login.email2);
        }
    }
    public void ClickMenu(View view){
        //Abrir Drawer
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        MainActivity.openDrawer(drawerLayout);
    }
    public void ClickLogo(View view){
        //Fechar Drawer
        MainActivity.closeDrawer(drawerLayout);
    }
    public void ClickHome(View view){
        //Redirecionar activity para Home
        MainActivity.redirectActivity(this,MainActivity.class);
        finish();
    }
    public void ClickAlimentos(View view){
        //Redirecionar activity para Alimentos
        MainActivity.redirectActivity(this,Alimentos.class);
        finish();
    }
    public void ClickPiramide(View view){
        //Redirecionar activity para Piramide
        MainActivity.redirectActivity(this,Piramide.class);
        finish();
    }
    public void ClickPorcoes(View view){
        // Redirecionar para Piramide Alimentar
        MainActivity.redirectActivity(this,Porcoes.class);
        finish();
    }
    public void ClickCalculadora(View view){
        //Redirecionar Activity
        MainActivity.closeDrawer(drawerLayout);
    }
    public void ClickGadgets(View view){
        //Redirecionar activity para Gadgets
        MainActivity.redirectActivity(this,Gadgets.class);
        finish();
    }
    public void ClickConfig(View view){
        // Redirecionar para sobre o app
        MainActivity.redirectActivity(this,Config.class);
        finish();
    }
    public void ClickSobreApp(View view){
        //Redirecionar activity para Sobre o App
        MainActivity.redirectActivity(this,SobreApp.class);
        finish();
    }
    public void ClickLogout(View view){
        //Sair da conta
        SplashScreen.prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = SplashScreen.prefs.edit();
        editor.putBoolean("keep", false);
        editor.apply();
        MainActivity.redirectActivity(this,Login.class);
        finish();
    }
    @Override
    public void onBackPressed() {
        MainActivity.sair(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        //Fechar Drawer
        MainActivity.closeDrawer(drawerLayout);
    }
}