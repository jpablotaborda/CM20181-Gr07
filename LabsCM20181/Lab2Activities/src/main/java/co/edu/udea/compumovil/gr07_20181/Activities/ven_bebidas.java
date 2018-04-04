package co.edu.udea.compumovil.gr07_20181.Activities;

import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.compumovil.gr07_20181.Adapters.Adaptabebidas;
import co.edu.udea.compumovil.gr07_20181.Adapters.Adaptaplatos;
import co.edu.udea.compumovil.gr07_20181.Models.Bebida;
import co.edu.udea.compumovil.gr07_20181.Models.Plato;
import co.edu.udea.compumovil.gr07_20181.lab1.R;

import static android.app.Activity.RESULT_OK;

public class ven_bebidas extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerbebidas;
    private List<Bebida> lista_bebidas;
    private static final int SELECCIONAR_IMAGEN=100;
    private Uri Image_uri=null;
    private Adaptabebidas adap_recycler_bebidas;
    private boolean primeravez=true;
    private View v;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_ven_bebidas,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        v=getView();
        recyclerbebidas= (RecyclerView)v.findViewById(R.id.recyclerbebidas);
        NestedScrollView scrollbeb= (NestedScrollView) v.findViewById(R.id.scrollbebidas);
        scrollbeb.setNestedScrollingEnabled(true);


        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerbebidas.setLayoutManager(linearLayoutManager);

        setHasOptionsMenu(true);

        lista_bebidas= new ArrayList<>();

        recyclerbebidas.setVisibility(View.INVISIBLE);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);





        // Implementando métodos del formulaio registro de bebidas

        Button bot_regbebida= (Button) v.findViewById(R.id.bot_regbebida);

        Button sel_foto= (Button) v.findViewById(R.id.bot_foto_bebidareg);
        bot_regbebida.setOnClickListener(this);

        sel_foto.setOnClickListener(this);

        llenaralinicio_recyclerview();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bot_regbebida:

                EditText nom_bebida= (EditText) v.findViewById(R.id.edit_nom);





                EditText ingredientes_beb= (EditText) v.findViewById(R.id.edit_ingredientes);
                EditText precio_beb= (EditText) v.findViewById(R.id.edit_precio);
                //comprobando que los datos ingresados no estén vacios.

                if(nom_bebida.getText().toString().isEmpty()){
                    Toast.makeText( getActivity().getApplicationContext(),"Debe ingresar el nombre de la bebida",Toast.LENGTH_SHORT).show();

                }
                else if(ingredientes_beb.getText().toString().isEmpty()){
                    Toast.makeText( getActivity().getApplicationContext(),"Ingrese los ingredientes de la bebida",Toast.LENGTH_SHORT).show();

                }
                else if(precio_beb.getText().toString().isEmpty() || !precio_beb.getText().toString().matches("[0-9]+")){
                    Toast.makeText( getActivity().getApplicationContext(),"Debe ingresar un precio para la bebida",Toast.LENGTH_SHORT).show();

                }
                else if(Image_uri==null){
                    Toast.makeText( getActivity().getApplicationContext(),"Debe seleccionar una imagen para la bebida",Toast.LENGTH_SHORT).show();

                }
                else// si se validaron con exito todos los datos
                {
                    SqlHelper conex_db = new SqlHelper(getContext(), "bd_restauran", null, 1);
                    SQLiteDatabase db= conex_db.getWritableDatabase();

                    ContentValues valores= new ContentValues();
                    SharedPreferences preferencias= getActivity().getSharedPreferences("Mis preferencias", Context.MODE_PRIVATE);
                    valores.put("id_usuario",preferencias.getString("id","idnull"));
                    valores.put("nombre",nom_bebida.getText().toString());
                    valores.put("precio",precio_beb.getText().toString());
                    valores.put("ingredientes",ingredientes_beb.getText().toString());
                    valores.put("dir_imagen",Image_uri.toString());
                    Long id= db.insert("Bebidas",null,valores);
                    lista_bebidas.add(new Bebida(Image_uri,nom_bebida.getText().toString(),ingredientes_beb.getText().toString(),precio_beb.getText().toString()));
                    adap_recycler_bebidas.notifyDataSetChanged();
                    Toast.makeText(getActivity().getApplicationContext(),"Los datos han sido ingresados con exito",Toast.LENGTH_SHORT).show();

                }


                break;

            case R.id.bot_foto_bebidareg:
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

        EditText nom_bebida= (EditText) v.findViewById(R.id.edit_nom);

        EditText precio_pla= (EditText) v.findViewById(R.id.edit_precio);
        EditText ingredientes_bebida= (EditText) v.findViewById(R.id.edit_ingredientes);
        nom_bebida.setText("");
        Image_uri=null;
        precio_pla.setText("");
        ingredientes_bebida.setText("");




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


        Cursor c=db.rawQuery("SELECT * FROM Bebidas",null);
        while (c.moveToNext()){
            if(String.valueOf(c.getInt(1)).equals(preferencias.getString("id","sinid"))){
                lista_bebidas.add(new Bebida(Uri.parse(c.getString(5)),c.getString(2),c.getString(3),c.getString(4)));
            }



        }
        adap_recycler_bebidas= new Adaptabebidas(lista_bebidas);
        recyclerbebidas.setVisibility(View.VISIBLE);
        recyclerbebidas.setAdapter(adap_recycler_bebidas);

    }
}
