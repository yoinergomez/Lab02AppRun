package co.edu.udea.compumovil.gr02.lab02apprun.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pc1 on 22/03/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static  final String BD_NOMBRE = "BD_AppRun.sql";
    private static  final int BD_VERSION_ESQUEMA = 1;

    public static final String TABLA_USUARIO = "Usuario";
    public static final String TABLA_CARRERA = "Carrera";
    public static final String TABLA_USUARIO_CARRERA = "Usuario_Carrera";
    //campos de la la tabla Usuario
    public static final String ID_USUARIO_COLUMNA = "_id";
    public static final String NOMBRE_USUARIO_COLUMNA = "nombre";
    public static final String APELLIDO_USUARIO_COLUMNA = "apellido";
    public static final String PASSWORD_USUARIO_COLUMNA = "password";
    public static final String CORREO_USUARIO_COLUMNA= "correo";
    public static final String IMAGEN_USUARIO_COLUMNA= "imagen";
    //campos de la la tabla Carrera
    public static final String ID_CARRERA_COLUMNA = "_id";
    public static final String NOMBRE_CARRERA_COLUMNA = "nombre";
    public static final String DESCRIPCION_CARRERA_COLUMNA = "descripcion";
    public static final String DISTANCIA_CARRERA_COLUMNA = "distancia";
    public static final String LUGAR_CARRERA_COLUMNA = "lugar";
    public static final String FECHA_CARRERA_COLUMNA= "fecha";
    public static final String TELEFONO_CONTACTO_CARRERA_COLUMNA = "telefono_Contacto";
    public static final String CORREO_CONTACTO_CARRERA_COLUMNA = "correo_Contacto";
    //campos de la tabla Usuario_Carrera
    public static final String ID_USUARIO_CARRERA_COLUMNA = "_id";
    public static final String ID_CARRERA_USUARIO_CARRERA_COLUMNA = "id_Carrera";
    public static final String ID_USUARIO_USUARIO_CARRERA_COLUMNA = "id_Usuario";
    public static final String FECHA_REGISTRO_USUARIO_CARRERA_COLUMNA = "fecha_Registro";

    public static final String CREAR_TABLA_USUARIO="CREATE TABLE "
            + TABLA_USUARIO + "(" + ID_USUARIO_COLUMNA + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NOMBRE_USUARIO_COLUMNA + " TEXT NOT NULL, " + APELLIDO_USUARIO_COLUMNA + " TEXT NOT NULL, "
            + PASSWORD_USUARIO_COLUMNA + " TEXT NOT NULL, "
            + IMAGEN_USUARIO_COLUMNA + "BLOB,"
            + CORREO_USUARIO_COLUMNA + " TEXT NOT NULL );";

    public static final String CREAR_TABLA_CARRERA="CREATE TABLE "
            + TABLA_CARRERA + "(" + ID_CARRERA_COLUMNA + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NOMBRE_CARRERA_COLUMNA + " TEXT NOT NULL, " + DESCRIPCION_CARRERA_COLUMNA + " TEXT, "
            + DISTANCIA_CARRERA_COLUMNA + " INTEGER, " + LUGAR_CARRERA_COLUMNA + " TEXT NOT NULL, "
            + FECHA_CARRERA_COLUMNA + " DATE, " + TELEFONO_CONTACTO_CARRERA_COLUMNA + " TEXT, "
            + CORREO_CONTACTO_CARRERA_COLUMNA + " TEXT);";

    public static final String CREAR_TABLA_USUARIO_CARRERA="CREATE TABLE "
            + TABLA_USUARIO_CARRERA + "(" + ID_USUARIO_CARRERA_COLUMNA + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ID_CARRERA_USUARIO_CARRERA_COLUMNA + " INTEGER NOT NULL, "
            + ID_USUARIO_USUARIO_CARRERA_COLUMNA + " INTEGER NOT NULL, "
            + FECHA_REGISTRO_USUARIO_CARRERA_COLUMNA + " DATE,"
            + " FOREIGN KEY( "+ ID_CARRERA_USUARIO_CARRERA_COLUMNA + " ) REFERENCES "
            + TABLA_CARRERA + "( "+ ID_CARRERA_COLUMNA + " ),"
            + " FOREIGN KEY( "+ ID_USUARIO_USUARIO_CARRERA_COLUMNA + " ) REFERENCES "
            + TABLA_USUARIO + "( "+ ID_USUARIO_COLUMNA + " ) );";





    public DataBaseHelper(Context context) {
        super(context, BD_NOMBRE, null, BD_VERSION_ESQUEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREAR_TABLA_USUARIO);
        db.execSQL(CREAR_TABLA_CARRERA);
        db.execSQL(CREAR_TABLA_USUARIO_CARRERA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
