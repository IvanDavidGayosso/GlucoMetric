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
    String[][] datos;

    public Adaptador(Context contexto,String datos[][] ){
        this.contexto=contexto;
        this.datos=datos;
        inflater=(LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        final View vista= inflater.inflate(R.layout.elemento_lista,null);
        TextView cedula = (TextView) vista.findViewById(R.id.tv_cedula);
        TextView nombre = (TextView) vista.findViewById(R.id.tv_nombre);
        nombre.setText(datos[i][0]);
        cedula.setText(datos[i][1]);


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
