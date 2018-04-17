package com.example.ivan.glucometric;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class ListaElementos extends AppCompatActivity {
    BaseDeDatos basedatos;
    ListView lista;
    String id_usu;
    String[][] datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_elementos);
        lista=(ListView) findViewById(R.id.lv_lista);
        Intent valor = this.getIntent();
        basedatos = new BaseDeDatos(this);
        id_usu = valor.getStringExtra("IDUSUARIO");
        BaseDeDatos datos_lista =new  BaseDeDatos(this);
        try{
        datos = datos_lista.buscarTodo("medicos","id_usuario",id_usu);
        lista.setAdapter(new Adaptador(this,datos));
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent usuario = new Intent(view.getContext(),GuardarMedico.class);
               startActivity(usuario);
           }
       });
        }catch (RuntimeException error){

        }
    }
}
