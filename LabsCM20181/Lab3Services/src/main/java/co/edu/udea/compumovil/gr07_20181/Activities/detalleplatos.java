package co.edu.udea.compumovil.gr07_20181.Activities;



import android.graphics.Bitmap;
import android.os.Bundle;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import co.edu.udea.compumovil.gr07_20181.lab1.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class detalleplatos extends Fragment {


    public detalleplatos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalleplatos, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageView p_foto= (ImageView) getActivity().findViewById(R.id.det_platos_foto);
        TextView p_nombre= (TextView) getActivity().findViewById(R.id.det_platos_nombre);
        TextView p_hora= (TextView) getActivity().findViewById(R.id.det_platos_horario);
        TextView p_tipo= (TextView) getActivity().findViewById(R.id.det_platos_tipo);
        TextView p_tiempre= (TextView) getActivity().findViewById(R.id.det_platos_tiempre);
        TextView p_pre= (TextView) getActivity().findViewById(R.id.det_platos_precio);
        TextView p_ingredi= (TextView) getActivity().findViewById(R.id.det_platos_ingredientes);

        Bitmap foto= getArguments().getParcelable("p_dir_foto");
        if (foto!=null) {

            p_foto.setImageBitmap(foto);
        }
        p_nombre.setText(getArguments().getString("p_nombre"));
        p_hora.setText(getArguments().getString("p_hora"));
        p_tipo.setText(getArguments().getString("p_tipo"));
        p_tiempre.setText(getArguments().getString("p_tiempre"));
        p_pre.setText(getArguments().getString("p_precio"));
        p_ingredi.setText( getArguments().getString("p_ingredientes"));

    }
}
