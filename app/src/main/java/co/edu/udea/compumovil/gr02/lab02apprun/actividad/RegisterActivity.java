package co.edu.udea.compumovil.gr02.lab02apprun.actividad;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import co.edu.udea.compumovil.gr02.lab02apprun.R;
import co.edu.udea.compumovil.gr02.lab02apprun.dao.UsuarioDAO;
import co.edu.udea.compumovil.gr02.lab02apprun.modelo.Usuario;

/**
 * Created by esteban on 30/03/16.
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText nombreView;
    private EditText apellidoView;
    private EditText correoView;
    private EditText contraseñaView;
    private EditText contraseñaRepitaView;
    private UsuarioDAO manager;
    private Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Obtengo componentes visuales
        nombreView = (EditText) findViewById(R.id.nombre);
        apellidoView= (EditText) findViewById(R.id.apellido);
        correoView= (EditText) findViewById(R.id.correo);
        contraseñaView = (EditText) findViewById(R.id.contrasena);
        contraseñaRepitaView = (EditText) findViewById(R.id.repita_contrasena);

        //Inicio objetos DAO
        manager = new UsuarioDAO(this);
        UsuarioDAO usuarioDAO = new UsuarioDAO(this);

        Button registerButton = (Button) findViewById(R.id.registrar_button_registrar);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //validarRegistro();
                if(validarRegistro()){
                    Snackbar.make(view, "Usuario registrado exitosamente!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            cancelarRegistro();
                        }
                    }, 1500);
                }
            }
        });

        Button cancelarButton = (Button) findViewById(R.id.cancelar_button_registrar);
        cancelarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelarRegistro();
            }
        });

    }

    private boolean validarRegistro() {

        // Reset errors.
        nombreView.setError(null);
        apellidoView.setError(null);
        correoView.setError(null);
        contraseñaView.setError(null);
        contraseñaRepitaView.setError(null);

        // Store values at the time of the activity_login attempt.
        String nombre = nombreView.getText().toString();
        String apellido = apellidoView.getText().toString();
        String correo = correoView.getText().toString();
        String contrasena = contraseñaView.getText().toString();
        String contrasenaRepita = contraseñaRepitaView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check valid password
        if (TextUtils.isEmpty(contrasena) || !esContrasenaValida(contrasena)) {
            contraseñaView.setError(getString(R.string.error_contrasena_invalida));
            focusView = contraseñaView;
            cancel = true;
        }else if (TextUtils.isEmpty(contrasenaRepita) || !esContrasenaValida(contrasenaRepita)) {
            contraseñaRepitaView.setError(getString(R.string.error_contrasena_invalida));
            focusView = contraseñaRepitaView;
            cancel = true;
        }else if (!contrasena.equals(contrasenaRepita)){
            contraseñaRepitaView.setError(getString(R.string.error_contrasena_no_iguales));
            focusView = contraseñaRepitaView;
            cancel = true;
        }


        // Check email address.
        if (TextUtils.isEmpty(correo)) {
            correoView.setError(getString(R.string.error_campo_obligatorio));
            focusView = correoView;
            cancel = true;
        } else if (!esCorreoValido(correo)) {
            correoView.setError(getString(R.string.error_correo_invalido));
            focusView = correoView;
            cancel = true;
        }

        // Check valid lastname
        if (TextUtils.isEmpty(apellido)) {
            apellidoView.setError(getString(R.string.error_campo_obligatorio));
            focusView = apellidoView;
            cancel = true;
        }


        // Check valid name
        if (TextUtils.isEmpty(nombre)) {
            nombreView.setError(getString(R.string.error_campo_obligatorio));
            focusView = nombreView;
            cancel = true;
        }



        if (!cancel) {


            cursor = manager.getUsuario(correo);

            //Nos aseguramos de que NO exista un usuario con igual correo
            if (!cursor.moveToFirst()) {

                Usuario usuario = new Usuario();
                usuario.setNombre(nombre);
                usuario.setApellido(apellido);
                usuario.setCorreo(correo);
                usuario.setPassword(contrasena);
                usuario.setPassword(contrasenaRepita);

                UsuarioDAO usuarioDAO = new UsuarioDAO(this);
                usuarioDAO.guardarUsuario(usuario);

                return true;

            } else {
                correoView.setError(getString(R.string.error_correo_incorrecto));
                focusView = correoView;
                focusView.requestFocus();
            }

        } else{
            focusView.requestFocus();
        }
        return false;
    }

    private void cancelarRegistro(){
        this.finish();
    }

    private boolean esCorreoValido(String email) {
        return email.contains("@");
    }

    private boolean esContrasenaValida(String password) {
        return password.length() > 4;
    }
}
