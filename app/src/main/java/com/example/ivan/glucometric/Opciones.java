package com.example.ivan.glucometric;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */

//Clase de opciones donde se crea un ArrayList, para guardar una serie de variables
public class Opciones extends AppCompatActivity {

    View opciones;
    ListView opciones_lista;
    String[] nombres_lista={"Ivan David ", "Emmanuel Resendiz"};
    
    public Opciones() {
        // Required empty public constructor
    }
    //Metodo en el cual se genera el ArrayList y se manda a llamar de la Interfaz (Usuarios)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_opciones);

        opciones_lista = (ListView) opciones.findViewById(R.id.lista);
        //opciones_lista.setAdapter(new Adaptador(this,this,nombres_lista));


    }


}
