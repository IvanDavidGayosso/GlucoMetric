
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
    SQLiteDatabase db;
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
        final Bundle parametros = this.getIntent().getExtras();
        aceptar.setOnClickListener(new View.OnClickListener() {
            //Se generá un evento al dat clic para insertar los datos a la base de datos usuario y contraseña para posteriormente ser llamada
            //por otra Interfaz
            @Override
            public void onClick(View v) {
                basedatos.insertar(parametros.getString("nombre"),parametros.getString("ap_pa"),parametros.getString("ap_ma"), "hj", "1997/04/03", 60.8f, 1.6f, true);
                String id = basedatos.buscar(parametros.getString("nombre"),parametros.getString("ap_pa"),parametros.getString("ap_ma"));
                basedatos.insertar(usuario.getText().toString(),contrasena.getText().toString(),"2018/02/01",Integer.parseInt(id));
                Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class);
                intent.putExtra("IDUSUARIO",basedatos.buscar(usuario.getText().toString(),contrasena.getText().toString()));
                startActivity(intent);
                finish();

            }
        });



    }
}
