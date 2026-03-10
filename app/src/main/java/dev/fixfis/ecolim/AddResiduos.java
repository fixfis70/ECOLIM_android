package dev.fixfis.ecolim;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

import dev.fixfis.ecolim.server.ApiClient;
import dev.fixfis.ecolim.server.EmptiesCalculator;
import dev.fixfis.ecolim.server.Metrics;
import dev.fixfis.ecolim.server.Result;
import dev.fixfis.ecolim.server.entities.DesperdicioDto;
import dev.fixfis.ecolim.server.entities.LugarDto;
import dev.fixfis.ecolim.server.entities.TipoResiduoDto;
import dev.fixfis.ecolim.server.request.AddDesperdicioTandaRequest;
import dev.fixfis.ecolim.stuff.DesperdicoAdapter;

public class AddResiduos extends AppCompatActivity {
    RecyclerView ryc;
    Spinner spin;
    EditText nombre,cantidad;
    Button enviar,cerrar;
    private void loadUI() {
        nombre = findViewById(R.id.add_residuo_nombre);
        spin = findViewById(R.id.add_residuo_spin);
        cantidad = findViewById(R.id.add_residuo_cantidad);
        cerrar = findViewById(R.id.add_residuo_cerrar);
        enviar = findViewById(R.id.add_residuo_enviar);
        ryc = findViewById(R.id.add_residuo_recycler);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_residuos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadUI();

        cargarLista();

        enviar.setOnClickListener(v->{
            enviarTanda();
        });
        cerrar.setOnClickListener(s->{
            cerrarTanda();
        });
    }
    boolean isEmpty = false;
    private void enviarTanda() {
        TipoResiduoDto dto = ((TipoResiduoDto) spin.getSelectedItem());
        String n = EmptiesCalculator.getValue(nombre,isEmpty);
        int c = Integer.parseInt( EmptiesCalculator.getValue(cantidad,isEmpty));
        if (isEmpty) {
            isEmpty =false;
            return;
        }

        AddDesperdicioTandaRequest addDesperdicioTandaRequest = new AddDesperdicioTandaRequest();
        DesperdicioDto desperdicioDto = new DesperdicioDto(
                null,
                n,
                c,
                dto.getId()
        );
        addDesperdicioTandaRequest.setDesperdicio(desperdicioDto);
        addDesperdicioTandaRequest.setIdTanda(Metrics.getIdTandaActiva());
        new Thread(()->{
            Result result = ApiClient.create("/tandas/addDesperdicio", AddDesperdicioTandaRequest.class)
                    .postter(addDesperdicioTandaRequest).result();

            runOnUiThread(()->{
                Toast.makeText(this,result.getMessage(),Toast.LENGTH_SHORT).show();
            });
        }).start();
    }
    private void cerrarTanda() {
        new Thread(()->{
            Result result = ApiClient.create("/tandas/closeTanda", UUID.class)
                    .postter(Metrics.getUserUUID()).result();
            runOnUiThread(()->{
                Toast.makeText(this,result.getMessage(),Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
            });
        }).start();
    }

    private void cargarLista() {



        new Thread(() -> {
            Object array = ApiClient.create("/tandas/getTableTanda",Long.class)
                    .postter(5L).result().getData();

            Type type = TypeToken
                    .getParameterized(List.class, DesperdicioDto.class)
                    .getType();

            List<DesperdicioDto> desperdicioDtoList = new Gson().fromJson(
                    array.toString(),type
            );

            List<TipoResiduoDto> tipoResiduoDtoList = ApiClient.create("/residuos/residuos").toList(TipoResiduoDto.class);
            // TODO agregar una forma para poder crear lugares desde este mismo panel

            runOnUiThread(() -> {
                DesperdicoAdapter desperdicoAdapter = new DesperdicoAdapter(desperdicioDtoList,this);
                ryc.setLayoutManager(new LinearLayoutManager(this));
                ryc.setAdapter(desperdicoAdapter);
                ArrayAdapter<TipoResiduoDto> adapter = new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        tipoResiduoDtoList
                );

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spin.setAdapter(adapter);
            });

        }).start();
    }
}