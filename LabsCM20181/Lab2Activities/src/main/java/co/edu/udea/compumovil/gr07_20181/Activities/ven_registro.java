package co.edu.udea.compumovil.gr07_20181.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.zip.Inflater;

import co.edu.udea.compumovil.gr07_20181.lab1.R;

public class ven_registro extends AppCompatActivity implements View.OnClickListener {


    private final int SEL_IMAGEN=150;
    private String dir_ima=null;
    private SqlHelper conex_db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ven_registro);

        conex_db= new SqlHelper(this, "bd_restauran",null,1);

        //Referenciando controles

        Button bot_sel_foto= (Button) findViewById(R.id.bot_sel_foto_usu);
        Button bot_reg_usu= (Button) findViewById(R.id.bot_reg_usu);

        bot_sel_foto.setOnClickListener(this);
        bot_reg_usu.setOnClickListener(this);
        //Iniciando toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("LabsCM20181");
        //Desactivando navigation drawer
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);



    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.bot_sel_foto_usu:
                Intent lanzar_galeria= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(lanzar_galeria,SEL_IMAGEN);

                break;
            case R.id.bot_reg_usu:

                EditText nombre= (EditText) findViewById(R.id.edit_nom_reg_usu);
                EditText correo= (EditText) findViewById(R.id.edit_correo_reg_usu);
                EditText contra= (EditText) findViewById(R.id.edit_contra_reg_usu);
                if(nombre.getText().toString().isEmpty()){
                    Toast.makeText(this, "Debe ingresar un nombre de usuario", Toast.LENGTH_SHORT).show();
                }
                else if(correo.getText().toString().isEmpty()){
                    Toast.makeText(this, "Debe ingresar un correo", Toast.LENGTH_SHORT).show();
                }
                else if(contra.getText().toString().isEmpty()){
                    Toast.makeText(this, "Debe ingresar una contraseña", Toast.LENGTH_SHORT).show();
                }
                else if(dir_ima==null){
                    Toast.makeText(this, "Debe seleccionar una imagen", Toast.LENGTH_SHORT).show();
                }
                else{
                    SQLiteDatabase db= conex_db.getWritableDatabase();
                    //Se ingresarán los datos a la tabla usuarios

                    ContentValues valores= new ContentValues();
                    valores.put("nombre",nombre.getText().toString() );
                    valores.put("correo",correo.getText().toString());
                    valores.put("contrasena", contra.getText().toString());
                    valores.put("dir_imagen",dir_ima);
                    Long id= db.insert("Usuarios","id",valores);
                    Toast.makeText(this, "Se ha insertado el usuario", Toast.LENGTH_SHORT).show();
                    db.close();
                    this.finish();



                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== RESULT_OK && requestCode==SEL_IMAGEN){
            Uri Imageuri= data.getData();
            dir_ima= Imageuri.toString();



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
                EditText nombre= (EditText) findViewById(R.id.edit_nom_reg_usu);
                EditText correo= (EditText) findViewById(R.id.edit_correo_reg_usu);
                EditText contra= (EditText) findViewById(R.id.edit_contra_reg_usu);
                nombre.setText("");
                correo.setText("");
                contra.setText("");
                dir_ima=null;
                break;
            case R.id.salir_menu:
                this.finish();
                break;
        }
            return true;
    }
}
