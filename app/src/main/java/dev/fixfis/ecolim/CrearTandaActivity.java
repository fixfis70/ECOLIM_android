package dev.fixfis.ecolim;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import dev.fixfis.ecolim.server.Metrics;
import dev.fixfis.ecolim.server.Result;
import dev.fixfis.ecolim.server.entities.LugarDto;
import dev.fixfis.ecolim.server.request.AdminRequierd;

public class CrearTandaActivity extends AppCompatActivity {
    Spinner spin;
    Button crear;
    private void loadUi() {
        spin = findViewById(R.id.crear_tanda_spiner);
        crear = findViewById(R.id.crear_tanda_crear);
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

        crear.setOnClickListener(v->{
            crea();
        });
    }

    private void crea() {
        LugarDto selectedItem = (LugarDto) spin.getSelectedItem();
        new Thread(()->{
            AdminRequierd<LugarDto> rqs = new AdminRequierd<>();
            rqs.setData(selectedItem);

            Result result = ApiClient.create("/tandas/crearTanda",AdminRequierd.class)
                    .postter(rqs).result();
            if (result.getError()==0){
                runOnUiThread(()->{
                    //TODO arreglar el problema, supuestamente .getData da un double

                    Metrics.setIdTandaActiva(((Number) result.getData()).longValue());
                    startActivity(new Intent(this,AddResiduos.class));
                });
            }
        }).start();
    }

    private void cargarLista() {
        new Thread(() -> {
            List<LugarDto> l = ApiClient.create("/lugares/").toList(LugarDto.class);

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