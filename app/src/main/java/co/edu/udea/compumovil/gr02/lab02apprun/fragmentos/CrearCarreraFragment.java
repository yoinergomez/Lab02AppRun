package co.edu.udea.compumovil.gr02.lab02apprun.fragmentos;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.udea.compumovil.gr02.lab02apprun.R;
import co.edu.udea.compumovil.gr02.lab02apprun.actividad.LoginActivity;
import co.edu.udea.compumovil.gr02.lab02apprun.dao.CarreraDAO;
import co.edu.udea.compumovil.gr02.lab02apprun.dao.UsuarioDAO;
import co.edu.udea.compumovil.gr02.lab02apprun.dao.Usuario_CarreraDAO;
import co.edu.udea.compumovil.gr02.lab02apprun.modelo.Carrera;
import co.edu.udea.compumovil.gr02.lab02apprun.modelo.Usuario;
import co.edu.udea.compumovil.gr02.lab02apprun.modelo.Usuario_Carrera;


/**
 * A simple {@link Fragment} subclass.
 */
public class CrearCarreraFragment extends Fragment implements View.OnClickListener {

    private Button btnCrearcarrera;
    private CarreraDAO carreraDAO;
    private Carrera carrera;
    private Usuario usuario;
    private UsuarioDAO usuarioDAO;
    private Usuario_CarreraDAO usuario_carreraDAO;
    private Usuario_Carrera usuario_carrera;
    private int contadorCarrera;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_crear_carrera, container, false);
        carreraDAO=new CarreraDAO(this.getContext());
        usuarioDAO=new UsuarioDAO(this.getContext());
        usuario_carreraDAO=new Usuario_CarreraDAO(this.getContext());
        usuario_carrera=new Usuario_Carrera();
        carrera=new Carrera();


        contadorCarrera=carreraDAO.getIdUltimoRegistro()+1;
        btnCrearcarrera = (Button) view.findViewById(R.id.button_crearCarrera);
        btnCrearcarrera.setOnClickListener(this);

        //Obtener usuario de la sesion
        SharedPreferences sharedpreferences;
        sharedpreferences = this.getActivity().getSharedPreferences(LoginActivity.PREFERENCIA, Context.MODE_PRIVATE);

        int id_usuario = 0;
        String correo = sharedpreferences.getString("correo", null);
        String contrasena = "";
        String nombre = "";
        String apellido = "";
        byte[] imagen = null;
        System.out.println("@@@"+correo);

        Cursor c=usuarioDAO.getTodasLosUsuarios();
        Log.d("@@@", "Registros de la tabla Usuario");
        while(c.moveToNext()){
            Log.d("@@@",c.getInt(0)+" "+c.getString(1)+ " "+c.getString(2));
        }


        Cursor cursor = usuarioDAO.getUsuario(correo);
        if(cursor.moveToFirst()) {

            id_usuario = cursor.getInt(0);
            correo = cursor.getString(1);
            contrasena = cursor.getString(2);
            nombre = cursor.getString(3);
            apellido = cursor.getString(4);
            imagen = cursor.getBlob(5);

        }
        usuario = new Usuario();
        usuario.setIdUsuario(id_usuario);
        usuario.setPassword(contrasena);
        usuario.setCorreo(correo);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setImagen(imagen);

        return view;
    }

    public void crearCarrera() {

    }


    @Override
    public void onClick(View view) {
        View viewFragment=this.getView();
        EditText e1= (EditText)viewFragment.findViewById(R.id.edTxt_nombre);
        String nombre=e1.getText().toString();
        carrera.setNombre(nombre);
        e1=(EditText)viewFragment.findViewById(R.id.edTxt_descripcion);
        String descripcion=e1.getText().toString();
        carrera.setDescripcion(descripcion);
        e1=(EditText)viewFragment.findViewById(R.id.edTxt_distancia);
        String distanciaStr=e1.getText().toString();
        int distancia=Integer.parseInt(distanciaStr);
        carrera.setDistancia(distancia);
        e1=(EditText)viewFragment.findViewById(R.id.edTxt_lugar);
        String lugar=e1.getText().toString();
        carrera.setLugar(lugar);
        DatePicker datePicker=(DatePicker)viewFragment.findViewById(R.id.datePicker);
        int dia=datePicker.getDayOfMonth();
        int mes=datePicker.getDayOfMonth();
        int year=datePicker.getYear();
        String fecha=year + "-" + mes+ "-" + dia;
        carrera.setFecha(fecha);
        e1=(EditText)viewFragment.findViewById(R.id.edTxt_correoContacto);
        String correoContacto=e1.getText().toString();
        carrera.setCorreo_Contacto(correoContacto);
        e1=(EditText)viewFragment.findViewById(R.id.edTxt_telefonoContacto);
        String telefonoContacto=e1.getText().toString();
        carrera.setTelefono_Contacto(telefonoContacto);
        carrera.setId_Carrera(contadorCarrera);
        contadorCarrera=contadorCarrera+1;
        carreraDAO.guardarCarrera(carrera);

        usuario_carrera.setUsuario(usuario);
        usuario_carrera.setCarrera(carrera);
        usuario_carreraDAO.guardarUsuario_Carrera(usuario_carrera);
        Cursor c=usuario_carreraDAO.getTodasCarrerasCreadas();


        Log.d("@@@", "Registros de la tabla Usuario_carrera del usuario 2");
        while(c.moveToNext()){
            Log.d("@@@",c.getInt(0)+" "+c.getString(1)+ " "+c.getString(2));
        }
        /*
        c=usuario_carreraDAO.getTodasLosUsuarios_Carreras();
        Log.d("@@@", "Registros de la tabla Usuario_carrera");
        while(c.moveToNext()){
            Log.d("@@@",c.getInt(0)+" "+c.getInt(1)+ " "+c.getInt(2));
        }

        c=usuarioDAO.getTodasLosUsuarios();
        Log.d("@@@", "Registros de la tabla Usuario");
        while(c.moveToNext()){
            Log.d("@@@",c.getInt(0)+" "+c.getString(1)+ " "+c.getString(2));
        }

        c=carreraDAO.getTodasLasCarreras();
        Log.d("@@@","Registros de la tabla Carrera");
        while(c.moveToNext()){
            Log.d("@@@",c.getInt(0)+" "+c.getString(1)+ " "+c.getString(2)+ " "+ c.getString(3));
        }*/





        Toast.makeText(this.getActivity(),
                nombre, Toast.LENGTH_LONG).show();

    }
}