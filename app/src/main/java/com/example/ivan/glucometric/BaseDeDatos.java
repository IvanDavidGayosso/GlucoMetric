package com.example.ivan.glucometric;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

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
    public static final String GLUCOMETRIC = "glucometric.db";
    public static final Integer VERSION = 1;
    Context context;

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
                                                +FECHA_PASS + " DATE NOT NULL,"
                                                +USUARIO+ " TEXT NOT NULL, "
                                                +ID_USUARIO + " INTEGER NOT NULL,"
                                                +"FOREIGN KEY ("+ ID_USUARIO +") REFERENCES " + USUARIOS + "("+ ID_USUARIO + "));";

    //Tabla Estado Glucosa
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
    //Tabla Dispositivos
    public static final String DISPOSITIVOS = "dispositivos";
    public static final String SERIE = "serie";
    public static final String MODELO ="modelo";
    public static final String MAC="mac";
    public static final String DESCRIPCION ="valor_maximo";
    static final String TABLA_DISPOSITIVOS = "CREATE TABLE IF NOT EXISTS " + DISPOSITIVOS+ "("
                                            +SERIE+ " TEXT PRIMARY KEY ,"
                                            +MODELO + " TEXT NOT NULL ,"
                                            +MAC + " TEXT NOT NULL ,"
                                            +DESCRIPCION+ " TEXT NOT NULL, "
                                            +ID_USUARIO + " INTEGER NOT NULL,"
                                            +"FOREIGN KEY ("+ ID_USUARIO +") REFERENCES " + USUARIOS + "("+ ID_USUARIO + "));";

    //Tabla Registro de la Glucosa
    public static final String REGISTROS_GLUCOSA = "registros_glucosa";
    public static final String ID_REGISTRO_GLUCOSA= "id_registro_glucosa";
    public static final String FECHA ="fecha";
    public static final String HORA ="hora";
    public static final String VALOR="valor";
    public static final String UNIDAD_MEDIDA ="unidad_medida";
    static final String TABLA_REGISTRO_GLUCOSA = "CREATE TABLE IF NOT EXISTS " + REGISTROS_GLUCOSA+ " ("
                                                +ID_REGISTRO_GLUCOSA+ " INTEGER PRIMARY KEY ,"
                                                +FECHA + " DATE NOT NULL ,"
                                                +HORA + " TIME NOT NULL ,"
                                                +VALOR+ " FLOAT NOT NULL,"
                                                +UNIDAD_MEDIDA + " TEXTO NOT NULL,"
                                                +SERIE + " INTEGER NOT NULL, "
                                                +ID_ESTADO + " INTEGER NOT NULL,"
                                                +"FOREIGN KEY ("+ SERIE +") REFERENCES " + DISPOSITIVOS + "("+ SERIE + "),"
                                                +"FOREIGN KEY ("+ ID_ESTADO +") REFERENCES " + ESTADOS_GLUCOSA + "("+ ID_ESTADO + "));";

    
    //Tabla Doctor
    public static final String CEDULA="cedula";
    public static final String MEDICOS = "medicos";
    public static final String TABLA_MEDICOS="CREATE TABLE IF NOT EXISTS " + MEDICOS + "("
                                                +CEDULA+ " INTEGER PRIMARY KEY ,"
                                                +NOMBRE + " DATE NOT NULL ,"
                                                +APELL_PATERNO + " TIME NOT NULL ,"
                                                +APELL_MATERNO+ " INTEGER NOT NULL,"
                                                +CORREO_ELECTRONICO + "TEXTO NOT NULL,"
                                                +ID_USUARIO + " INTEGER NOT NULL, "
                                                +ESTADO + " INTEGER NOT NULL,"
                                                +"FOREIGN KEY ("+ ID_USUARIO +") REFERENCES " + USUARIOS + "("+ ID_USUARIO+ "));";

    //Elimina la base de datos "GLUCOMETRIC" si esta la existe
    public static final String DROP_DATABASE="DROP TABLE IF EXISTS "+GLUCOMETRIC+";";



    public BaseDeDatos(Context context) {
        super(context, GLUCOMETRIC, null,VERSION );
        this.context = context;

    }

    //Crea las tablas de la base de datos db
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_USUARIOS);
        db.execSQL(TABLA_CONTRASENAS);
        db.execSQL(TABLA_ESTADOS_GLUCOSA);
        db.execSQL(TABLA_DISPOSITIVOS);
        db.execSQL(TABLA_REGISTRO_GLUCOSA);
        db.execSQL(TABLA_MEDICOS);
    }

    //Generará una actualización de la version antigua de la base de datos db
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Mandará a llamar los valores antes generados para crear la inserción de los campos de texto y llevarlos a la base de datos en la tabla usuarios.
    //Creado el registro se imprimirá el mensaje de que la operación se llevó con éxito
    public void insertar( String nom, String ap_pa, String ap_ma, String co_el, String fe_na, Float pe, Float est, Boolean es){
        SQLiteDatabase db = this.getWritableDatabase();
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
        System.out.println("Ya insertooooooooooooooo");
        db.close();
    }

    //Se generá una actualización en la base de datos, tomando en cuenta los mismos datos que la anterior metodo
    public void actualizar(String iden, String nom, String ap_pa, String ap_ma, String co_el, String fe_na, Float pe, Float est, Boolean es){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOMBRE , nom);
        values.put(APELL_PATERNO, ap_pa);
        values.put(APELL_MATERNO , ap_ma);
        values.put(CORREO_ELECTRONICO, co_el);
        values.put(FECHA_NACIMIENTO , fe_na);
        values.put(PESO , pe);
        values.put(ESTATURA , est);
        values.put(ESTADO , es);
        String[] id = {iden};
        db.update(USUARIOS, values, ID_USUARIO + " = ?",id);
        db.close();
        Toast.makeText(context.getApplicationContext(), "Se modifico con exito " , Toast.LENGTH_LONG).show();
    }

    //Se creará una busqueda de la misma tabla de usuarios, en este caso se obtendra una consulta mediante un cursor que permita buscar al usuario y la contraseña
    public String buscar(String usu,String pass) {

        try {
            String id = "null";
            String[] columns = {ID_USUARIO};
            SQLiteDatabase db = this.getWritableDatabase();
            String selection = USUARIO + " = ? and " + PASSWORD + "=?";
            String[] selectionArgs = {usu, pass};
            Cursor cursor = db.query(CONTRASENAS, //Table to query
                    columns,                    //columns to return
                    selection,                  //columns for the WHERE clause
                    selectionArgs,              //The values for the WHERE clause
                    null,                       //group the rows
                    null,                      //filter by row groups
                    null);                      //The sort order
            cursor.moveToFirst();
            id = cursor.getString(0);

            db.close();
            return id;
        }catch (CursorIndexOutOfBoundsException error){
            return "null";
        }
    }

    //En este caso se obtendrá la inserción de la contraseña para así tener como entrar a la cuenta
    public void insertar( String usu, String con,String fec,Integer id_usu){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(PASSWORD , con);
        values.put(FECHA_PASS, fec);
        values.put(USUARIO , usu);
        values.put(ID_USUARIO,id_usu);
        db.insert(CONTRASENAS,null,values);
        db.close();
        Toast.makeText(context.getApplicationContext(), "Se guardo con exito " , Toast.LENGTH_LONG).show();
    }

    public String buscar(String nom,String ap, String am){

        // array of columns to fetch
        String id="null";
        String[] columns = {ID_USUARIO};
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = NOMBRE + " = ? and "+APELL_PATERNO+ "= ? and " +APELL_PATERNO +" = ?";
        String[] selectionArgs = {nom, ap, am};
        System.out.println("Datos recididos "+ nom +" "+ap+" "+am);
        Cursor cursor = db.query(USUARIOS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order

            if (cursor.moveToFirst()) {
                id = cursor.getString(0);
                System.out.println("++++++ hola " + id);
            }else{
                System.out.println("--------- Algo salio mal");
            }

        cursor.close();
        db.close();
        return id;
    }

    public ArrayList<String> buscar( String tabla, String columna, Integer valor){

        // array of columns to fetch
        ArrayList<String> datos = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = columna + " = ? ";
        String[] selectionArgs = {valor.toString()};
        Cursor cursor = db.query(tabla, //Table to query
                null,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getColumnCount();

        cursor.moveToFirst();

        if (cursor.moveToFirst()) {
            for(int i=0;i<cursorCount;i++)
            datos.add(i,cursor.getString(i));
        }

        cursor.close();
        db.close();
        return datos;
    }

}
