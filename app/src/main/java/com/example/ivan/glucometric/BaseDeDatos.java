package com.example.ivan.glucometric;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class BaseDeDatos extends SQLiteOpenHelper {

    //CREACION DE LA BASE DE DATOS DE MANERA LOCAL
    //Base de datos
    public static final String GLUCOMETRIC = "glucometric.db";
    public static final Integer VERSION = 1;
    Context context;

    //Tabla usuario
    public static final String ID_USUARIO  = "id_usuario";
    public static final String CORREO_ELECTRONICO = "correo_electronico";
    public static final String NOMBRE = "nombre";
    public static final String APELL_PATERNO = "apell_paterno";
    public static final String APELL_MATERNO = "apell_materno";
    public static final String FECHA_NACIMIENTO = "fecha_nacimiento";
    public static final String PESO = "peso";
    public static final String ESTATURA = "estatura";
    public static final String ESTADO = "estado";
    public static final String USUARIOS = "usuarios";
    public static final String TABLA_USUARIOS = "CREATE TABLE IF NOT EXISTS " + USUARIOS + "("
            + ID_USUARIO + " INTEGER PRIMARY KEY ,"
            + NOMBRE + " TEXT NOT NULL,"
            + APELL_PATERNO + " TEXT NOT NULL,"
            + APELL_MATERNO + " TEXT NOT NULL,"
            + FECHA_NACIMIENTO + " DATE NOT NULL,"
            + PESO + " FLOAT NOT NULL ,"
            + ESTATURA + " FLOAT NOT NULL ,"
            + ESTADO + " BOOLEAN NOT NULL );";


    //Tabla contraseñas
    public static final String CONTRASENAS = "contrasenas";
    public static final String ID_PASS = "id_pass";
    public static final String PASSWORD = "password";
    public static final String FECHA_PASS = "fecha_pass";
    public static final String USUARIO = "usuario";
    public static final String TABLA_CONTRASENAS = "CREATE TABLE IF NOT EXISTS " + CONTRASENAS + "("
            + ID_PASS + " INTEGER PRIMARY KEY,"
            + PASSWORD + " TEXT NOT NULL ,"
            + FECHA_PASS + " DATE NOT NULL,"
            + USUARIO + " TEXT NOT NULL, "
            + ID_USUARIO + " INTEGER NOT NULL,"
            + "FOREIGN KEY (" + ID_USUARIO + ") REFERENCES " + USUARIOS + "(" + ID_USUARIO + "));";


    public static final String ESTADOS_GLUCOSA = "estados_glucosa";
    public static final String ID_ESTADO = "id_estado";
    public static final String ESTADO_GLUCOSA = "estado_glucosa";
    public static final String VALOR_MINIMO = "valor_minimo";
    public static final String VALOR_MAXIMO = "valor_maximo";
    public static final String TABLA_ESTADOS_GLUCOSA = "CREATE TABLE IF NOT EXISTS " + ESTADOS_GLUCOSA + " ("
            + ID_ESTADO + " INTEGER PRIMARY KEY ,"
            + ESTADO_GLUCOSA + " TEXT NOT NULL, "
            + VALOR_MINIMO + " FLOAT(5,2) NOT NULL,"
            + VALOR_MAXIMO + " FLOAT(5,2) );";


    public static final String REGISTROS_GLUCOSA = "registros_glucosa";
    public static final String ID_REGISTRO_GLUCOSA = "id_registro_glucosa";
    public static final String FECHA = "fecha";
    public static final String HORA = "hora";
    public static final String VALOR = "valor";
    public static final String UNIDAD_MEDIDA = "unidad_medida";
    static final String TABLA_REGISTRO_GLUCOSA = "CREATE TABLE IF NOT EXISTS " + REGISTROS_GLUCOSA + " ("
            + ID_REGISTRO_GLUCOSA + " INTEGER PRIMARY KEY ,"
            + FECHA + " DATE NOT NULL ,"
            + HORA + " TIME NOT NULL ,"
            + VALOR + " FLOAT NOT NULL,"
            + UNIDAD_MEDIDA + " TEXTO NOT NULL,"
            + ID_USUARIO + " INTEGER NOT NULL, "
            + ID_ESTADO + " INTEGER NOT NULL,"
            + "FOREIGN KEY (" + ID_USUARIO + ") REFERENCES " + USUARIOS + "(" + ID_USUARIO + "),"
            + "FOREIGN KEY (" + ID_ESTADO + ") REFERENCES " + ESTADOS_GLUCOSA + "(" + ID_ESTADO + "));";

    public static final String CEDULA = "cedula";
    public static final String MEDICOS = "medicos";
    public static final String TABLA_MEDICOS = "CREATE TABLE IF NOT EXISTS " + MEDICOS + " ("
            + CEDULA + " TEXTO PRIMARY KEY ,"
            + NOMBRE + " TEXTO NOT NULL ,"
            + APELL_PATERNO + " TEXTO NOT NULL ,"
            + APELL_MATERNO + " TEXTO NOT NULL,"
            + CORREO_ELECTRONICO + " TEXTO NOT NULL,"
            + ESTADO + " BOOLEAN NOT NULL,"
            + ID_USUARIO + " INTEGER NOT NULL, "
            + "FOREIGN KEY (" + ID_USUARIO + ") REFERENCES " + USUARIOS + " ( " + ID_USUARIO + " )) ;";


    public static  final String USUARIO_ACTIVO = "usuario_activo";
    public static final String CREATE_VIEW_USARIO_ACTIVO="CREATE VIEW IF NOT EXISTS "+USUARIO_ACTIVO+" AS "
            +"SELECT "+USUARIOS+"."+ID_USUARIO+" ,"+USUARIOS+"."+ESTADO+" ,"
            +CONTRASENAS+"."+USUARIO+" ,"+CONTRASENAS+"."+PASSWORD+" FROM "+USUARIOS
            +" INNER JOIN " +CONTRASENAS + " ON "+CONTRASENAS+"."+ID_USUARIO+" = "+USUARIOS+"."+ID_USUARIO+";";


    public static final String DROP_DATABASE = "DROP TABLE IF EXISTS " + GLUCOMETRIC + ";";

    public BaseDeDatos(Context context) {
        super(context, GLUCOMETRIC, null, VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_USUARIOS);
        db.execSQL(TABLA_CONTRASENAS);
        db.execSQL(TABLA_ESTADOS_GLUCOSA);
        db.execSQL(TABLA_REGISTRO_GLUCOSA);
        db.execSQL(TABLA_MEDICOS);
        db.execSQL(CREATE_VIEW_USARIO_ACTIVO);
        System.out.println(TABLA_MEDICOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.deleteDatabase(new File(GLUCOMETRIC));
        onCreate(db);
    }

    public void Eliminar(String tabla, String iden, String col) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ESTADO, 0);
        String[] id = {iden};
        db.update(tabla, values, col + " = ?", id);

        db.close();
    }

    public void insertarUsuario(String nom, String ap_pa, String ap_ma, String fe_na, Float pe, Float est, Boolean es) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOMBRE, nom);
        values.put(APELL_PATERNO, ap_pa);
        values.put(APELL_MATERNO, ap_ma);
        values.put(FECHA_NACIMIENTO, fe_na);
        values.put(PESO, pe);
        values.put(ESTATURA, est);
        values.put(ESTADO, es);
        db.insert(USUARIOS, null, values);
        db.close();
    }



    public void insetarEstado(String Estado, Integer min, Integer max) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ESTADO_GLUCOSA, Estado);
        values.put(VALOR_MINIMO, min);
        values.put(VALOR_MAXIMO, max);
        db.insert(ESTADOS_GLUCOSA, null, values);
        Toast.makeText(context.getApplicationContext(), "Se guardo con exito ", Toast.LENGTH_LONG).show();
        db.close();
    }

    public void insetarDoctor(String ced, String nom_doc, String ap_pa_doc, String ap_ma_doc, String co_el_doc, Boolean est, String id_usu) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CEDULA, ced);
        values.put(NOMBRE, nom_doc);
        values.put(APELL_PATERNO, ap_pa_doc);
        values.put(APELL_MATERNO, ap_ma_doc);
        values.put(CORREO_ELECTRONICO, co_el_doc);
        values.put(ESTADO, est);
        values.put(ID_USUARIO, id_usu);
        db.insert(MEDICOS, null, values);
        Toast.makeText(context.getApplicationContext(), "Se guardo con exito ", Toast.LENGTH_LONG).show();
        db.close();
    }

    public void modificarDoctor(String ced, String nom_doc, String ap_pa_doc, String ap_ma_doc, String co_el_doc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CEDULA, ced);
        values.put(NOMBRE, nom_doc);
        values.put(APELL_PATERNO, ap_pa_doc);
        values.put(APELL_MATERNO, ap_ma_doc);
        values.put(CORREO_ELECTRONICO, co_el_doc);
        String[]id = {ced};
        db.update(MEDICOS, values,CEDULA+" =? ",id);
        Toast.makeText(context.getApplicationContext(), "Se guardo con exito ", Toast.LENGTH_LONG).show();
        db.close();
    }

    public void insetarGlucosa(String fecha, String hora, String valor, String medida,String usu,Integer estado) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FECHA, fecha);
        values.put(HORA, hora);
        values.put(VALOR, valor);
        values.put(UNIDAD_MEDIDA, medida);
        values.put(ID_USUARIO,usu);
        values.put(ID_ESTADO,estado);
        db.insert(REGISTROS_GLUCOSA, null, values);
        Toast.makeText(context.getApplicationContext(), "Se guardo con exito ", Toast.LENGTH_LONG).show();
        db.close();
    }

    public void actualizar(String iden, String nom, String ap_pa, String ap_ma, String fe_na, Float pe, Float est, Boolean es) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOMBRE, nom);
        values.put(APELL_PATERNO, ap_pa);
        values.put(APELL_MATERNO, ap_ma);
        values.put(FECHA_NACIMIENTO, fe_na);
        values.put(PESO, pe);
        values.put(ESTATURA, est);
        values.put(ESTADO, es);
        String[] id = {iden};
        db.update(USUARIOS, values, ID_USUARIO + " = ?", id);
        db.close();
        Toast.makeText(context.getApplicationContext(), "Se modifico con exito ", Toast.LENGTH_LONG).show();
    }

    public String buscar(String usu, String pass) {

        try {
            String id = "null";
            String[] columns = {ID_USUARIO};
            SQLiteDatabase db = this.getWritableDatabase();
            String selection = USUARIO + " = ? and " + PASSWORD + "=? and "+ ESTADO + "=1";
            String[] selectionArgs = {usu, pass};
            Cursor cursor = db.query(USUARIO_ACTIVO, //Table to query
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
        } catch (CursorIndexOutOfBoundsException error) {
            return "null";
        }
    }

    public void insertar(String usu, String con, String fec, Integer id_usu) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PASSWORD, con);
        values.put(FECHA_PASS, fec);
        values.put(USUARIO, usu);
        values.put(ID_USUARIO, id_usu);
        db.insert(CONTRASENAS, null, values);
        db.close();
        Toast.makeText(context.getApplicationContext(), "Se guardo con exito ", Toast.LENGTH_LONG).show();
    }

    public String buscarId(String nom, String ap, String am) {

        // array of columns to fetch
        String id = "null";
        String[] columns = {ID_USUARIO};
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = NOMBRE + " =? and " + APELL_PATERNO + "=? and " + APELL_PATERNO + "= ?";
        String[] selectionArgs = {nom, ap, am};
        System.out.println("Datos recididos " + nom + " " + ap + " " + am);
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
        }

        cursor.close();
        db.close();
        return id;
    }

    public ArrayList<String> buscar(String tabla, String columna, String valor) {

        // array of columns to fetch
        ArrayList<String> datos = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = columna + " = ? ";
        String[] selectionArgs = {valor};
        Cursor cursor = db.query(tabla, //Table to query
                null,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getColumnCount();

        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursorCount; i++)
                datos.add(i, cursor.getString(i));
        }

        cursor.close();
        db.close();
        return datos;
    }

    public Cursor getMedicos(String id_usu) {
        //-- Realiza una busqueda
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columas = {CEDULA, NOMBRE, APELL_PATERNO, APELL_MATERNO};
        String seleccion = ID_USUARIO + "= ?";
        String[] selectionArgs = {id_usu};
        Cursor cursor = db.query(MEDICOS, columas, seleccion, selectionArgs, null, null, null);
        return cursor;

    }

    public  void crearEstado(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(ESTADOS_GLUCOSA, null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            if(!(cursor.getInt(0)==1)){
                insetarEstado("Bajo",0,79);
                insetarEstado("Normal",80,140);
                insetarEstado("Alto",141,600);
            }
        }

    }

    public Cursor mostrarRegistros(String id_usu){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(REGISTROS_GLUCOSA, null, null,null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                System.out.println(cursor.getString(3)+" "+cursor.getString(4));
            }while(cursor.moveToNext());

        }

        return cursor;
    }

}
