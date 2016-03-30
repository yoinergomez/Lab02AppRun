package co.edu.udea.compumovil.gr02.lab02apprun.actividad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import co.edu.udea.compumovil.gr02.lab02apprun.R;
import co.edu.udea.compumovil.gr02.lab02apprun.dao.UsuarioDAO;
import co.edu.udea.compumovil.gr02.lab02apprun.fragmentos.AcercaFragment;
import co.edu.udea.compumovil.gr02.lab02apprun.fragmentos.CrearCarreraFragment;
import co.edu.udea.compumovil.gr02.lab02apprun.fragmentos.ListaCarrerasFragment;
import co.edu.udea.compumovil.gr02.lab02apprun.fragmentos.PerfilFragment;


public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment crearCarreraFragment;
    Fragment listaCarreraFragment;
    Fragment perfilFragment;
    Fragment acercaFragment;
    private UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                crearCarreraFragment=new CrearCarreraFragment();
                fragmentTransaction.replace(R.id.contenedor_fragmentos,crearCarreraFragment);
                fragmentTransaction.commit();
            }
        });

        findViewById(R.id.textViewCorreo);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        //Obtener usuario de la sesion
        SharedPreferences sharedpreferences;
        sharedpreferences = this.getSharedPreferences(LoginActivity.PREFERENCIA, Context.MODE_PRIVATE);

        String correo = sharedpreferences.getString("correo", null);
        String nombre = "";

        System.out.println("@@@"+correo);
        usuarioDAO=new UsuarioDAO(this);
        Cursor cursor = usuarioDAO.getUsuario(correo);
        if(cursor.moveToFirst()) {
            correo = cursor.getString(1);
            nombre = cursor.getString(3);
        }
        TextView textView = (TextView) findViewById(R.id.textViewUsuario);
        textView.setText(nombre);
        textView = (TextView) findViewById(R.id.textViewCorreo);
        textView.setText(correo);

        return true;
    }

    //@@@ TODO ESTE METODO
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cerrar_sesion) {
            //Limpiando las preferencias compartidas
            SharedPreferences sharedpreferences;
            sharedpreferences = getSharedPreferences(LoginActivity.PREFERENCIA, Context.MODE_PRIVATE);
            sharedpreferences.edit().clear().apply();

            //Iniciando la actividad login
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            this.finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        if (id == R.id.nav_carreras) {
            listaCarreraFragment=new ListaCarrerasFragment();
            fragmentTransaction.replace(R.id.contenedor_fragmentos,listaCarreraFragment);
        } else if (id == R.id.nav_perfil) {
            perfilFragment = new PerfilFragment();
            fragmentTransaction.replace(R.id.contenedor_fragmentos,perfilFragment);
        } else if (id == R.id.nav_acerca) {
            acercaFragment = new AcercaFragment();
            fragmentTransaction.replace(R.id.contenedor_fragmentos,acercaFragment);
        }

        fragmentTransaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
