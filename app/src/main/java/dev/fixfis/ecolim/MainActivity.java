package dev.fixfis.ecolim;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.JsonElement;

import java.util.UUID;

import dev.fixfis.ecolim.server.ApiClient;
import dev.fixfis.ecolim.server.Metrics;
import dev.fixfis.ecolim.server.Result;

public class MainActivity extends AppCompatActivity {
    Button presentacion, crearTanda, verTandas, Cambiarpass;

    private void loadButtons() {
        presentacion = findViewById(R.id.main_aboutme);
        crearTanda = findViewById(R.id.main_creartandas);
        verTandas = findViewById(R.id.main_vertandas);
        Cambiarpass = findViewById(R.id.main_cambiarpass);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadButtons();

        crearTanda.setOnClickListener(v->{irATandas();});

    }

    private void irATandas() {
        new Thread(()->{
            JsonElement jsonElement = ApiClient.create("/tandas/getTanda", UUID.class).postter(Metrics.getUserUUID()).devResult();
            long l = Long.parseLong(jsonElement.toString());
            Metrics.setIdTandaActiva(l);

            Class<?> clzz;
            if (l<0) {
                clzz = CrearTandaActivity.class;
            } else {
                clzz = AddResiduos.class;
            }

            runOnUiThread(()->{
                btntoact(clzz);
            });
        }).start();
    }


    void btntoact(Class<?> z){
        startActivity(new Intent(this,z));
    }
}