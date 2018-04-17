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

//Clase de RegistroUsuario en donde se encuentran las variables de los botones, los campos de textoy la base de datos.
public class RegistroUsuario extends AppCompatActivity {
    Button btn_aceptar,btn_cancelar;
    EditText id,nombre,apellido_pat,apellido_mat,fecha_naci,peso,estatura;
    String ID;
    String opcion="1";
    BaseDeDatos basedatos;
    SQLiteDatabase db;
    static String OPCION="usuario";

    //Constructor
    public RegistroUsuario() {

    }
//Se enlazan las variables anteriormente creadas con los campos de texto
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registro_usuario);
        nombre = (EditText) findViewById(R.id.nombre);
        apellido_pat = (EditText) findViewById(R.id.ap);
        apellido_mat = (EditText) findViewById(R.id.am);
        fecha_naci = (EditText)findViewById(R.id.fecha_nac);
        peso = (EditText)findViewById(R.id.et_peso);
        estatura = (EditText)findViewById(R.id.et_estatura);
        btn_aceptar= (Button) findViewById(R.id.btnAceptar);
        basedatos = new BaseDeDatos(this);
        final Intent valor = getIntent();

        //Se crea un Evento en el cual se guardaran los parametros de los usuarios
        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                 Bundle parametros = new Bundle();
                 parametros.putString("nombre", nombre.getText().toString());
                 parametros.putString("pate" , apellido_pat.getText().toString());
                 parametros.putString("mate" , apellido_mat.getText().toString());parametros.putString("corre", nombre.getText().toString());
                 parametros.putString("fena" , fecha_naci.getText().toString());
                 parametros.putFloat("peso" , Float.parseFloat(peso.getText().toString()));
                 parametros.putFloat("esta" , Float.parseFloat(estatura.getText().toString()));

                Intent i = new Intent(getApplicationContext(), Usuario.class);
                i.putExtras(parametros);
                startActivity(i);
                finish();
            }
        });

        //Se generá otro Evento en el cual regresa a la interfaz (InicioSesion)
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
