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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.compumovil.gr07_20181.Adapters.Adaptaplatos;
import co.edu.udea.compumovil.gr07_20181.Models.Plato;
import co.edu.udea.compumovil.gr07_20181.lab1.R;

public class ven_platos extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerplatos;
    private List<Plato> lista_platos;
    private static final int SELECCIONAR_IMAGEN=100;
    private Uri Image_uri=null;
    private tiempre_picker picker;
    private Adaptaplatos adap_recycler_platos;
    private boolean primeravez=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ven_platos);

        //configurando recyclerview

        recyclerplatos= (RecyclerView)findViewById(R.id.recyclerplatos);
        NestedScrollView scrollpla= (NestedScrollView) findViewById(R.id.scrollplatos);
        scrollpla.setNestedScrollingEnabled(true);


        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerplatos.setLayoutManager(linearLayoutManager);



        lista_platos= new ArrayList<>();
        picker=new tiempre_picker();
        recyclerplatos.setVisibility(View.INVISIBLE);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //seteando barra menu
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("LabsCM20181");
        // Implementando métodos del formulaio registro de platos

        Button bot_regplato= (Button) findViewById(R.id.bot_regplato);
        Button sel_tiempre= (Button) findViewById(R.id.bot_tiempre_platoreg);
        Button sel_foto= (Button) findViewById(R.id.bot_foto_platoreg);
        bot_regplato.setOnClickListener(this);
        sel_tiempre.setOnClickListener(this);
        sel_foto.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bot_regplato:

                EditText nom_plato= (EditText) findViewById(R.id.edit_nomreg);
                CheckBox checkmañana= (CheckBox) findViewById(R.id.horario_platocheckmanana);
                CheckBox checktarde= (CheckBox) findViewById(R.id.horario_platochecktarde);
                CheckBox checknoche= (CheckBox) findViewById(R.id.horario_platochecknoche);
                RadioButton entrada=  (RadioButton) findViewById(R.id.tipoplatocheckentrada);
                RadioButton platofuerte=  (RadioButton) findViewById(R.id.tipoplatocheckplafuerte);
                EditText precio_pla= (EditText) findViewById(R.id.edit_precioreg);
                EditText ingredientes_pla= (EditText) findViewById(R.id.edit_ingredientesreg);
                //comprobando que los datos ingresados no estén vacios.

                if(nom_plato.getText().toString().isEmpty()){
                    Toast.makeText( getApplicationContext(),"Debe ingresar el nombre del plato",Toast.LENGTH_SHORT).show();

                }
                else if(!checkmañana.isChecked() && !checktarde.isChecked() && !checknoche.isChecked()){
                    Toast.makeText( getApplicationContext(),"Debe seleccionar un horario para el plato",Toast.LENGTH_SHORT).show();
                }
                else if(!entrada.isChecked()&& !platofuerte.isChecked()){
                    Toast.makeText( getApplicationContext(),"Debe seleccionar un tipo de plato",Toast.LENGTH_SHORT).show();

                }
                else if(precio_pla.getText().toString().isEmpty()){
                    Toast.makeText( getApplicationContext(),"Debe ingresar un precio para el plato",Toast.LENGTH_SHORT).show();

                }
                else if(ingredientes_pla.getText().toString().isEmpty()){
                    Toast.makeText( getApplicationContext(),"Ingrese los ingredientes plato",Toast.LENGTH_SHORT).show();

                }
                else if(picker.tiempre==-1){
                    Toast.makeText(getApplicationContext(),"Ingrese el tiempo de preparación.",Toast.LENGTH_SHORT).show();
                }
                else if(Image_uri==null){
                    Toast.makeText( getApplicationContext(),"Debe seleccionar una imagen para el plato",Toast.LENGTH_SHORT).show();

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

                    lista_platos.add(new Plato(nom_plato.getText().toString(),hora,tipo,precio_pla.getText().toString(),ingredientes_pla.getText().toString(),Integer.toString(picker.tiempre) + " min",Image_uri));


                    if(primeravez){
                        adap_recycler_platos= new Adaptaplatos(lista_platos);
                        recyclerplatos.setVisibility(View.VISIBLE);
                        recyclerplatos.setAdapter(adap_recycler_platos);
                        primeravez=false;
                    }
                    adap_recycler_platos.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(),"Los datos han sido ingresados con exito",Toast.LENGTH_SHORT).show();

                }

                break;
            case R.id.bot_tiempre_platoreg:
                FragmentManager control_frag= getFragmentManager();
                picker=new tiempre_picker();

                picker.show(control_frag,"picker");


                break;
            case R.id.bot_foto_platoreg:
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.limpiar_menu:
                Limpiar();
                break;
            case R.id.salir_menu :
                this.finish();
                break;
        }
        return true;
    }

    private void Limpiar() {

        EditText nom_plato= (EditText) findViewById(R.id.edit_nomreg);
        CheckBox checkmañana= (CheckBox) findViewById(R.id.horario_platocheckmanana);
        CheckBox checktarde= (CheckBox) findViewById(R.id.horario_platochecktarde);
        CheckBox checknoche= (CheckBox) findViewById(R.id.horario_platochecknoche);
        EditText precio_pla= (EditText) findViewById(R.id.edit_precioreg);
        EditText ingredientes_pla= (EditText) findViewById(R.id.edit_ingredientesreg);
        RadioGroup radiogr= (RadioGroup) findViewById(R.id.radiogrplatoreg);
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
}
