package co.edu.udea.compumovil.gr07_20181.Models;

/**
 * Created by Juan Pablo on 18/03/2018.
 */

public class Usuario {
    private String nombre;
    private String correo;
    private String contra;
    private String dir_imagen;

    public Usuario(String n, String cor, String con, String dir){

        this.nombre= n;
        this.correo= cor;
        this.contra=con;
        this.dir_imagen=dir;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getDir_imagen() {
        return dir_imagen;
    }

    public void setDir_imagen(String dir_imagen) {
        this.dir_imagen = dir_imagen;
    }
}
