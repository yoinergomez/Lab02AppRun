package co.edu.udea.compumovil.gr02.lab02apprun.actividad;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import co.edu.udea.compumovil.gr02.lab02apprun.R;


/**
 * Login através de correo y contraseña
 */
public class LoginActivity extends AppCompatActivity {

    public static final String PREFERENCIA = "preferencia";
    public SharedPreferences sharedpreferences;
    private EditText contraseñaView;
    private AutoCompleteTextView correoView;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Obtengo preferencia compartida para almacenar la sesion
        sharedpreferences = getSharedPreferences(PREFERENCIA, Context.MODE_PRIVATE);

        //Obtengo componentes visuales
        correoView = (AutoCompleteTextView) findViewById(R.id.email);
        contraseñaView = (EditText) findViewById(R.id.password);
        contraseñaView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    validarLogin();
                    return true;
                }
                return false;
            }
        });


        //Verificar si existe una sesion previa
        if(existeSesion()){
            iniciarNavigationDrawer();
        }

        Button mEmailSignInButton = (Button) findViewById(R.id.ingresar_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                validarLogin();
            }
        });

    }

    private void guardarSesion(String correo, String contrasena){
        editor = sharedpreferences.edit();
        editor.putString("correo", correo);
        editor.putString("contrasena", contrasena);
        if(editor.commit()){
            Log.d("@@@", "Guardo sesion");
        }
    }

    private void validarLogin() {

        // Reset errors.
        contraseñaView.setError(null);

        // Store values at the time of the activity_login attempt.
        String email = correoView.getText().toString();
        String password = contraseñaView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check valid password
        if (TextUtils.isEmpty(password) && !esContrasenaValida(password)) {
            contraseñaView.setError(getString(R.string.error_contrasena_invalida));
            focusView = contraseñaView;
            cancel = true;
        }

        // Check email address.
        if (TextUtils.isEmpty(email)) {
            correoView.setError(getString(R.string.error_campo_obligatorio));
            focusView = correoView;
            cancel = true;
        } else if (!esCorreoValido(email)) {
            correoView.setError(getString(R.string.error_correo_invalido));
            focusView = correoView;
            cancel = true;
        }

        if (!cancel) {
            guardarSesion(email,password);
            iniciarNavigationDrawer();
        } else{
            focusView.requestFocus();
        }

    }

    public boolean existeSesion(){
        String correo = sharedpreferences.getString("correo", null);
        String contrasena = sharedpreferences.getString("contrasena", null);
        return !(correo==null && contrasena==null);
    }

    public void iniciarNavigationDrawer(){
        Intent intent = new Intent(this, NavigationDrawerActivity.class);
        startActivity(intent);
        this.finish();
    }

    private boolean esCorreoValido(String email) {
        return email.contains("@");
    }

    private boolean esContrasenaValida(String password) {
        return password.length() > 4;
    }

}

