package co.edu.udea.compumovil.gr07_20181.Activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.ExploreByTouchHelper;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import co.edu.udea.compumovil.gr07_20181.lab1.R;

public class nd_restaurant extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nd_restaurant);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarnd);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Restaurante asados y bebidas");





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        Fragment frag_perfil= new perfil_usuario_rest();
        FragmentTransaction transac= getSupportFragmentManager().beginTransaction();
        transac.replace(R.id.framlay_nav_drag,frag_perfil);
        transac.commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_viewnd);
        navigationView.setNavigationItemSelectedListener(this);
        //Creación de contenedor para la personalización del usuario
        SharedPreferences preferencias= getSharedPreferences("Mis preferencias", Context.MODE_PRIVATE);

        SharedPreferences.Editor  editor= preferencias.edit();
        //Obtención de datos de base de datos
        SqlHelper sqlHelper = new SqlHelper(this,"bd_restauran",null,1);
        SQLiteDatabase db= sqlHelper.getReadableDatabase();
        Cursor c;
        String us=getIntent().getExtras().getString("usuario");
        String con=getIntent().getExtras().getString("contrasena");

        String[] parametros={us,con};//valores para la busqueda
        String [] campos={"id","nombre","correo","contrasena","dir_imagen"};//valores que devolverá
        try{
            c = db.rawQuery("SELECT " + campos[0] + "," + campos[1] + ","+ campos[2] + ","+ campos[3] + ","+ campos[4] + " FROM Usuarios WHERE nombre= '" + parametros[0] + "' AND contrasena='" + parametros[1] + "'", null);
            c.moveToFirst();

            editor.putString("id",c.getString(0));
            editor.putString("nombre",c.getString(1));
            editor.putString("correo",c.getString(2));
            editor.putString("contrasena",c.getString(3));
            editor.putString("dir_imagen",c.getString(4));
            editor.commit();



            NavigationView navview= (NavigationView) findViewById(R.id.nav_viewnd);

            ImageView imand= (ImageView)navview.getHeaderView(0).findViewById(R.id.imageViewnd);
            TextView nomnd= (TextView) navview.getHeaderView(0).findViewById(R.id.nd_nombre_usu);
            imand.setImageURI(Uri.parse(c.getString(4)));
            nomnd.setText(c.getString(1));




        }
        catch (Exception e){
            Toast.makeText(this, "No se pudieron obtener los datos de usuario", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.limpiar_menu) {
            return false;
        }
        if (id == R.id.salir_menu) {
            return false;
        }


        return false;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here.
            int id = item.getItemId();
            FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
            Toolbar barra= (Toolbar) findViewById(R.id.toolbarnd);

            if (id == R.id.nd_plato) {
                ven_platos venPlatos= new ven_platos();
                transaction.replace(R.id.framlay_nav_drag,venPlatos);
                barra.inflateMenu(R.menu.menu);
                setSupportActionBar(barra);


            } else if (id == R.id.nd_bebida) {
                ven_bebidas venBebidas= new ven_bebidas();
                transaction.replace(R.id.framlay_nav_drag,venBebidas);
                barra.inflateMenu(R.menu.menu);
                setSupportActionBar(barra);

            } else if (id == R.id.nd_perfil) {
                perfil_usuario_rest perfilUsuarioRest= new perfil_usuario_rest();
                transaction.replace(R.id.framlay_nav_drag,perfilUsuarioRest);


            } else if (id == R.id.nd_configuracion) {
                Intent lanzar_con= new Intent(this,configuracion.class);
                startActivity(lanzar_con);
            } else if (id == R.id.nd_cerrar_sesion) {
                this.finish();

            } else if (id == R.id.nd_acercade) {
                Toast.makeText(this, "Sistema desarrollado para: Restaurantes asados y bebidas, En el curso de computación movil. 2018.", Toast.LENGTH_LONG).show();

            }
            transaction.commit();




            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawer,barra,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.setDrawerIndicatorEnabled(true);
            toggle.syncState();
            drawer.closeDrawer(GravityCompat.START);
            return true;
    }
}
