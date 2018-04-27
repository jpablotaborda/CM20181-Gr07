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
public class detallebebidas extends Fragment {


    public detallebebidas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detallebebidas, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageView b_foto= (ImageView) getActivity().findViewById(R.id.det_bebidas_foto);
        TextView b_nombre= (TextView) getActivity().findViewById(R.id.det_bebidas_nombre);
        TextView b_pre= (TextView) getActivity().findViewById(R.id.det_bebidas_precio);
        TextView b_ingredi= (TextView) getActivity().findViewById(R.id.det_bebidas_ingredientes);

        Bitmap foto= getArguments().getParcelable("b_dir_foto");
        if (foto!=null) {

            b_foto.setImageBitmap(foto);
        }
        b_nombre.setText(getArguments().getString("b_nombre"));
        b_pre.setText(getArguments().getString("b_precio"));
        b_ingredi.setText( getArguments().getString("b_ingredientes"));
    }
}
