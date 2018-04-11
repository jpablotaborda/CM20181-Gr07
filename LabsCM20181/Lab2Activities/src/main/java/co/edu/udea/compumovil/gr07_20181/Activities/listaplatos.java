package co.edu.udea.compumovil.gr07_20181.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.compumovil.gr07_20181.Adapters.Adaptaplatos;
import co.edu.udea.compumovil.gr07_20181.Models.Plato;
import co.edu.udea.compumovil.gr07_20181.lab1.R;

public class listaplatos extends Fragment implements View.OnClickListener {
    View v;
    private List<Plato> lista_platos;
    private Adaptaplatos adap_recycler_platos;
    private   RecyclerView recyclerplatos;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.activity_listaplatos,container,false);
        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerplatos= (RecyclerView)v.findViewById(R.id.recyclerplatos);
        NestedScrollView scrollpla= (NestedScrollView) v.findViewById(R.id.scrollplatos);
        scrollpla.setNestedScrollingEnabled(true);
        setHasOptionsMenu(true);
        FloatingActionButton fab=(FloatingActionButton) v.findViewById(R.id.fabplatos);
        fab.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerplatos.setLayoutManager(linearLayoutManager);
        llenaralinicio_recyclerview();
        adap_recycler_platos.notifyDataSetChanged();

    }
    public void llenaralinicio_recyclerview(){
        SqlHelper conex= new SqlHelper(getContext(),"bd_restauran",null,1);
        SQLiteDatabase db= conex.getReadableDatabase();
        SharedPreferences preferencias= getActivity().getSharedPreferences("Mis preferencias", Context.MODE_PRIVATE);

        lista_platos= new ArrayList<>();
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


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.findItem(R.id.buscar_nombre).setVisible(true);
        menu.findItem(R.id.buscar_precio).setVisible(true);
        super.onCreateOptionsMenu(menu, inflater);

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.buscar_nombre:
                Toast.makeText(getContext(), "Funciono", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buscar_precio:

                break;

        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fabplatos:
                Intent lanzar_reg_ven= new Intent(getContext(),ven_platos.class);
                startActivity(lanzar_reg_ven);
                adap_recycler_platos.notifyDataSetChanged();
                break;

        }
    }
}
