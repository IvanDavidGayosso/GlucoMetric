package com.example.ivan.glucometric;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BaseDeDatos extends SQLiteOpenHelper{
/*
    create table contrasenas(
            id_pass				int	primary key auto_increment,
            password			varchar(20) not null,
    fecha_pass			date	not null,
    id_usuario			int		not null,
    foreign key (id_usuario) references usuarios(id_usuario)
            );*/
    //CREACION DE LA BASE DE DATOS DE MANERA LOCAL
        //Base de datos
    public static final String GLUCOMETRIC = "GLUCOMETRIC";

        //Tabla usuario
    public static final String ID_USUARIO = "id_usuario";
    public static final String CORREO_ELECTRONICO = "correo_electronico";
    public static final String NOMBRE = "nombre";
    public static final String APELL_PATERNO = "apell_paterno";
    public static final String APELL_MATERNO = "apell_materno";
    public static final String FECHA_NACIMIENTO = "fecha_nacimiento";
    public static final String PESO = "peso";
    public static final String ESTATURA = "estatura";
    public static final String ESTADO = "estado";
    public static final String USUARIOS="usuarios";
    public static final String TABLA_USUARIOS = "CREATE TABLE IF NOT EXISTS " + USUARIOS + "("
                                                + ID_USUARIO + " INTEGER PRIMARY KEY ,"
                                                + NOMBRE + " TEXT NOT NULL,"
                                                + APELL_PATERNO + " TEXT NOT NULL,"
                                                + APELL_MATERNO + " TEXT NOT NULL,"
                                                + CORREO_ELECTRONICO + " TEXT NOT NULL,"
                                                + FECHA_NACIMIENTO + " DATE NOT NULL,"
                                                + PESO + " FLOAT NOT NULL ,"
                                                + ESTATURA + " FLOAT NOT NULL ,"
                                                + ESTADO + " BOOLEAN NOT NULL );";


    //Tabla contraseñas
    public static final String CONTRASENAS =  "contrasenas";
    public static final String ID_PASS = "id_pass";
    public static final String PASSWORD = "password";
    public static final String FECHA_PASS = "fecha_pass";
    public static final String USUARIO = "usuario";
    public static final String TABLA_CONTRASENAS = "CREATE TABLE IF NOT EXISTS " + CONTRASENAS+ "("
                                                +ID_PASS + " INTEGER PRIMARY KEY,"
                                                +PASSWORD + " TEXT NOT NULL ,"
                                                +FECHA_PASS + " DATE NOT NULL ,"
                                                +USUARIO+ " TEXT NOT NULL, "
                                                +ID_USUARIO + " INTEGER NOT NULL,"
                                                +"FOREIGN KEY ("+ ID_USUARIO +") REFERENCES " + USUARIOS + "("+ ID_USUARIO + "));";


    public static final String ESTADOS_GLUCOSA = "estados_glucosa";
    public static final String ID_ESTADO = "id_estado";
    public static final String ESTADO_GLUCOSA ="estado_glucosa";
    public static final String VALOR_MINIMO="valor_minimo";
    public static final String VALOR_MAXIMO ="valor_maximo";
    public static final String TABLA_ESTADOS_GLUCOSA="CREATE TABLE IF NOT EXISTS "+ ESTADOS_GLUCOSA+" ("
                                                    +ID_ESTADO + " INTEGER PRIMARY KEY ,"
                                                    +ESTADO_GLUCOSA + " TEXT NOT NULL, "
                                                    +VALOR_MINIMO + " FLOAT(5,2) NOT NULL,"
                                                    +VALOR_MAXIMO+ " FLOAT(5,2) );";

    public static final String DISPOSITIVOS = "dispositivos";
    public static final String SERIE = "id_estado";
    public static final String MODELO ="estado_glucosa";
    public static final String MAC="valor_minimo";
    public static final String DESCRIPCION ="valor_maximo";
    static final String TABLA_DISPOSITIVOS = "CREATE TABLE IF NOT EXISTS " + DISPOSITIVOS+ "("
                                            +SERIE+ " TEXT PRIMARY KEY ,"
                                            +MODELO + " TEXT NOT NULL ,"
                                            +MAC + " TEXT NOT NULL ,"
                                            +DESCRIPCION+ " TEXT NOT NULL, "
                                            +ID_USUARIO + " INTEGER NOT NULL,"
                                            +"FOREIGN KEY ("+ ID_USUARIO +") REFERENCES " + USUARIOS + "("+ ID_USUARIO + "));";

    public static final String REGISTROS_GLUCOSA = "registros_glucosa";
    public static final String ID_REGISTRO_GLUCOSA= "id_registro_glucosa";
    public static final String FECHA ="fecha";
    public static final String HORA ="hora";
    public static final String VALOR="valor";
    public static final String UNIDAD_MEDIDA ="unidad_medida";
    static final String TABLA_REGISTRO_GLUCOSA = "CREATE TABLE IF NOT EXISTS " + REGISTROS_GLUCOSA+ "("
                                                +ID_REGISTRO_GLUCOSA+ " INTEGER PRIMARY KEY ,"
                                                +FECHA + " DATE NOT NULL ,"
                                                +HORA + " TIME NOT NULL ,"
                                                +VALOR+ " INTEGER NOT NULL,d"
                                                +UNIDAD_MEDIDA + "TEXTO NOT NULL"
                                                +SERIE + " TEXT NOT NULL, "
                                                +ID_USUARIO + " INTEGER NOT NULL,"
                                                +"FOREIGN KEY ("+ ID_USUARIO +") REFERENCES " + USUARIOS + "("+ ID_USUARIO + "),"
                                                +"FOREIGN KEY ("+ ID_ESTADO +") REFERENCES " + ESTADOS_GLUCOSA + "("+ ID_ESTADO+ "));";;



    public BaseDeDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_USUARIOS);
        db.execSQL(TABLA_CONTRASENAS);
        db.execSQL(TABLA_ESTADOS_GLUCOSA);
        db.execSQL(TABLA_DISPOSITIVOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insetar(Context context, SQLiteDatabase db, String nom, String ap_pa, String ap_ma, String co_el, String fe_na, Float pe, Float est, Boolean es){
        ContentValues values= new ContentValues();
        values.put(NOMBRE , nom);
        values.put(APELL_PATERNO, ap_pa);
        values.put(APELL_MATERNO , ap_ma);
        values.put(CORREO_ELECTRONICO, co_el);
        values.put(FECHA_NACIMIENTO , fe_na);
        values.put(PESO , pe);
        values.put(ESTATURA , est);
        values.put(ESTADO , es);
        db.insert(USUARIOS,null,values);
        Toast.makeText(context.getApplicationContext(), "Se guardo con exito " , Toast.LENGTH_LONG).show();
    }

}
