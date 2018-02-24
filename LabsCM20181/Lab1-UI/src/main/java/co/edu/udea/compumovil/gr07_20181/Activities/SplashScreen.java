package co.edu.udea.compumovil.gr07_20181.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(SplashScreen.this,Menurest.class));
        finish();
    }
}
