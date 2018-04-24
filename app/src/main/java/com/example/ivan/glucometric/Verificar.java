package com.example.ivan.glucometric;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.EditText;
import android.widget.Toast;

public class Verificar {
    Context context;
    public Verificar(Context context){
        this.context= context;
    }


    public boolean verificarCaracteres(EditText editex, Integer infe, Integer max){
        boolean correcto=false;

        if(editex.getText().toString().length() >= infe && editex.getText().toString().length()<=max){
            correcto=true;
        }else{
            Toast.makeText(context.getApplicationContext(), "Verificar dato", Toast.LENGTH_LONG).show();
            editex.requestFocusFromTouch();



        }

        return correcto;
    }
}
