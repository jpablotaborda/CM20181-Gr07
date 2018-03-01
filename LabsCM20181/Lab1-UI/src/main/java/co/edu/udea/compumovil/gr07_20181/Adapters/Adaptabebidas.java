package co.edu.udea.compumovil.gr07_20181.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.edu.udea.compumovil.gr07_20181.Models.Bebida;
import co.edu.udea.compumovil.gr07_20181.lab1.R;

/**
 * Created by jose-gm on 28/02/18.
 */

public class Adaptabebidas extends RecyclerView.Adapter<Adaptabebidas.bebidaviewholder> {
    List<Bebida> bebidas;
    public Adaptabebidas(List<Bebida> bebids){
        this.bebidas= bebids;
    }
    @Override
    public bebidaviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cardbebidas, parent,false);
        bebidaviewholder bvh= new bebidaviewholder(view);
        return bvh;
    }

    @Override
    public void onBindViewHolder(bebidaviewholder holder, int position) {
        holder.bebidafot.setImageResource(bebidas.get(position).getFoto_url());
        holder.bebidanombre.setText(bebidas.get(position).getNombre());
        holder.bebidaingre.setText(bebidas.get(position).getIngredientes());
        holder.bebidapre.setText(bebidas.get(position).getPrecio());
    }

    @Override
    public int getItemCount() {
        return bebidas.size();
    }

    public static class bebidaviewholder extends RecyclerView.ViewHolder {
        public CardView bebidacard;
        public ImageView bebidafot;
        public TextView bebidanombre;
        public TextView bebidaingre;
        public TextView bebidapre;

        public bebidaviewholder(View itemView) {
            super(itemView);

            bebidacard=(CardView) itemView.findViewById(R.id.cardbebidas);
            bebidafot=(ImageView) itemView.findViewById(R.id.bebidas_foto);
            bebidanombre=(TextView) itemView.findViewById(R.id.bebidas_nombre) ;
            bebidaingre=(TextView)itemView.findViewById(R.id.bebidas_ingredientes) ;
            bebidapre=(TextView) itemView.findViewById(R.id.bebidas_precio);

        }
    }
}
