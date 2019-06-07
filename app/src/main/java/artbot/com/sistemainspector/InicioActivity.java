package artbot.com.sistemainspector;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(InicioActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },1000);
    }

    public boolean obtenerEstadoyDatos(){
        SharedPreferences preferences = getSharedPreferences("USER", Context.MODE_PRIVATE);
        return preferences.getBoolean("sesion",false);
    }
}
