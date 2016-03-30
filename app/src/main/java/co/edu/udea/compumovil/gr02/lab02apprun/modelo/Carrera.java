package co.edu.udea.compumovil.gr02.lab02apprun.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Pc1 on 22/03/2016.
 */
public class Carrera {
    private int id_Carrera;
    private String nombre;
    private String descripcion;
    private int distancia;
    private String lugar;
    private Date fecha;
    private String telefono_Contacto;
    private String correo_Contacto;
    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.ENGLISH);


    public Carrera(){}

    public int getId_Carrera() {
        return id_Carrera;
    }

    public void setId_Carrera(int id_Carrera) {
        this.id_Carrera = id_Carrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        try {
            this.fecha = formatter.parse(fecha);
        } catch (ParseException e) {
            this.fecha=null;
        }
    }

    public String getTelefono_Contacto() {
        return telefono_Contacto;
    }

    public void setTelefono_Contacto(String telefono_Contacto) {
        this.telefono_Contacto = telefono_Contacto;
    }

    public String getCorreo_Contacto() {
        return correo_Contacto;
    }

    public void setCorreo_Contacto(String correo_Contacto) {
        this.correo_Contacto = correo_Contacto;
    }
}
