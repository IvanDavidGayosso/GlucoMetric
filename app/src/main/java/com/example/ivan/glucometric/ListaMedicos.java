package com.example.ivan.glucometric;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListaMedicos extends AppCompatActivity {
    ListView lista;
    BaseDeDatos basedatos;
    List<String> item=null;
    List<String> cedula_key=null;
    String usuario;
    final List<String[]> lista_medico = new LinkedList<String[]>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medicos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent menu = getIntent();
        usuario=menu.getStringExtra("IDUSUARIO");
        lista=(ListView) findViewById(R.id.lv_lista);
        showMedicos();
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i= new Intent(ListaMedicos.this, ModificarMedico.class);
                i.putExtra("IDUSUARIO",usuario);
                i.putExtra("CEDULA",cedula_key.get(position));
                startActivity(i);

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(ListaMedicos.this,GuardarMedico.class);
                i.putExtra("IDUSUARIO",usuario);
                startActivity(i);
                finish();
            }
        });
    }

    public void showMedicos(){
        basedatos= new BaseDeDatos(this);
        Cursor c = basedatos.getMedicos(usuario);
        item = new ArrayList<String>();
        cedula_key = new ArrayList<String>();
        String cedula = "",nombre="";
        if(c.moveToFirst()){
            do{
                cedula_key.add(c.getString(0));
                lista_medico.add(new String[]{c.getString(0),c.getString(1)+" "+c.getString(2)+" "+c.getString(3)});
            }while (c.moveToNext());
        }

        ArrayAdapter<String[]> adaptador = new ArrayAdapter<String[]>(this,android.R.layout.simple_list_item_2,android.R.id.text1,lista_medico){
            @Override
            public View getView(int position,View convertView, ViewGroup parent){
                View view=super.getView(position,convertView,parent);

                String[] entry = lista_medico.get(position);
                TextView text1=(TextView) view.findViewById(android.R.id.text1);
                TextView text2=(TextView) view.findViewById(android.R.id.text2);
                text1.setText(entry[0]);
                text2.setText(entry[1]);


                return view;
            }
        };




        lista.setAdapter(adaptador);

    }

}
