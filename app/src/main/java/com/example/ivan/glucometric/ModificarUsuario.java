package com.example.ivan.glucometric;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class ModificarUsuario extends AppCompatActivity {
    Button btn_aceptar,btn_cancelar;
    EditText id,nombre,ap,am;
    String ID;
    BaseDeDatos basedatos;
    ArrayList<String> datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registro_usuario);
        btn_aceptar = (Button)findViewById(R.id.btnAceptar);
        nombre = (EditText) findViewById(R.id.nombre);
        ap = (EditText) findViewById(R.id.ap);
        am = (EditText) findViewById(R.id.am);
        basedatos = new BaseDeDatos(this);
        datos=new ArrayList();
        buscar();

        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent id_usuario= getIntent();
                basedatos.actualizar(id_usuario.getStringExtra("IDUSUARIO"), nombre.getText().toString(), ap.getText().toString(), am.getText().toString(), "hj", "1997/04/03", 60.8f, 1.6f, true);
                Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class);
                intent.putExtra("IDUSUARIO", datos.get(0));
                startActivity(intent);
                finish();
            }

        });
    }

    public  void buscar(){
        Intent id_usuario= getIntent();
        datos=basedatos.buscar(basedatos.USUARIOS,basedatos.ID_USUARIO,Integer.parseInt(id_usuario.getStringExtra("IDUSUARIO")));
        System.out.println(datos);
        nombre.setText(datos.get(1));
        ap.setText(datos.get(2));
        am.setText(datos.get(3));
        System.out.println(datos.get(4));
        System.out.println(datos.get(5));
        System.out.println(datos.get(6));
        System.out.println(datos.get(7));
        System.out.println(datos.get(8));
    }
}
