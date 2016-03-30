package co.edu.udea.compumovil.gr02.lab02apprun.fragmentos;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import co.edu.udea.compumovil.gr02.lab02apprun.R;
import co.edu.udea.compumovil.gr02.lab02apprun.dao.DataBaseHelper;
import co.edu.udea.compumovil.gr02.lab02apprun.dao.Usuario_CarreraDAO;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListaCarrerasFragment extends Fragment {

    private Usuario_CarreraDAO usuario_carreraDAO;
    private SimpleCursorAdapter adapter;
    private Cursor cursor;
    private ListView listView;
    public ListaCarrerasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_lista_carreras, container, false);
        listView=(ListView)view.findViewById(R.id.listView);
        usuario_carreraDAO=new Usuario_CarreraDAO(this.getContext());
        cursor=usuario_carreraDAO.getTodasCarrerasCreadas();
        String[] from=new String[]{DataBaseHelper.NOMBRE_CARRERA_COLUMNA, DataBaseHelper.DESCRIPCION_CARRERA_COLUMNA,
        DataBaseHelper.LUGAR_CARRERA_COLUMNA, DataBaseHelper.FECHA_CARRERA_COLUMNA};
        int[] to=new int[]{R.id.txtView_nombre, R.id.txtView_descripcion, R.id.txtView_lugar, R.id.txtView_fecha};
        adapter=new SimpleCursorAdapter(this.getContext(), R.layout.cuatro_lineas_item,cursor,from,to,0);
        listView.setAdapter(adapter);



        return view;
    }

}
