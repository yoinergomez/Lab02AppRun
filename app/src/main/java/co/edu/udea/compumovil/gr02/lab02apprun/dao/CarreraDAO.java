package co.edu.udea.compumovil.gr02.lab02apprun.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.text.SimpleDateFormat;
import java.util.Locale;
import co.edu.udea.compumovil.gr02.lab02apprun.modelo.Carrera;

/**
 * Created by Pc1 on 22/03/2016.
 */
public class CarreraDAO extends BDAppRunDAO{

    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.ENGLISH);

    public CarreraDAO(Context context) {
        super(context);
    }

    public long guardarCarrera(Carrera carrera){
        ContentValues values=new ContentValues();
        values.put(DataBaseHelper.NOMBRE_CARRERA_COLUMNA,carrera.getNombre());
        values.put(DataBaseHelper.DESCRIPCION_CARRERA_COLUMNA,carrera.getDescripcion());
        values.put(DataBaseHelper.DISTANCIA_CARRERA_COLUMNA,carrera.getDistancia());
        values.put(DataBaseHelper.LUGAR_CARRERA_COLUMNA, carrera.getLugar());
        values.put(DataBaseHelper.FECHA_CARRERA_COLUMNA,formatter.format(carrera.getFecha()));
        values.put(DataBaseHelper.TELEFONO_CONTACTO_CARRERA_COLUMNA,carrera.getTelefono_Contacto());
        values.put(DataBaseHelper.CORREO_CONTACTO_CARRERA_COLUMNA, carrera.getCorreo_Contacto());

        return getDb().insert(DataBaseHelper.TABLA_CARRERA,null,values);

    }


    public int eliminarCarrera(Carrera carrera){
        String nomCarrera=carrera.getNombre();
        return getDb().delete(DataBaseHelper.TABLA_CARRERA, DataBaseHelper.NOMBRE_CARRERA_COLUMNA + "=?",
                new String[]{nomCarrera});

    }

    public Cursor getTodasLasCarreras() {
        String[] columnas = new String[]{DataBaseHelper.ID_CARRERA_COLUMNA,DataBaseHelper.NOMBRE_CARRERA_COLUMNA ,
                DataBaseHelper.LUGAR_CARRERA_COLUMNA,DataBaseHelper.DESCRIPCION_CARRERA_COLUMNA };
        return getDb().query(DataBaseHelper.TABLA_CARRERA, columnas, null, null, null, null, null);

    }

    public int getIdUltimoRegistro(){
        Cursor c=getTodasLasCarreras();
        if(c.moveToLast()){
            return c.getInt(0);
        }else{
            return 0;
        }
    }
}
