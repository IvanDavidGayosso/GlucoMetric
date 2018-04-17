package com.example.ivan.glucometric;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.Principal;

//Clase der la interfaz (InicioSesion) en el cual se declaran los componentes de la interfaz.
public class InicioSesion extends AppCompatActivity {
    Fragment registrar;
    Button  btnReg,btnAcce;
    static String OPCION="opcion";

    BaseDeDatos basedatos;
    SQLiteDatabase db;
    EditText usuario,contrasena;


    //Se guarda lo que se obtuvo en la interfaz mediante las variables generadas con anterioridad.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_inicio_sesion);
        usuario = (EditText) findViewById(R.id.et_usuario) ;
        contrasena = (EditText) findViewById(R.id.et_contrasena) ;

        basedatos = new BaseDeDatos(this);


        //Realizará la operacion de boton al momento del Click.
        //La acción que se realiza es ir de la interfaz (InisioSesion) a la interfaz (Registro)
        btnReg = (Button) findViewById(R.id.btnRegistro);
        btnReg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(InicioSesion.this, RegistroUsuario.class);
                startActivity(intent);
                finish();
            }
        });

        //La variable del boton es igual alboton de la interfaz (InisioSesion)
        //Aquí esta el Evento del botón Aceptar. Al momento de dar clic la aplicación busca similitud entre el usuario y la contraseña.
        //Posteriormente el usuario entrará a la interfaz (MenuPrincipal)
        btnAcce = (Button) findViewById(R.id.btnAcceder);
        btnAcce.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!basedatos.buscar(usuario.getText().toString(),contrasena.getText().toString()).equals("null")) {
                    Intent intent = new Intent(InicioSesion.this, MenuPrincipal.class);
                    intent.putExtra("IDUSUARIO",basedatos.buscar(usuario.getText().toString(),contrasena.getText().toString()));
                    startActivity(intent);

                    //Al no ser iguales la contraseña y el usuario se muestra el mensaje para que el usuario verifique sus datos
                }else{
                    Toast.makeText(getApplicationContext(), "Verifique sus datos " , Toast.LENGTH_LONG).show();

                }
            }
        });

    }

}
