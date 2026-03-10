package dev.fixfis.ecolim;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import dev.fixfis.ecolim.server.ApiClient;
import dev.fixfis.ecolim.server.entities.LugarDto;

public class CrearTandaActivity extends AppCompatActivity {
    Spinner spin;

    private void loadUi() {
        spin = findViewById(R.id.crear_tanda_spiner);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear_tanda);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadUi();

        cargarLista();
    }

    private void cargarLista() {
        new Thread(() -> {
            Type t = new TypeToken<List<LugarDto>>() {
            }.getType();

            List<LugarDto> l = new Gson().fromJson(
                    ApiClient.create("/lugares/").getter(JsonArray.class),
                    t
            );
            // TODO agregar una forma para poder crear lugares desde este mismo panel

            runOnUiThread(() -> {
                ArrayAdapter<LugarDto> adapter = new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        l
                );

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spin.setAdapter(adapter);
            });

        }).start();
    }
}