package com.example.ivan.glucometric;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by ninte on 09/04/2018.
 */

public class Insertar extends SQLiteOpenHelper{

    public static abstract class DatosTabla implements BaseColumns{
        public static final String NOMBRE_TABLA = "Usuario";
        public static final String COLUMNA_ID = "id";
        public static final String COLUMNA_NOMBRE = "nombre";
        public static final String COLUMNA_APELLIDO_PATERNO = "aPaterno";
        public static final String COLUMNA_APELLIDO_MATERNO = "aMaterno";

        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";
        private static final String CREAR_TABLA_1 = "CREATE TABLE " + DatosTabla.NOMBRE_TABLA + " (" + DatosTabla.COLUMNA_ID + " INTEGER PRIMARY KEY," + DatosTabla.COLUMNA_NOMBRE + TEXT_TYPE + COMMA_SEP + DatosTabla.COLUMNA_APELLIDO_PATERNO + TEXT_TYPE + DatosTabla.COLUMNA_APELLIDO_MATERNO + TEXT_TYPE + ")";

        private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DatosTabla.NOMBRE_TABLA;
    }

    public static final int DATABASE_Version = 1;
    public static final String DATABASE_NAME = "Gluco.db";

    public Insertar(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DatosTabla.CREAR_TABLA_1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DatosTabla.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
