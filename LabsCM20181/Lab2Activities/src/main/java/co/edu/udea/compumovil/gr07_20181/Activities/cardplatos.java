package co.edu.udea.compumovil.gr07_20181.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import co.edu.udea.compumovil.gr07_20181.lab1.R;

public class cardplatos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardplatos);
        ImageView foto_card=(ImageView) findViewById(R.id.platos_foto);
        foto_card.setTag("");
    }
}
