package com.example.ivan.glucometric;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class RegistroUsuario extends AppCompatActivity {
    Button btn_aceptar,btn_cancelar;
    EditText id,nombre,ap,am;
    String ID;
    String opcion="1";
    BaseDeDatos basedatos;
    SQLiteDatabase db;
    static String OPCION="usuario";


    public RegistroUsuario() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registro_usuario);
        btn_aceptar = (Button)findViewById(R.id.btnAceptar);
        nombre = (EditText) findViewById(R.id.et_nombre);
        ap = (EditText) findViewById(R.id.et_apell_pat);
        am = (EditText) findViewById(R.id.et_apell_mat);
        basedatos = new BaseDeDatos(this);
        final Intent valor = getIntent();


        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                 Bundle parametros = new Bundle();
                 parametros.putString("nombre", nombre.getText().toString());
                 parametros.putString("ap_pa",ap.getText().toString());
                 parametros.putString("ap_ma",ap.getText().toString());

                Intent i = new Intent(getApplicationContext(), Usuario.class);
                i.putExtras(parametros);
                startActivity(i);
                finish();
            }
        });

        btn_cancelar = (Button) findViewById(R.id.btnCancelar);
        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InicioSesion.class);
                startActivity(intent);
                finish();
            }
        });
    }




}
