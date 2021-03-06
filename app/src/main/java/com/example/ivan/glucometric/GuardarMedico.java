package com.example.ivan.glucometric;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent ;


public class GuardarMedico extends AppCompatActivity {
    BaseDeDatos basedatos;
    EditText nombre,et_cedula,ap,am,correo;
    Button btn_aceptar,btn_cancelar;
    String id;
    Verificar ver;

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
        ver = new Verificar(this);
        id=parametros.getStringExtra("IDUSUARIO");
        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ver.verificarCaracteres(et_cedula,4,8)==true){
                    if(ver.verificarCaracteres(nombre,3,25)==true){

                basedatos.insetarDoctor(et_cedula.getText().toString(),nombre.getText().toString(),ap.getText().toString(),am.getText().toString(),correo.getText().toString(),true, id);
                Intent intent = new Intent(GuardarMedico.this, ListaMedicos.class);
                intent.putExtra("IDUSUARIO",id);
                startActivity(intent);
                finish();
                }
            }
            }
        });

    }


}
