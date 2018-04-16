package com.example.ivan.glucometric;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class ListaElementos extends AppCompatActivity {
    ListView lista;
    String[][] datos={
            {"Ivan David Acosta Gayosso"," MCD88NNKK"},
            {"Brayan Emmanuel Recendiz Gonzale","94NKNLSFG"}
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_elementos);
        lista=(ListView) findViewById(R.id.lv_lista);
        lista.setAdapter(new Adaptador(this,datos));

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent usuario = new Intent(view.getContext(),GuardarMedico.class);

               startActivity(usuario);
           }
       });
    }
}
