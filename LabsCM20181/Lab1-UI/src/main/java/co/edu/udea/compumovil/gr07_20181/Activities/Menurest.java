package co.edu.udea.compumovil.gr07_20181.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import co.edu.udea.compumovil.gr07_20181.lab1.R;

public class Menurest extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menurest);
        Button bot_platos=(Button) findViewById(R.id.bot_platos);
        Button bot_bebidas=(Button) findViewById(R.id.bot_bebidas);

        bot_platos.setOnClickListener(this);
        bot_bebidas.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bot_platos:
                Intent lanzar_platos= new Intent(getApplicationContext(),ven_platos.class);
                startActivity(lanzar_platos);
                break;
            case R.id.bot_bebidas:
                Intent lanzar_bebidas= new Intent(getApplicationContext(),ven_bebidas.class);
                startActivity(lanzar_bebidas);
                break;
        }
    }
}
