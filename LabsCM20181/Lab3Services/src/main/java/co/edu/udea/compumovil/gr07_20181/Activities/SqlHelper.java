package co.edu.udea.compumovil.gr07_20181.Activities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Juan Pablo on 18/03/2018.
 */

public class SqlHelper extends SQLiteOpenHelper {
    private String Crear_tabla_usu= "CREATE TABLE Usuarios (id INTEGER PRIMARY KEY ,nombre TEXT, correo TEXT,contrasena TEXT, dir_imagen TEXT )";
    private String Crear_tabla_platos= "CREATE TABLE Platos(id INTEGER PRIMARY KEY , id_usuario INTEGER,  nombre TEXT,horario TEXT,tipo TEXT,tiem_pre TEXT,precio INT, ingredientes TEXT,dir_imagen TEXT)";
    private String Crear_tabla_bebidas= "CREATE TABLE Bebidas(id INTEGER PRIMARY KEY ,id_usuario INTEGER, nombre TEXT,ingredientes TEXT,precio INT,dir_imagen TEXT)";

    public SqlHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Crear_tabla_usu);
        sqLiteDatabase.execSQL(Crear_tabla_platos);
        sqLiteDatabase.execSQL(Crear_tabla_bebidas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int ver_ant, int ver_nue) {
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        onCreate(db);

    }
}
