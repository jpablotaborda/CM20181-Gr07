package co.edu.udea.compumovil.gr07_20181.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.udea.compumovil.gr07_20181.lab1.R;

public class ven_inicio_sesion extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ven_inicio_sesion);

        Button bot_crear_cuenta= (Button) findViewById(R.id.bot_crear_cuenta);
        Button bot_inicio_ses= (Button) findViewById(R.id.bot_inicio_ses);
        bot_crear_cuenta.setOnClickListener(this);
        bot_inicio_ses.setOnClickListener(this);
        //Iniciando toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("LabsCM20181");

        //DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        //mDrawerLayout.openDrawer(Gravity.START);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bot_crear_cuenta:
                Intent Lanzar_registrarse= new Intent(getApplicationContext(),ven_registro.class);
                startActivity(Lanzar_registrarse);
                break;
            case R.id.bot_inicio_ses:
                EditText edit_nombre= (EditText) findViewById(R.id.nom_usu);
                EditText edit_contra= (EditText) findViewById(R.id.contra_usu);

                if(edit_nombre.getText().toString().isEmpty()){
                    Toast.makeText(this, "Ingrese el nombre de usuario", Toast.LENGTH_SHORT).show();
                }
                else if(edit_contra.getText().toString().isEmpty()){
                    Toast.makeText(this, "Ingrese su contraseña", Toast.LENGTH_SHORT).show();
                }
                else {
                    SqlHelper conex_db = new SqlHelper(this, "bd_restauran", null, 1);
                    SQLiteDatabase db = conex_db.getReadableDatabase();

                    String[] parametros = {edit_nombre.getText().toString(), edit_contra.getText().toString()};//valor con el que buscaremos
                    String[] campos = {"nombre", "contrasena"};//valores que devolverá
                    Cursor c;
                    try {


                        c = db.rawQuery("SELECT " + campos[0] + "," + campos[1] + " FROM Usuarios WHERE nombre= '" + parametros[0] + "' AND contrasena='" + parametros[1] + "'", null);
                        c.moveToFirst();
                        if (c.getString(0).equals(parametros[0]) && c.getString(1).equals(parametros[1])){
                            Intent lanzar_menu= new Intent(this,Menurest.class);
                            startActivity(lanzar_menu);
                            this.finish();
                        }
                    }catch (Exception e){
                        Toast.makeText(this, "No coinciden usuario y contraseña", Toast.LENGTH_SHORT).show();
                    }
                }



                break;
            /*case R.id.tit_inicio_ses:
                DrawerLayout drawer= (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(Gravity.LEFT);*/
        }
    }
}
