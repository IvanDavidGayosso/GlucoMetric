
package com.example.ivan.glucometric;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//Clase en la que se crean los valores de los campos de texto, botones y la base de datos
public class Usuario extends AppCompatActivity {
    BaseDeDatos basedatos;
    EditText usuario,contrasena,rep_contra;
    Button aceptar,cancelar;

    //Se enalazan las variables creadas en la clase con los botones y campos de texto de la interfaz (Usuario)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        basedatos = new BaseDeDatos(this);
        usuario =(EditText) findViewById(R.id.et_usuario);
        contrasena =(EditText) findViewById(R.id.et_contrasena);
        rep_contra =(EditText) findViewById(R.id.et_rep_contra);
        aceptar = (Button) findViewById(R.id.btnAceptar);
        cancelar = (Button) findViewById(R.id.btnCancelar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            //Se generá un evento al dat clic para insertar los datos a la base de datos usuario y contraseña para posteriormente ser llamada
            //por otra Interfaz
            @Override
            public void onClick(View v) {

                final Bundle parametros = getIntent().getExtras();
                basedatos.insertarUsuario(parametros.getString("nombre"),parametros.getString("pate"),parametros.getString("mate"), parametros.getString("fena"), parametros.getFloat("peso"), parametros.getFloat("esta"), true);
                System.out.println(parametros.getString("nombre")+parametros.getString("pate")+parametros.getString("mate"));
                String id = basedatos.buscarId(parametros.getString("nombre"),parametros.getString("pate"),parametros.getString("mate"));
                basedatos.insertar(usuario.getText().toString(),contrasena.getText().toString(),"2018/02/01",Integer.parseInt(id));
                Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class);
                intent.putExtra("IDUSUARIO",id);
                startActivity(intent);
                finish();

            }
        });



    }
}
