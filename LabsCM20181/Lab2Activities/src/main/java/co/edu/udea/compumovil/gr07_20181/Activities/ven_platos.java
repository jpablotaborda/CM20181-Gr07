package co.edu.udea.compumovil.gr07_20181.Activities;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.compumovil.gr07_20181.Adapters.Adaptaplatos;
import co.edu.udea.compumovil.gr07_20181.Models.Bebida;
import co.edu.udea.compumovil.gr07_20181.Models.Plato;
import co.edu.udea.compumovil.gr07_20181.lab1.R;

import static android.app.Activity.RESULT_OK;

public class ven_platos extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerplatos;
    private List<Plato> lista_platos;
    private static final int SELECCIONAR_IMAGEN=100;
    private Uri Image_uri=null;
    private tiempre_picker picker;
    private Adaptaplatos adap_recycler_platos;
    private View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            return  inflater.inflate(R.layout.activity_ven_platos,container,false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        v= getView();
        recyclerplatos= (RecyclerView)v.findViewById(R.id.recyclerplatos);
        NestedScrollView scrollpla= (NestedScrollView) v.findViewById(R.id.scrollplatos);
        scrollpla.setNestedScrollingEnabled(true);


        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerplatos.setLayoutManager(linearLayoutManager);

        //Le planteó que maneje el menu del toolbar
        setHasOptionsMenu(true);

        lista_platos= new ArrayList<>();
        picker=new tiempre_picker();
        recyclerplatos.setVisibility(View.INVISIBLE);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        // Implementando métodos del formulaio registro de platos

        Button bot_regplato= (Button) v.findViewById(R.id.bot_regplato);
        Button sel_tiempre= (Button) v.findViewById(R.id.bot_tiempre_platoreg);
        Button sel_foto= (Button) v.findViewById(R.id.bot_foto_platoreg);
        bot_regplato.setOnClickListener(this);
        sel_tiempre.setOnClickListener(this);
        sel_foto.setOnClickListener(this);

        llenaralinicio_recyclerview();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bot_regplato:

                EditText nom_plato= (EditText) v.findViewById(R.id.edit_nomreg);
                CheckBox checkmañana= (CheckBox) v.findViewById(R.id.horario_platocheckmanana);
                CheckBox checktarde= (CheckBox) v.findViewById(R.id.horario_platochecktarde);
                CheckBox checknoche= (CheckBox) v.findViewById(R.id.horario_platochecknoche);
                RadioButton entrada=  (RadioButton) v.findViewById(R.id.tipoplatocheckentrada);
                RadioButton platofuerte=  (RadioButton) v.findViewById(R.id.tipoplatocheckplafuerte);
                EditText precio_pla= (EditText) v.findViewById(R.id.edit_precioreg);
                EditText ingredientes_pla= (EditText) v.findViewById(R.id.edit_ingredientesreg);
                //comprobando que los datos ingresados no estén vacios.

                if(nom_plato.getText().toString().isEmpty()){
                    Toast.makeText( getActivity().getApplicationContext(),"Debe ingresar el nombre del plato",Toast.LENGTH_SHORT).show();

                }
                else if(!checkmañana.isChecked() && !checktarde.isChecked() && !checknoche.isChecked()){
                    Toast.makeText( getActivity().getApplicationContext(),"Debe seleccionar un horario para el plato",Toast.LENGTH_SHORT).show();
                }
                else if(!entrada.isChecked()&& !platofuerte.isChecked()){
                    Toast.makeText( getActivity().getApplicationContext(),"Debe seleccionar un tipo de plato",Toast.LENGTH_SHORT).show();

                }
                else if(precio_pla.getText().toString().isEmpty()){
                    Toast.makeText( getActivity().getApplicationContext(),"Debe ingresar un precio para el plato",Toast.LENGTH_SHORT).show();

                }
                else if(ingredientes_pla.getText().toString().isEmpty()){
                    Toast.makeText( getActivity().getApplicationContext(),"Ingrese los ingredientes plato",Toast.LENGTH_SHORT).show();

                }
                else if(picker.tiempre==-1){
                    Toast.makeText(getActivity().getApplicationContext(),"Ingrese el tiempo de preparación.",Toast.LENGTH_SHORT).show();
                }
                else if(Image_uri==null){
                    Toast.makeText( getActivity().getApplicationContext(),"Debe seleccionar una imagen para el plato",Toast.LENGTH_SHORT).show();

                }


                else// si se validaron con exito todos los datos
                {
                    String hora=null;
                    String tipo= null;
                    if(checkmañana.isChecked()){
                        hora=checkmañana.getText().toString();
                    }
                    if(checktarde.isChecked()){
                    hora=checktarde.getText().toString();
                }
                    if(checknoche.isChecked()){
                        hora=checknoche.getText().toString();
                    }
                    if(entrada.isChecked()){
                        tipo=entrada.getText().toString();
                    }
                    if(platofuerte.isChecked()){
                        tipo=platofuerte.getText().toString();
                    }
                    SqlHelper conex_db = new SqlHelper(getContext(), "bd_restauran", null, 1);
                    SQLiteDatabase db= conex_db.getWritableDatabase();

                    ContentValues valores= new ContentValues();
                    SharedPreferences preferencias= getActivity().getSharedPreferences("Mis preferencias", Context.MODE_PRIVATE);
                    valores.put("id_usuario",preferencias.getString("id","idnull"));
                    valores.put("nombre",nom_plato.getText().toString());
                    valores.put("horario",hora);
                    valores.put("tipo",tipo);
                    valores.put("tiem_pre",picker.tiempre);
                    valores.put("precio",precio_pla.getText().toString());
                    valores.put("ingredientes",ingredientes_pla.getText().toString());
                    valores.put("dir_imagen",Image_uri.toString());
                    Long id= db.insert("Platos",null,valores);
                    lista_platos.add(new Plato(Image_uri,nom_plato.getText().toString(),hora,tipo,String.valueOf(picker.tiempre),precio_pla.getText().toString(),ingredientes_pla.getText().toString()));
                    adap_recycler_platos.notifyDataSetChanged();
                    Toast.makeText(getActivity().getApplicationContext(),"Los datos han sido ingresados con exito",Toast.LENGTH_SHORT).show();

                }

                break;
            case R.id.bot_tiempre_platoreg:
                FragmentManager control_frag= ((AppCompatActivity)getActivity()).getSupportFragmentManager();
                picker=new tiempre_picker();


                picker.show(control_frag,"Picker" );


                break;
            case R.id.bot_foto_platoreg:
                Intent lanzar_galeria= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                Fragment frag= this;
                frag.startActivityForResult(lanzar_galeria,SELECCIONAR_IMAGEN);

                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode ==RESULT_OK && requestCode==SELECCIONAR_IMAGEN){
            Image_uri=data.getData();

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.findItem(R.id.limpiar_menu).setVisible(true);
        menu.findItem(R.id.salir_menu).setVisible(true);
        super.onCreateOptionsMenu(menu, inflater);

    }

    private void Limpiar() {

        EditText nom_plato= (EditText) v.findViewById(R.id.edit_nomreg);
        CheckBox checkmañana= (CheckBox) v.findViewById(R.id.horario_platocheckmanana);
        CheckBox checktarde= (CheckBox) v.findViewById(R.id.horario_platochecktarde);
        CheckBox checknoche= (CheckBox) v.findViewById(R.id.horario_platochecknoche);
        EditText precio_pla= (EditText) v.findViewById(R.id.edit_precioreg);
        EditText ingredientes_pla= (EditText) v.findViewById(R.id.edit_ingredientesreg);
        RadioGroup radiogr= (RadioGroup) v.findViewById(R.id.radiogrplatoreg);
        nom_plato.setText("");
        checkmañana.setChecked(false);
        checktarde.setChecked(false);
        checknoche.setChecked(false);
        Image_uri=null;
        picker=new tiempre_picker();
        precio_pla.setText("");
        ingredientes_pla.setText("");


        radiogr.clearCheck();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.limpiar_menu:
                Limpiar();
                break;
            case R.id.salir_menu:
                perfil_usuario_rest per= new perfil_usuario_rest();
                getFragmentManager().beginTransaction().replace(R.id.framlay_nav_drag,per).commit();
                break;

        }
        return super.onOptionsItemSelected(item);

    }
    public void llenaralinicio_recyclerview(){
        SqlHelper conex= new SqlHelper(getContext(),"bd_restauran",null,1);
        SQLiteDatabase db= conex.getReadableDatabase();
        SharedPreferences preferencias= getActivity().getSharedPreferences("Mis preferencias",Context.MODE_PRIVATE);


        Cursor c=db.rawQuery("SELECT * FROM Platos",null);
        while (c.moveToNext()){
            if(String.valueOf(c.getInt(1)).equals(preferencias.getString("id","sinid"))){
                lista_platos.add(new Plato(Uri.parse(c.getString(8)),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(6)));
            }



        }
        adap_recycler_platos= new Adaptaplatos(lista_platos);
        recyclerplatos.setVisibility(View.VISIBLE);
        recyclerplatos.setAdapter(adap_recycler_platos);

    }
}
