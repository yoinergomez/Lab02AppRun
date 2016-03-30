package co.edu.udea.compumovil.gr02.lab02apprun.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Locale;
import co.edu.udea.compumovil.gr02.lab02apprun.modelo.Usuario_Carrera;

/**
 * Created by Pc1 on 22/03/2016.
 */
public class Usuario_CarreraDAO extends BDAppRunDAO {

    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.ENGLISH);

    public Usuario_CarreraDAO(Context context) {
        super(context);
    }

    public long guardarUsuario_Carrera(Usuario_Carrera usuario_carrera){
        ContentValues values=new ContentValues();
        int id_Usuario=usuario_carrera.getUsuario().getIdUsuario();
        int id_Carrera=usuario_carrera.getCarrera().getId_Carrera();
        values.put(DataBaseHelper.ID_CARRERA_USUARIO_CARRERA_COLUMNA,id_Carrera);
        values.put(DataBaseHelper.ID_USUARIO_USUARIO_CARRERA_COLUMNA, id_Usuario);

        return getDb().insert(DataBaseHelper.TABLA_USUARIO_CARRERA,null,values);

    }


    public int eliminarUsuario_Carrera(Usuario_Carrera usuario_carrera){
        String id_Usuario= Integer.toString(usuario_carrera.getUsuario().getIdUsuario());
        String id_Carrera= Integer.toString(usuario_carrera.getCarrera().getId_Carrera());

        return getDb().delete(DataBaseHelper.TABLA_USUARIO_CARRERA, DataBaseHelper.ID_USUARIO_USUARIO_CARRERA_COLUMNA + "=?",
                new String[]{id_Usuario});

    }

    public Cursor getCarrerasDeUnUsuario(int id_Usuario) {

        String usuario_Id = Integer.toString(id_Usuario);
        String consulta = "SELECT DISTINCT c." + DataBaseHelper.ID_CARRERA_COLUMNA + ", c."
                + DataBaseHelper.NOMBRE_CARRERA_COLUMNA + ", c."
                + DataBaseHelper.DESCRIPCION_CARRERA_COLUMNA + ", c."
                + DataBaseHelper.DISTANCIA_CARRERA_COLUMNA + ", c."
                + DataBaseHelper.LUGAR_CARRERA_COLUMNA + ", c."
                + DataBaseHelper.FECHA_CARRERA_COLUMNA + ", c."
                + DataBaseHelper.TELEFONO_CONTACTO_CARRERA_COLUMNA + ", c."
                + DataBaseHelper.CORREO_CONTACTO_CARRERA_COLUMNA  + " FROM "
                + DataBaseHelper.TABLA_USUARIO + " u, "
                + DataBaseHelper.TABLA_CARRERA + " c, "
                + DataBaseHelper.TABLA_USUARIO_CARRERA + " uc WHERE uc."
                + DataBaseHelper.ID_USUARIO_USUARIO_CARRERA_COLUMNA + " = "
                + id_Usuario + " AND uc." + DataBaseHelper.ID_CARRERA_USUARIO_CARRERA_COLUMNA
                + " = c." + DataBaseHelper.ID_CARRERA_COLUMNA + ";";

        Log.d("consulta: ", consulta);
        Cursor cursor = getDb().rawQuery(consulta, null);
        return cursor;
    }

    public Cursor getTodasCarrerasCreadas(){
        String consulta = "SELECT DISTINCT c." + DataBaseHelper.ID_CARRERA_COLUMNA + ", c."
                + DataBaseHelper.NOMBRE_CARRERA_COLUMNA + ", c."
                + DataBaseHelper.DESCRIPCION_CARRERA_COLUMNA + ", c."
                + DataBaseHelper.DISTANCIA_CARRERA_COLUMNA + ", c."
                + DataBaseHelper.LUGAR_CARRERA_COLUMNA + ", c."
                + DataBaseHelper.FECHA_CARRERA_COLUMNA + ", c."
                + DataBaseHelper.TELEFONO_CONTACTO_CARRERA_COLUMNA + ", c."
                + DataBaseHelper.CORREO_CONTACTO_CARRERA_COLUMNA  + " FROM "
                + DataBaseHelper.TABLA_USUARIO + " u, "
                + DataBaseHelper.TABLA_CARRERA + " c, "
                + DataBaseHelper.TABLA_USUARIO_CARRERA + " uc WHERE uc."
                + DataBaseHelper.ID_CARRERA_USUARIO_CARRERA_COLUMNA
                + " = c." + DataBaseHelper.ID_CARRERA_COLUMNA + ";";
        Log.d("consulta: ", consulta);
        Cursor cursor = getDb().rawQuery(consulta, null);
        return cursor;
    }

    public Cursor getTodasLosUsuarios_Carreras() {
        String[] columnas = new String[]{DataBaseHelper.ID_USUARIO_CARRERA_COLUMNA,
                DataBaseHelper.ID_USUARIO_USUARIO_CARRERA_COLUMNA ,
                DataBaseHelper.ID_CARRERA_USUARIO_CARRERA_COLUMNA};
        return getDb().query(DataBaseHelper.TABLA_USUARIO_CARRERA, columnas, null, null, null, null, null);

    }


}
