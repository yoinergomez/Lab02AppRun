package co.edu.udea.compumovil.gr02.lab02apprun.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import co.edu.udea.compumovil.gr02.lab02apprun.modelo.Usuario;

/**
 * Created by Pc1 on 22/03/2016.
 */
public class UsuarioDAO extends BDAppRunDAO {

    public UsuarioDAO(Context context) {
        super(context);
    }

    public long guardarUsuario(Usuario usuario){
        Log.d("@@", DataBaseHelper.CREAR_TABLA_USUARIO);
        ContentValues values=new ContentValues();
        values.put(DataBaseHelper.NOMBRE_USUARIO_COLUMNA,usuario.getNombre());
        values.put(DataBaseHelper.APELLIDO_USUARIO_COLUMNA,usuario.getApellido());
        values.put(DataBaseHelper.PASSWORD_USUARIO_COLUMNA,usuario.getPassword());
        values.put(DataBaseHelper.CORREO_USUARIO_COLUMNA, usuario.getCorreo());

        return getDb().insert(DataBaseHelper.TABLA_USUARIO,null,values);


    }

    //@@@ TODO EL METODO
    public Cursor getUsuario(String correo){

        String[] columnas = new String[]{
                DataBaseHelper.ID_USUARIO_COLUMNA,
                DataBaseHelper.CORREO_USUARIO_COLUMNA,
                DataBaseHelper.PASSWORD_USUARIO_COLUMNA
        };

        return getDb().query(DataBaseHelper.TABLA_USUARIO,columnas,
                DataBaseHelper.CORREO_USUARIO_COLUMNA + "=?",
                new String[]{correo},null,null,null);

    }

    public int eliminarUsuario(Usuario usuario){
        String idUsuario= Integer.toString(usuario.getIdUsuario());
        return getDb().delete(DataBaseHelper.TABLA_USUARIO, DataBaseHelper.ID_USUARIO_COLUMNA + "=?",
                new String[]{idUsuario});
    }

    public Cursor getTodasLosUsuarios() {
        String[] columnas = new String[]{DataBaseHelper.ID_USUARIO_COLUMNA,DataBaseHelper.CORREO_USUARIO_COLUMNA,
                DataBaseHelper.PASSWORD_USUARIO_COLUMNA};
        return getDb().query(DataBaseHelper.TABLA_USUARIO, columnas, null, null, null, null, null);

    }
}
