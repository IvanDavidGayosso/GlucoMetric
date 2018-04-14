package com.example.ivan.glucometric;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.security.Principal;


public class InicioSesion extends AppCompatActivity {
    Fragment registrar;
    Button  btnReg,btnAcce;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_inicio_sesion);

        btnReg = (Button) findViewById(R.id.btnRegistro);
        btnReg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(InicioSesion.this, RegistroUsuario.class);
                startActivity(intent);
            }
        });

        btnAcce = (Button) findViewById(R.id.btnAcceder);
        btnAcce.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(InicioSesion.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });

    }

}
