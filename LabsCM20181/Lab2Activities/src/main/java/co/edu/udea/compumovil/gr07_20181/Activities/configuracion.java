package co.edu.udea.compumovil.gr07_20181.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import co.edu.udea.compumovil.gr07_20181.lab1.R;
/**
 * Created by Juan Pablo on 04/04/2018.
 */

public class configuracion extends PreferenceActivity{

    private EditTextPreference edittextcontra;
    private  EditTextPreference edittextcorreo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.configuracion);
        edittextcontra= (EditTextPreference) findPreference("cam_contrasena");
        String contra_act=getSharedPreferences("Mis preferencias", Context.MODE_PRIVATE).getString("contrasena","Sincontrasena");
        edittextcontra.setText(contra_act);

        edittextcorreo= (EditTextPreference) findPreference("cam_correo");
        String correo_act= getSharedPreferences("Mis preferencias", Context.MODE_PRIVATE).getString("correo","Sincorreo");
        edittextcorreo.setText(correo_act);
        CheckBoxPreference check_sesion= (CheckBoxPreference) findPreference("man_sesion");
        check_sesion.setChecked(true);



    }


}
