package co.edu.udea.compumovil.gr07_20181.Models;

import android.net.Uri;





public class Bebida {
    private Uri foto_url;
    private String nombre;
    private String ingredientes;
    private String precio;

    public Bebida(Uri ft,String nom,String ingre,String pre ){
        this.foto_url =ft ;
        this.nombre = nom ;
        this.ingredientes= ingre;
        this.precio =pre ;
    }

    public Uri getFoto_url() {
        return foto_url;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public String getPrecio() { return precio; }

}
