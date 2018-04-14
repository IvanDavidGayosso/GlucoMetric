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


public class RegistroUsuario extends AppCompatActivity {
    View registro_usuario;
    Button btn_aceptar,btn_cancelar;
    EditText id,nombre,ap,am;
    String ID;

    public RegistroUsuario() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registro_usuario);
        btn_aceptar = (Button)findViewById(R.id.btnAceptar);
        id = (EditText) findViewById(R.id.Id);
        nombre = (EditText) findViewById(R.id.et_nombre);
        ap = (EditText) findViewById(R.id.et_apell_pat);
        am = (EditText) findViewById(R.id.et_apell_pat);

        btn_aceptar = (Button) findViewById(R.id.btnAceptar);
        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                BaseDeDatos basedatos = new BaseDeDatos(getApplicationContext(),"glucometric.db",null,1);
                SQLiteDatabase db = basedatos.getWritableDatabase();
                basedatos.insetar(getApplicationContext(),db,nombre.getText().toString(),ap.getText().toString(),am.getText().toString(),"hj","1997/04/03",60.8f,1.6f,true);
                Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class);
                startActivity(intent);




            }
        });

        /*btn_cancelar = (Button) registro_usuario.findViewById(R.id.btnCancelar);
        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inicio_sesion = new InicioSesion();
                FragmentManager mi_manejador=getFragmentManager();
                FragmentTransaction mi_transaccion= mi_manejador.beginTransaction();
                mi_transaccion.replace(R.id.registrar,inicio_sesion).commit();

            }
        });*/
    }


}
