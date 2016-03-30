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
        values.put(DataBaseHelper.ID_USUARIO_USUARIO_CARRERA_COLUMNA,id_Usuario);

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
        String consulta = "SELECT c." + DataBaseHelper.ID_CARRERA_COLUMNA + ", c."
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
                + usuario_Id + " AND uc." + DataBaseHelper.ID_CARRERA_USUARIO_CARRERA_COLUMNA
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



    /*

    public ArrayList<Usuario_Carrera> getCarrerasDeUnUsuario(int id_Usuario){
        ArrayList<Usuario_Carrera> carreras= new ArrayList<Usuario_Carrera>();
        String usuario_Id=Integer.toString(id_Usuario);
        String consulta="SELECT c." + DataBaseHelper.ID_CARRERA_COLUMNA + ", c."
                + DataBaseHelper.NOMBRE_CARRERA_COLUMNA + ", c."
                + DataBaseHelper.DESCRIPCION_CARRERA_COLUMNA + ", c."
                + DataBaseHelper.DISTANCIA_CARRERA_COLUMNA + ", c."
                + DataBaseHelper.LUGAR_CARRERA_COLUMNA + ", c."
                + DataBaseHelper.FECHA_CARRERA_COLUMNA + ", c."
                + DataBaseHelper.TELEFONO_CONTACTO_CARRERA_COLUMNA + ", c."
                + DataBaseHelper.CORREO_CONTACTO_CARRERA_COLUMNA + ", uc."
                + DataBaseHelper.FECHA_REGISTRO_USUARIO_CARRERA_COLUMNA + ", u."
                + DataBaseHelper.ID_USUARIO_COLUMNA + ", u."
                + DataBaseHelper.NOMBRE_CARRERA_COLUMNA + ", u."
                + DataBaseHelper.APELLIDO_USUARIO_COLUMNA + ", u."
                + DataBaseHelper.ALIAS_USUARIO_COLUMNA + ", u."
                + DataBaseHelper.PASSWORD_USUARIO_COLUMNA + ", u."
                + DataBaseHelper.CORREO_USUARIO_COLUMNA + " FROM "
                + DataBaseHelper.TABLA_USUARIO + " u, "
                + DataBaseHelper.TABLA_CARRERA + " c, "
                + DataBaseHelper.TABLA_USUARIO_CARRERA + " uc WHERE uc."
                + DataBaseHelper.ID_USUARIO_USUARIO_CARRERA_COLUMNA + " = "
                + usuario_Id + " AND uc." + DataBaseHelper.ID_CARRERA_USUARIO_CARRERA_COLUMNA
                + " = c." + DataBaseHelper.ID_CARRERA_COLUMNA + ";";

        Log.d("consulta: ", consulta);
        Cursor cursor = getDb().rawQuery(consulta, null);
        while (cursor.moveToNext()) {
            int id_Carrera= cursor.getInt(0);
            String nombre_Carrera= cursor.getString(1);
            String descripcion_Carrera= cursor.getString(2);
            int distancia_Carrera= cursor.getInt(3);
            String lugar_Carrera= cursor.getString(4);
            Date fecha_Carrera;
            try {
                fecha_Carrera=formatter.parse(cursor.getString(5));
            } catch (ParseException e) {
                fecha_Carrera=null;
            }
            String telefono_Carrera= cursor.getString(6);
            String correo_Carrera= cursor.getString(7);
            Carrera carrera= new Carrera(id_Carrera,nombre_Carrera,descripcion_Carrera,distancia_Carrera,
                    fecha_Carrera,lugar_Carrera,telefono_Carrera,correo_Carrera);
            Date fecha_Registro;
            try {
                fecha_Registro=formatter.parse(cursor.getString(8));
            } catch (ParseException e) {
                fecha_Registro=null;
            }
            int id_Us=cursor.getInt(9);
            String nombre_Usuario=cursor.getString(10);
            String apellido_Usuario=cursor.getString(11);
            String alias_Usuario=cursor.getString(12);
            String password_Usuario=cursor.getString(13);
            String correo_Usuario=cursor.getString(14);

            Usuario usuario=new Usuario(id_Us,apellido_Usuario,alias_Usuario,correo_Usuario,
                    password_Usuario, nombre_Usuario);

            Usuario_Carrera usuario_carrera=new Usuario_Carrera(usuario,carrera,fecha_Registro);

            carreras.add(usuario_carrera);
        }
        return carreras;


    }

    */

}
