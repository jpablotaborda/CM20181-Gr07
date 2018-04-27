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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.compumovil.gr07_20181.Adapters.Adaptabebidas;
import co.edu.udea.compumovil.gr07_20181.Models.Bebida;
import co.edu.udea.compumovil.gr07_20181.lab1.R;

public class listabebidas extends Fragment implements View.OnClickListener {
    private View v;
    private RecyclerView recyclerbebidas;
    private List<Bebida> lista_bebidas;
    private Adaptabebidas adap_recycler_bebidas;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_listabebidas,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        v=getView();
        recyclerbebidas= (RecyclerView)v.findViewById(R.id.recyclerbebidas);
        NestedScrollView scrollbeb= (NestedScrollView) v.findViewById(R.id.scrollbebidas);
        scrollbeb.setNestedScrollingEnabled(true);

        setHasOptionsMenu(true);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerbebidas.setLayoutManager(linearLayoutManager);
        lista_bebidas= new ArrayList<>();
        FloatingActionButton fab= (FloatingActionButton)v.findViewById(R.id.fabbebidas);
        fab.setOnClickListener(this);
        llenaralinicio_recyclerview();
        adap_recycler_bebidas.notifyDataSetChanged();

    }
    public void llenaralinicio_recyclerview(){
        SqlHelper conex= new SqlHelper(getContext(),"bd_restauran",null,1);
        SQLiteDatabase db= conex.getReadableDatabase();
        SharedPreferences preferencias= getActivity().getSharedPreferences("Mis preferencias", Context.MODE_PRIVATE);


        Cursor c=db.rawQuery("SELECT * FROM Bebidas",null);
        while (c.moveToNext()){
            if(String.valueOf(c.getInt(1)).equals(preferencias.getString("id","sinid"))){
                lista_bebidas.add(new Bebida(Uri.parse(c.getString(5)),c.getString(2),c.getString(3),c.getString(4)));
            }



        }
        adap_recycler_bebidas= new Adaptabebidas(getContext(),lista_bebidas);
        recyclerbebidas.setVisibility(View.VISIBLE);
        recyclerbebidas.setAdapter(adap_recycler_bebidas);

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.findItem(R.id.buscar_nombre).setVisible(true);
        menu.findItem(R.id.buscar_precio).setVisible(true);
        super.onCreateOptionsMenu(menu, inflater);

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.buscar_nombre:

                break;
            case R.id.buscar_precio:

                break;

        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fabbebidas:
                Intent lanzar_regbebidas= new Intent(getContext(),ven_bebidas.class);
                startActivity(lanzar_regbebidas);
                adap_recycler_bebidas.notifyDataSetChanged();
                break;

        }
    }
}
