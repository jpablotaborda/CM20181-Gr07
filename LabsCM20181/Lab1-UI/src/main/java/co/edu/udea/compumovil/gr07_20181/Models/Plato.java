package co.edu.udea.compumovil.gr07_20181.Models;

import android.net.Uri;

/**
 * Created by Juan Pablo on 24/02/2018.
 */

public class Plato {
    private Uri foto_url;
    private String nombre;
    private String horario;
    private String tipo;
    private String tiem_pre;
    private String precio;
    private String ingredientes;

    public Plato(String nom,String hora,String tipo,String pre,String ingre,String tp, Uri ft){
        this.foto_url =ft ;
        this.nombre = nom ;
        this.horario =hora ;
        this.tipo = tipo;
        this. tiem_pre= tp;
        this.precio =pre ;
        this. ingredientes= ingre;

    }
    public Uri getFoto_url() {
        return foto_url;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHorario() {
        return horario;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTiem_pre() {
        return tiem_pre;
    }

    public String getPrecio() {
        return precio;
    }

    public String getIngredientes() {
        return ingredientes;
    }
}
