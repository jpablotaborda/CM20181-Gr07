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

public class ven_bebidas extends AppCompatActivity implements View.OnClickListener {



    private static final int SELECCIONAR_IMAGEN=100;
    private Uri Image_uri=null;

    private boolean primeravez=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ven_bebidas);

        // Implementando métodos del formulaio registro de bebidas

        Button bot_regbebida= (Button) findViewById(R.id.bot_regbebida);

        Button sel_foto= (Button) findViewById(R.id.bot_foto_bebidareg);
        bot_regbebida.setOnClickListener(this);

        sel_foto.setOnClickListener(this);
        //Iniciando toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("LabsCM20181");

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bot_regbebida:

                EditText nom_bebida= (EditText) findViewById(R.id.edit_nom);





                EditText ingredientes_beb= (EditText) findViewById(R.id.edit_ingredientes);
                EditText precio_beb= (EditText) findViewById(R.id.edit_precio);
                //comprobando que los datos ingresados no estén vacios.

                if(nom_bebida.getText().toString().isEmpty()){
                    Toast.makeText( getApplicationContext(),"Debe ingresar el nombre de la bebida",Toast.LENGTH_SHORT).show();

                }
                else if(ingredientes_beb.getText().toString().isEmpty()){
                    Toast.makeText( getApplicationContext(),"Ingrese los ingredientes de la bebida",Toast.LENGTH_SHORT).show();

                }
                else if(precio_beb.getText().toString().isEmpty() || !precio_beb.getText().toString().matches("[0-9]+")){
                    Toast.makeText( getApplicationContext(),"Debe ingresar un precio para la bebida",Toast.LENGTH_SHORT).show();

                }
                else if(Image_uri==null){
                    Toast.makeText( getApplicationContext(),"Debe seleccionar una imagen para la bebida",Toast.LENGTH_SHORT).show();

                }
                else// si se validaron con exito todos los datos
                {
                    SqlHelper conex_db = new SqlHelper(this, "bd_restauran", null, 1);
                    SQLiteDatabase db= conex_db.getWritableDatabase();

                    ContentValues valores= new ContentValues();
                    SharedPreferences preferencias= getSharedPreferences("Mis preferencias", Context.MODE_PRIVATE);
                    valores.put("id_usuario",preferencias.getString("id","idnull"));
                    valores.put("nombre",nom_bebida.getText().toString());
                    valores.put("precio",precio_beb.getText().toString());
                    valores.put("ingredientes",ingredientes_beb.getText().toString());
                    valores.put("dir_imagen",Image_uri.toString());
                    Long id= db.insert("Bebidas",null,valores);


                    Toast.makeText(getApplicationContext(),"Los datos han sido ingresados con exito",Toast.LENGTH_SHORT).show();
                    db.close();
                    this.finish();

                }


                break;

            case R.id.bot_foto_bebidareg:
                Intent lanzar_galeria= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(lanzar_galeria,SELECCIONAR_IMAGEN);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    private void Limpiar() {

        EditText nom_bebida= (EditText) findViewById(R.id.edit_nom);

        EditText precio_pla= (EditText) findViewById(R.id.edit_precio);
        EditText ingredientes_bebida= (EditText) findViewById(R.id.edit_ingredientes);
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
                this.finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
