package com.example.ivan.glucometric;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


public class ModificarMedico extends AppCompatActivity {
    BaseDeDatos basedatos;
    EditText nombre,et_cedula,ap,am,correo;
    Button btn_aceptar,btn_cancelar;
    String id,cedula;
    ArrayList<String> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_guardar_medico);
        basedatos = new BaseDeDatos(this);
        et_cedula=(EditText) findViewById(R.id.et_cedula);
        nombre = (EditText) findViewById(R.id.et_nombre_med);
        ap = (EditText) findViewById(R.id.et_apell_pat_med);
        am = (EditText) findViewById(R.id.ed_apell_mat_med);
        correo = (EditText) findViewById(R.id.et_correo);
        btn_aceptar = (Button) findViewById(R.id.btnAceptar);
        btn_cancelar = (Button) findViewById(R.id.btnCancelar);
        Intent parametros = this.getIntent();
        id=parametros.getStringExtra("IDUSUARIO");
        cedula= parametros.getStringExtra("CEDULA");
        System.out.println(id +" Listo "+cedula);
        this.buscar();
        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basedatos.modificarDoctor(et_cedula.getText().toString(),nombre.getText().toString(),ap.getText().toString(),am.getText().toString(),correo.getText().toString());
                Intent intent = new Intent(ModificarMedico.this, ListaMedicos.class);
                intent.putExtra("IDUSUARIO",id);
                startActivity(intent);
                finish();

            }
        });

    }

    public  void buscar(){
        datos=basedatos.buscar("medicos","cedula",cedula);
        et_cedula.setText(datos.get(0));
        nombre.setText(datos.get(1));
        ap.setText(datos.get(2));
        am.setText(datos.get(3));
        correo.setText(datos.get(4));

    }

}
