package co.edu.udea.compumovil.gr02.lab02apprun.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Pc1 on 22/03/2016.
 */
public class Usuario_Carrera {
    private int idUsuarioCarrera;
    private Usuario usuario;
    private Carrera carrera;




    public int getIdUsuarioCarrera() {
        return idUsuarioCarrera;
    }

    public void setIdUsuarioCarrera(int idUsuarioCarrera) {
        this.idUsuarioCarrera = idUsuarioCarrera;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }


}
