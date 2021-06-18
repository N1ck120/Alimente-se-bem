package com.example.tcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class MainActivity extends AppCompatActivity {

    // Inicializar a variavel
    DrawerLayout drawerLayout;

    public CardView adm;
    public LinearLayout nt1, conect;

    SliderView sliderView;
    final int[] images = {
            R.drawable.slide1,
            R.drawable.slide2,
            R.drawable.slide3,
            R.drawable.slide4,
            R.drawable.slide5,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Atribuir variavel
        drawerLayout = findViewById(R.id.drawer_layout);
        sliderView = findViewById(R.id.image_slider);
        adm = findViewById(R.id.a);
        nt1 = findViewById(R.id.noticiasll);
        conect = findViewById(R.id.erro);

        SliderAdapter sliderAdapter = new SliderAdapter(images);
        WebView myWebView = (WebView) findViewById(R.id.not);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl("https://www.uol.com.br/vivabem/saude/ultimas/");

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.SLIDE);
        sliderView.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        sliderView.startAutoCycle();

        isOnline();
        internet();
        createNotificationChannel();
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

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Canal1", "Canal1", importance);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return manager.getActiveNetworkInfo() != null &&
                manager.getActiveNetworkInfo().isConnectedOrConnecting();

    }
    public void internet(){
        if(!isOnline()) {
            nt1.setVisibility(View.GONE);
            conect.setVisibility(View.VISIBLE);

        }else {
            nt1.setVisibility(View.VISIBLE);
            conect.setVisibility(View.GONE);
        }

    }
    public void reconectar(View view){
        isOnline();
        internet();
        WebView myWebView = (WebView) findViewById(R.id.not);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl("https://www.uol.com.br/vivabem/saude/ultimas/");
    }


    public void CardCalculadora(View view){
        // Redirecionar para Calculadora de IMC
        redirectActivity(this,Calculadora.class);
        finish();
    }

    public void CardPiramide(View view){
        // Redirecionar para Calculadora de IMC
        redirectActivity(this,Piramide.class);
        finish();
    }

    public void CardAlimentos(View view){
        // Redirecionar para Calculadora de IMC
        redirectActivity(this,Alimentos.class);
        finish();
    }

    public void  refresh(View view){
        isOnline();
        if (isOnline()){
            WebView myWebView = (WebView) findViewById(R.id.not);
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            myWebView.clearCache(true);
            myWebView.loadUrl("https://www.uol.com.br/vivabem/saude/ultimas/");
            Context context = getApplicationContext();
            CharSequence text = "Recarregando...";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }else{
            internet();
            Context context = getApplicationContext();
            CharSequence text = "Sem internet!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void ClickMenu(View view){
        //Abrir Drawer
        openDrawer(drawerLayout);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Canal1")
                .setSmallIcon(R.drawable.ic_notific)
                .setContentTitle("Já bebeu água hoje??")
                .setContentText("Segundo estimativa é recomendado beber 2 litros de água ao dia")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        // Abrir o Layout drawer
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view){
        //Fechar o drawer
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //Fechar o drawer
        //Checar condicao
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            //Quando o drawer estiver aberta
            //Fechar o drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view){
        // Recrear activity
        closeDrawer(drawerLayout);
    }

    public void ClickAlimentos(View view){
        // Redirecionar para Alimentos
        redirectActivity(this,Alimentos.class);
        finish();
    }

    public void ClickPiramide(View view){
        // Redirecionar para Piramide Alimentar
        redirectActivity(this,Piramide.class);
        finish();
    }

    public void ClickPorcoes(View view){
        // Redirecionar para Piramide Alimentar
        redirectActivity(this,Porcoes.class);
        finish();
    }

    public void ClickCalculadora(View view){
        // Redirecionar para Calculadora de IMC
        redirectActivity(this,Calculadora.class);
        finish();
    }

    public void ClickGadgets(View view){
        // Redirecionar para Gadgets
        redirectActivity(this,Gadgets.class);
        finish();
    }
    public void ClickConfig(View view){
        // Redirecionar para sobre o app
        redirectActivity(this,Config.class);
        finish();
    }

    public void ClickSobreApp(View view){
        // Redirecionar para sobre o app
        redirectActivity(this,SobreApp.class);
        finish();
    }

    public void ClickLogout(View view){
        //Sair da conta
        redirectActivity(this,Login.class);
        finish();
    }

    public static void sair(Activity activity) {
        // Iniciar a caixa de alerta
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        //Titulo do Alerta
        builder.setTitle("Sair");
        //Mensagem
        builder.setMessage("Você tem certeza que deseja sair?");
        //Botao "SIM"
        builder.setPositiveButton("Sim", (dialog, which) -> {
            //Fechar activity
            activity.finishAffinity();
            //Sair do app
            System.exit(0);
        });
        //Botao "NAO"
        builder.setNegativeButton("Não", (dialog, which) -> {
            //Fechar mensagem
            dialog.dismiss();
        });
        //Exibir mensagem
        builder.show();

    }

    public static void redirectActivity(Activity activity,Class aClass) {
        //Iniciar intent
        Intent intent = new Intent(activity,aClass);
        //Definir
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Iniciar Activity
        activity.startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        sair(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Fechar drawer
        closeDrawer(drawerLayout);
    }
}