package co.edu.udea.compumovil.gr07_20181.Models;

/**
 * Created by Juan Pablo on 24/02/2018.
 */

public class Bebida {
    private int foto_url;
    private String nombre;
    private String ingredientes;
    private String precio;

    public Bebida(int ft,String nom,String ingre,String pre ){
        this.foto_url =ft ;
        this.nombre = nom ;
        this.ingredientes= ingre;
        this.precio =pre ;
    }

    public int getFoto_url() {
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
