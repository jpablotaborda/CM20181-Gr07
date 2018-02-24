package co.edu.udea.compumovil.gr07_20181.Activities;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.compumovil.gr07_20181.Adapters.Adaptaplatos;
import co.edu.udea.compumovil.gr07_20181.Models.Plato;
import co.edu.udea.compumovil.gr07_20181.lab1.R;

public class ven_platos extends AppCompatActivity {

    private RecyclerView recyclerplatos;
    private List<Plato> lista_platos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ven_platos);
        iniciar_lista();
        recyclerplatos= (RecyclerView)findViewById(R.id.recyclerplatos);
        NestedScrollView scrollpla= (NestedScrollView) findViewById(R.id.scrollplatos);
        scrollpla.setNestedScrollingEnabled(true);
        recyclerplatos.setHasFixedSize(true);// aumenta rendimiento ponerlo si se sabe que el tamaño del layout no cambiará

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerplatos.setLayoutManager(linearLayoutManager);

        Adaptaplatos adap_recycler_platos= new Adaptaplatos(lista_platos);
        recyclerplatos.setAdapter(adap_recycler_platos);

    }

    private void iniciar_lista() {
        lista_platos= new ArrayList<>();
        lista_platos.add(new Plato(R.drawable.rancheros,"Frijoles Rancheros","20/02/2018","Plato Fuerte","1:30H","12.000$","- Frijoles, - Salchicha Ranchera, - Zanahoria, -Platano, -Chicharron"));
        lista_platos.add(new Plato(R.drawable.rancheros,"Frijoles Rancheros","20/02/2018","Plato Fuerte","1:30H","12.000$","- Frijoles, - Salchicha Ranchera, - Zanahoria, -Platano, -Chicharron"));
        lista_platos.add(new Plato(R.drawable.rancheros,"Frijoles Rancheros","20/02/2018","Plato Fuerte","1:30H","12.000$","- Frijoles, - Salchicha Ranchera, - Zanahoria, -Platano, -Chicharron"));
        lista_platos.add(new Plato(R.drawable.rancheros,"Frijoles Rancheros","20/02/2018","Plato Fuerte","1:30H","12.000$","- Frijoles, - Salchicha Ranchera, - Zanahoria, -Platano, -Chicharron"));

    }
}
