package co.edu.udea.compumovil.gr02.lab02apprun.modelo;

/**
 * Created by Pc1 on 22/03/2016.
 */
public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String password;
    private String correo;


    public Usuario(){

    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int id) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
