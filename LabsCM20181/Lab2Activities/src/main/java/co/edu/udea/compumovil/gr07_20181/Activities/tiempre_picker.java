package co.edu.udea.compumovil.gr07_20181.Activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import co.edu.udea.compumovil.gr07_20181.lab1.R;

/**
 * Created by Juan Pablo on 27/02/2018.
 */

public class tiempre_picker extends DialogFragment implements View.OnClickListener{

    private View vista;
    private TextView text_tiempre;
    public static int tiempre=-1;
    public tiempre_picker(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        vista= inflater.inflate(R.layout.activity_tiempre_layout,null);
        this.getDialog().setTitle("Tiempo de preparación");
        this.setCancelable(false);

        Button bot_seleccionar= (Button) vista.findViewById(R.id.bot_tiempre_picker);
        ImageView flecha_arriba= (ImageView) vista.findViewById(R.id.flecha_arriba);
        ImageView flecha_abajo= (ImageView) vista.findViewById(R.id.flecha_abajo);
        text_tiempre= vista.findViewById(R.id.text_tiempre);
        text_tiempre.setText("5");
        bot_seleccionar.setOnClickListener(this);
        flecha_abajo.setOnClickListener(this);
        flecha_arriba.setOnClickListener(this);

        return vista ;

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.bot_tiempre_picker:
                tiempre=Integer.parseInt(text_tiempre.getText().toString());
                this.dismiss();
                break;
            case R.id.flecha_arriba:
                int tp= Integer.parseInt(text_tiempre.getText().toString())+1;

                text_tiempre.setText(Integer.toString(tp));
                break;
            case R.id.flecha_abajo:
                int tp2= Integer.parseInt(text_tiempre.getText().toString());
                if(tp2>1){
                    tp2--;
                    text_tiempre.setText(Integer.toString(tp2));

                }
                break;
        }

    }
}
