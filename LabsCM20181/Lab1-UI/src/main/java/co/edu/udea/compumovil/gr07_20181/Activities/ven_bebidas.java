package co.edu.udea.compumovil.gr07_20181.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ven_bebidas);

        //configurando recyclerview
        iniciar_lista();
        recyclerbebidas= (RecyclerView)findViewById(R.id.recyclerbebidas);
        NestedScrollView scrollbeb= (NestedScrollView) findViewById(R.id.scrollbebidas);
        scrollbeb.setNestedScrollingEnabled(true);
        recyclerbebidas.setHasFixedSize(true);// aumenta rendimiento ponerlo si se sabe que el tamaño del layout no cambiará

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerbebidas.setLayoutManager(linearLayoutManager);

        Adaptabebidas adap_recycler_bebidas= new Adaptabebidas(lista_bebidas);
        recyclerbebidas.setAdapter(adap_recycler_bebidas);

        // Implementando métodos del formulaio registro de platos

        Button bot_regbebida= (Button) findViewById(R.id.bot_bebidas);
        Button sel_foto= (Button) findViewById(R.id.bot_foto_bebidareg);
        bot_regbebida.setOnClickListener(this);
        sel_foto.setOnClickListener(this);
    }

    private void iniciar_lista() {
        lista_bebidas= new ArrayList<>();
        lista_bebidas.add(new Bebida(Image_uri,"Frijoles Rancheros","- Frijoles, - Salchicha Ranchera, - Zanahoria, -Platano, -Chicharron","12.000$"));
        lista_bebidas.add(new Bebida(Image_uri,"Frijoles Rancheros","- Frijoles, - Salchicha Ranchera, - Zanahoria, -Platano, -Chicharron","12.000$"));
        lista_bebidas.add(new Bebida(Image_uri,"Frijoles Rancheros","- Frijoles, - Salchicha Ranchera, - Zanahoria, -Platano, -Chicharron","12.000$"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bot_regbebidas:

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
                    Toast.makeText( getApplicationContext(),"Debe seleccionar una imagen para el plato",Toast.LENGTH_SHORT).show();

                }
                else// si se validaron con exito todos los datos
                {

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
