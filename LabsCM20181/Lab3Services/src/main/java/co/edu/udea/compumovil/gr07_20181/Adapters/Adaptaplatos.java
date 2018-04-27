package co.edu.udea.compumovil.gr07_20181.Adapters;

import android.app.Activity;
import android.app.Fragment;
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

import co.edu.udea.compumovil.gr07_20181.Activities.detalleplatos;
import co.edu.udea.compumovil.gr07_20181.Models.Plato;
import co.edu.udea.compumovil.gr07_20181.lab1.R;

/**
 * Created by Juan Pablo on 24/02/2018.
 */

public class Adaptaplatos extends RecyclerView.Adapter<Adaptaplatos.platoviewholder> {
    List<Plato> platos;
    public  Context contexto;
    public Adaptaplatos(Context contex, List<Plato> platillos){
        this.contexto=contex;
        this.platos= platillos;
    }
    @Override
    public platoviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cardplatos, parent,false);
        platoviewholder pvh= new platoviewholder(contexto,view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(platoviewholder holder, int position) {
        holder.platofot.setImageURI(platos.get(position).getFoto_url());
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

    public static class platoviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView platocard;
        public ImageView platofot;
        public TextView platonombre;
        public TextView platohora;
        public TextView platotipo;
        public TextView platotiempre;
        public TextView platopre;
        public TextView platoingre;
        private Context contexto;

        public platoviewholder(Context contex, View itemView) {
            super(itemView);
            this.contexto=contex;
            platocard=(CardView) itemView.findViewById(R.id.cardplatos);
            platofot=(ImageView) itemView.findViewById(R.id.platos_foto);
            platonombre=(TextView) itemView.findViewById(R.id.platos_nombre) ;
            platohora=(TextView)itemView.findViewById(R.id.platos_horario);
            platotipo=(TextView) itemView.findViewById(R.id.platos_tipo);
            platotiempre= (TextView)itemView.findViewById(R.id.platos_tiempre) ;
            platopre=(TextView) itemView.findViewById(R.id.platos_precio);
            platoingre=(TextView)itemView.findViewById(R.id.platos_ingredientes) ;
            platocard.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.cardplatos:
                    detalleplatos detallepla= new detalleplatos();
                    platofot=(ImageView) view.findViewById(R.id.platos_foto);
                    platonombre=(TextView) view.findViewById(R.id.platos_nombre) ;
                    platohora=(TextView)view.findViewById(R.id.platos_horario);
                    platotipo=(TextView) view.findViewById(R.id.platos_tipo);
                    platotiempre= (TextView)view.findViewById(R.id.platos_tiempre) ;
                    platopre=(TextView) view.findViewById(R.id.platos_precio);
                    platoingre=(TextView)view.findViewById(R.id.platos_ingredientes) ;
                    Bundle contene= new Bundle();
                    if(((BitmapDrawable)platofot.getDrawable())!=null){
                        contene.putParcelable("p_dir_foto",((BitmapDrawable)platofot.getDrawable()).getBitmap());
                    }
                    contene.putString("p_nombre",platonombre.getText().toString());
                    contene.putString("p_hora",platohora.getText().toString());
                    contene.putString("p_tipo",platotipo.getText().toString());
                    contene.putString("p_tiempre",platotiempre.getText().toString());
                    contene.putString("p_precio",platopre.getText().toString());
                    contene.putString("p_ingredientes",platoingre.getText().toString());
                    detallepla.setArguments(contene);
                    ((AppCompatActivity)contexto).getSupportFragmentManager().beginTransaction().replace(R.id.framlay_nav_drag,detallepla).addToBackStack(null).commit();




                    break;

            }
        }
    }
}
