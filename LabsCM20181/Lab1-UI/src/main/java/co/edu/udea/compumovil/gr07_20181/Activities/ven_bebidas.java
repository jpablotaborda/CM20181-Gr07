package co.edu.udea.compumovil.gr07_20181.Activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.compumovil.gr07_20181.Adapters.Adaptabebidas;
import co.edu.udea.compumovil.gr07_20181.Models.Bebida;
import co.edu.udea.compumovil.gr07_20181.lab1.R;

public class ven_bebidas extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerbebidas;
    private List<Bebida> lista_bebidas;
    private static final int SELECCIONAR_IMAGEN=100;
    private Uri Image_uri=null;
    private Adaptabebidas adap_recycler_bebidas;
    private boolean primeravez=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ven_bebidas);

        //configurando recyclerview

        recyclerbebidas= (RecyclerView)findViewById(R.id.recyclerbebidas);
        NestedScrollView scrollbeb= (NestedScrollView) findViewById(R.id.scrollbebidas);
        scrollbeb.setNestedScrollingEnabled(true);


        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerbebidas.setLayoutManager(linearLayoutManager);



        lista_bebidas= new ArrayList<>();

        recyclerbebidas.setVisibility(View.INVISIBLE);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);





        // Implementando métodos del formulaio registro de bebidas

        Button bot_regbebida= (Button) findViewById(R.id.bot_regbebida);

        Button sel_foto= (Button) findViewById(R.id.bot_foto_bebidareg);
        bot_regbebida.setOnClickListener(this);

        sel_foto.setOnClickListener(this);
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
                else if(precio_beb.getText().toString().isEmpty()){
                    Toast.makeText( getApplicationContext(),"Debe ingresar un precio para la bebida",Toast.LENGTH_SHORT).show();

                }
                else if(Image_uri==null){
                    Toast.makeText( getApplicationContext(),"Debe seleccionar una imagen para la bebida",Toast.LENGTH_SHORT).show();

                }
                else// si se validaron con exito todos los datos
                {
                    lista_bebidas.add(new Bebida(Image_uri,nom_bebida.getText().toString(),ingredientes_beb.getText().toString(),precio_beb.getText().toString()));


                    if(primeravez){
                        adap_recycler_bebidas= new Adaptabebidas(lista_bebidas);
                        recyclerbebidas.setVisibility(View.VISIBLE);
                        recyclerbebidas.setAdapter(adap_recycler_bebidas);
                        primeravez=false;
                    }
                    adap_recycler_bebidas.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(),"Los datos han sido ingresados con exito",Toast.LENGTH_SHORT).show();

                }


                break;

            case R.id.bot_foto_bebidareg:
                Intent lanzar_galeria= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(lanzar_galeria,SELECCIONAR_IMAGEN);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode ==RESULT_OK && requestCode==SELECCIONAR_IMAGEN){
            Image_uri=data.getData();

        }

    }
}
