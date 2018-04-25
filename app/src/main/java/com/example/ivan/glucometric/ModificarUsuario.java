package com.example.ivan.glucometric;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

//Clase modificar usuario en la cual se crean las variables en las que el usuario sera modificado mediante los 
//campos de texto y a su vez los botones
public class ModificarUsuario extends AppCompatActivity {
    Button btn_aceptar,btn_cancelar;
    EditText id,nombre,ap,am,fe_na,peso,esta;
    BaseDeDatos basedatos;
    ArrayList<String> datos;
    //Metodo en el cual se crea una instancia para llamar a los valores antes generados; al final de esta 
    //instancia se genera la base de datos y se manda a llamar al metodo (buscar)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registro_usuario);
        btn_aceptar = (Button)findViewById(R.id.btnAceptar);
        nombre = (EditText) findViewById(R.id.nombre);
        ap = (EditText) findViewById(R.id.ap);
        am = (EditText) findViewById(R.id.am);
        fe_na=(EditText) findViewById(R.id.fecha_nac);
        peso=(EditText) findViewById(R.id.et_peso);
        esta = (EditText) findViewById(R.id.et_estatura);
        basedatos = new BaseDeDatos(this);
        datos=new ArrayList();
        buscar();

        //Esta opción generará el codigo para realizar el cambio de interfaz (Menu Principal)
        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent id_usuario= getIntent();
                basedatos.actualizar(id_usuario.getStringExtra("IDUSUARIO"), nombre.getText().toString(), ap.getText().toString(), am.getText().toString(), fe_na.getText().toString(), Float.parseFloat(peso.getText().toString()), Float.parseFloat(esta.getText().toString()), true);
                Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class);
                intent.putExtra("IDUSUARIO", datos.get(0));
                startActivity(intent);
                finish();
            }

        });
    }

    //Se crea el metodo buscar el cual obtendra los datos del usuario para mostrar los datos que se crearon con anterioridad
    public  void buscar(){
        Intent id_usuario= getIntent();
        datos=basedatos.buscar(basedatos.USUARIOS,basedatos.ID_USUARIO,id_usuario.getStringExtra("IDUSUARIO"));
        System.out.println(datos);
        nombre.setText(datos.get(1));
        ap.setText(datos.get(2));
        am.setText(datos.get(3));
        fe_na.setText(datos.get(4));
        peso.setText(datos.get(5));
        esta.setText(datos.get(6));
    }
}
