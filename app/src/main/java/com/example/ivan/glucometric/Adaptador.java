package com.example.ivan.glucometric;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;


public class Adaptador extends BaseAdapter{
    private static LayoutInflater inflater = null;
    Context contexto;
    String[] datos;
    Fragment guardar_medico,opciones;
    Integer id;
    GuardarMedico guardar_medico_class;

    public Adaptador(Context contexto,Fragment opciones, String[] datos){
        this.contexto=contexto;
        this.datos=datos;
        this.opciones=opciones;
        inflater=(LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        final View vista= inflater.inflate(R.layout.elemento_lista,null);
        this.id=i;
        TextView nombre = (TextView) vista.findViewById(R.id.tv_nombre);
        ImageButton editar = (ImageButton) vista.findViewById(R.id.ib_editar);
        ImageButton eliminar = (ImageButton) vista.findViewById(R.id.ib_eliminar);
        nombre.setText(datos[i]);
        editar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                FragmentManager mi_manejador = opciones.getFragmentManager();
                FragmentTransaction mi_transaccion= mi_manejador.beginTransaction();
                mi_transaccion.replace(R.id.opciones_usuario,guardar_medico).commit();
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //nooo
            }

        });

        return vista;
    }

    @Override
    public int getCount() {
        return datos.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



}
