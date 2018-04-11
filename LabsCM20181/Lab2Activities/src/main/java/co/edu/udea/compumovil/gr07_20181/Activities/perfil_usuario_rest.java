package co.edu.udea.compumovil.gr07_20181.Activities;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import co.edu.udea.compumovil.gr07_20181.lab1.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class perfil_usuario_rest extends Fragment {


    public perfil_usuario_rest() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_perfil_usuario_rest, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v= getView();
        TextView texnombreusu= (TextView) v.findViewById(R.id.per_nom_usu);
        TextView texcorreousu= (TextView) v.findViewById(R.id.per_correo_usu);
        SharedPreferences preferencias= getActivity().getSharedPreferences("Mis preferencias", Context.MODE_PRIVATE);
        ImageView imagen_usu= (ImageView) v.findViewById(R.id.imageview_perfil_usu);
        imagen_usu.setImageURI(Uri.parse(preferencias.getString("dir_imagen","sin imagen")));
        texnombreusu.setText(preferencias.getString("nombre","sin nombre"));
        texcorreousu.setText(preferencias.getString("correo","sin correo"));


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.findItem(R.id.buscar_precio).setVisible(false);
        menu.findItem(R.id.buscar_nombre).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }


}
