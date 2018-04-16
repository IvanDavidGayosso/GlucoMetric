package com.example.ivan.glucometric;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MenuPrincipal extends AppCompatActivity {
    CardView cv_perfil,cv_medico,cv_graficas,cv_dispositivos;
    static String OPCION="opcion";
    final String valor="1";
    String usuario;
    Button salir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        cv_perfil = (CardView) findViewById(R.id.cv_perfil);
        cv_medico = (CardView) findViewById(R.id.cv_medico);
        cv_graficas = (CardView) findViewById(R.id.cv_graficas);
        cv_dispositivos = (CardView) findViewById(R.id.cv_dispositivos);
        salir=(Button) findViewById(R.id.bn_salir);
        final Intent valor_inicio = getIntent();
        usuario= valor_inicio.getStringExtra("IDUSUARIO");

        cv_perfil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, ModificarUsuario.class);
                intent.putExtra(OPCION,valor);
                intent.putExtra("IDUSUARIO",usuario);
                startActivity(intent);
            }
        });

        cv_medico.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, GuardarMedico.class);
                startActivity(intent);
            }
        });

        cv_dispositivos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, BuscarDispositivos.class);
                startActivity(intent);
            }
        });

        cv_graficas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, GraficasGlucosa.class);
                startActivity(intent);
            }
        });

        salir.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, InicioSesion.class);
                startActivity(intent);
                finish();
            }
        });

    }


}
