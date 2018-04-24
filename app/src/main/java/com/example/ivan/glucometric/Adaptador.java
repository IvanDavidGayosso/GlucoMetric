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

//Clase en la cual se encuentran las variables.
//Extencion al un adaptador
public class Adaptador extends BaseAdapter{
    private static LayoutInflater inflater = null;
    Context contexto;
    String[][] datos;

    //Constructor del Adaptador
    public Adaptador(Context contexto,String datos[][] ){
        this.contexto=contexto;
        this.datos=datos;
        inflater=(LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);

    }

    //Metodo que manda a llamar de una interfaz los datos y los guarda en las variables
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        final View vista= inflater.inflate(R.layout.elemento_lista,null);
        TextView cedula = (TextView) vista.findViewById(R.id.tv_cedula);
        TextView nombre = (TextView) vista.findViewById(R.id.tv_nombre);
        nombre.setText(datos[i][0]);
        cedula.setText(datos[i][1]);


        return vista;
    }

    //Metodo que retorna los datos
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
