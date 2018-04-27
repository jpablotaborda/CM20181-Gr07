package co.edu.udea.compumovil.gr07_20181.Adapters;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.edu.udea.compumovil.gr07_20181.Activities.detallebebidas;
import co.edu.udea.compumovil.gr07_20181.Activities.detalleplatos;
import co.edu.udea.compumovil.gr07_20181.Models.Bebida;
import co.edu.udea.compumovil.gr07_20181.lab1.R;

/**
 * Created by jose-gm on 28/02/18.
 */

public class Adaptabebidas extends RecyclerView.Adapter<Adaptabebidas.bebidaviewholder> {
    List<Bebida> bebidas;
    public Context contexto;
    public Adaptabebidas(Context context,List<Bebida> bebids){

        this.bebidas= bebids;
        this.contexto=context;
    }
    @Override
    public bebidaviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cardbebidas, parent,false);
        bebidaviewholder bvh= new bebidaviewholder(contexto,view);
        return bvh;
    }

    @Override
    public void onBindViewHolder(bebidaviewholder holder, int position) {
        holder.bebidafot.setImageURI(bebidas.get(position).getFoto_url());
        holder.bebidanombre.setText(bebidas.get(position).getNombre());
        holder.bebidaingre.setText(bebidas.get(position).getIngredientes());
        holder.bebidapre.setText(bebidas.get(position).getPrecio());
    }

    @Override
    public int getItemCount() {
        return bebidas.size();
    }

    public static class bebidaviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView bebidacard;
        public ImageView bebidafot;
        public TextView bebidanombre;
        public TextView bebidaingre;
        public TextView bebidapre;
        public Context contexto;

        public bebidaviewholder(Context context,View itemView) {
            super(itemView);

            bebidacard=(CardView) itemView.findViewById(R.id.cardbebidas);
            bebidafot=(ImageView) itemView.findViewById(R.id.bebidas_foto);
            bebidanombre=(TextView) itemView.findViewById(R.id.bebidas_nombre) ;
            bebidaingre=(TextView)itemView.findViewById(R.id.bebidas_ingredientes) ;
            bebidapre=(TextView) itemView.findViewById(R.id.bebidas_precio);
            bebidacard.setOnClickListener(this);
            this.contexto=context;

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.cardbebidas:
                    detallebebidas detallebebi= new detallebebidas();
                    bebidafot=(ImageView) view.findViewById(R.id.bebidas_foto);
                    bebidanombre=(TextView) view.findViewById(R.id.bebidas_nombre) ;
                    bebidapre=(TextView) view.findViewById(R.id.bebidas_precio);
                    bebidaingre=(TextView)view.findViewById(R.id.bebidas_ingredientes) ;
                    Bundle contene= new Bundle();
                    if(((BitmapDrawable)bebidafot.getDrawable())!=null){
                        contene.putParcelable("b_dir_foto",((BitmapDrawable)bebidafot.getDrawable()).getBitmap());
                    }
                    contene.putString("b_nombre",bebidanombre.getText().toString());
                    contene.putString("b_precio",bebidapre.getText().toString());
                    contene.putString("b_ingredientes",bebidaingre.getText().toString());
                    detallebebi.setArguments(contene);
                    ((AppCompatActivity)contexto).getSupportFragmentManager().beginTransaction().replace(R.id.framlay_nav_drag,detallebebi).addToBackStack(null).commit();




                    break;

            }
        }
    }
}
