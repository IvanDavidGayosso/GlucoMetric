
package com.example.ivan.glucometric;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//Clase de Splash el cual solo creara al Fragment Inicio de sesion
public class Splash extends AppCompatActivity {
    Fragment  inicio_sesion;



    
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);

            //Genera una plantalla en un determinado tiempo (2.5 seg) y posteriormente enviara a la aplicacion a la Interfaz (InicioSesion)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, InicioSesion.class);
                startActivity(intent);
                finish();

            }
        },2500);
    }
}
