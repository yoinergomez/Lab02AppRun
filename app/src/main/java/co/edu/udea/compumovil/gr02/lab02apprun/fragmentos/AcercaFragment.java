package co.edu.udea.compumovil.gr02.lab02apprun.fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.edu.udea.compumovil.gr02.lab02apprun.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AcercaFragment extends Fragment {


    public AcercaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_acerca, container, false);
    }

}
