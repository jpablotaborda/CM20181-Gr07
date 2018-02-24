package co.edu.udea.compumovil.gr07_20181.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.edu.udea.compumovil.gr07_20181.Models.Plato;
import co.edu.udea.compumovil.gr07_20181.lab1.R;

/**
 * Created by Juan Pablo on 24/02/2018.
 */

public class Adaptaplatos extends RecyclerView.Adapter<Adaptaplatos.platoviewholder> {
    List<Plato> platos;
    public Adaptaplatos(List<Plato> platillos){
        this.platos= platillos;
    }
    @Override
    public platoviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cardplatos, parent,false);
        platoviewholder pvh= new platoviewholder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(platoviewholder holder, int position) {
        holder.platofot.setImageResource(platos.get(position).getFoto_url());
        holder.platonombre.setText(platos.get(position).getNombre());
        holder.platohora.setText(platos.get(position).getHorario());
        holder.platotipo.setText(platos.get(position).getTipo());
        holder.platotiempre.setText(platos.get(position).getTiem_pre());
        holder.platopre.setText(platos.get(position).getPrecio());
        holder.platoingre.setText(platos.get(position).getIngredientes());

    }

    @Override
    public int getItemCount() {
        return platos.size();
    }

    public static class platoviewholder extends RecyclerView.ViewHolder {
        public CardView platocard;
        public ImageView platofot;
        public TextView platonombre;
        public TextView platohora;
        public TextView platotipo;
        public TextView platotiempre;
        public TextView platopre;
        public TextView platoingre;

        public platoviewholder(View itemView) {
            super(itemView);

             platocard=(CardView) itemView.findViewById(R.id.cardplatos);
            platofot=(ImageView) itemView.findViewById(R.id.platos_foto);
            platonombre=(TextView) itemView.findViewById(R.id.platos_nombre) ;
            platohora=(TextView)itemView.findViewById(R.id.platos_horario);
            platotipo=(TextView) itemView.findViewById(R.id.platos_tipo);
            platotiempre= (TextView)itemView.findViewById(R.id.platos_tiempre) ;
            platopre=(TextView) itemView.findViewById(R.id.platos_precio);
            platoingre=(TextView)itemView.findViewById(R.id.platos_ingredientes) ;

        }



    }

}
