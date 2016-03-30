package co.edu.udea.compumovil.gr02.lab02apprun.fragmentos;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.edu.udea.compumovil.gr02.lab02apprun.R;
import co.edu.udea.compumovil.gr02.lab02apprun.actividad.LoginActivity;
import co.edu.udea.compumovil.gr02.lab02apprun.dao.UsuarioDAO;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    private TextView txtV_nombre;
    private TextView txtV_apellido;
    private TextView txtV_correo;

    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        //Obtener usuario de la sesion
        SharedPreferences sharedpreferences;
        sharedpreferences = this.getActivity().getSharedPreferences(LoginActivity.PREFERENCIA, Context.MODE_PRIVATE);
        String correo = sharedpreferences.getString("correo", null);
        String nombre = "";
        String apellido = "";
        byte[] imagen = null;

        UsuarioDAO usuarioDAO = new UsuarioDAO(this.getContext());;
        Cursor cursor = usuarioDAO.getUsuario(correo);
        if(cursor.moveToFirst()) {
            correo = cursor.getString(1);
            nombre = cursor.getString(3);
            apellido = cursor.getString(4);
            imagen = cursor.getBlob(5);
        }

        txtV_nombre = (TextView) view.findViewById(R.id.txtV_nombre);
        txtV_apellido = (TextView) view.findViewById(R.id.txtV_apellido);
        txtV_correo = (TextView) view.findViewById(R.id.txtV_correo);

        txtV_nombre.setText(nombre);
        txtV_apellido.setText(apellido);
        txtV_correo.setText(correo);

        return view;
    }

}
