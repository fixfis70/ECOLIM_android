package dev.fixfis.ecolim;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.UUID;

import dev.fixfis.ecolim.server.ApiClient;
import dev.fixfis.ecolim.server.EmptiesCalculator;
import dev.fixfis.ecolim.server.Metrics;
import dev.fixfis.ecolim.server.Result;
import dev.fixfis.ecolim.server.request.LoginRequest;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button login;

    private void loadUi() {
        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_pass);
        login = findViewById(R.id.login_logearse);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadUi();
        login.setOnClickListener(v ->{
            logearse();
        });

    }
    boolean isEmpty = false;
    private void logearse() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(EmptiesCalculator.getValue(username,isEmpty));
        loginRequest.setPassword(EmptiesCalculator.getValue(password,isEmpty));
        if (isEmpty) {
            isEmpty=false;
            return;
        }
        new Thread(() -> {

            Result result = ApiClient.create("/crerol/login", LoginRequest.class)
                    .postter(loginRequest).result();

            runOnUiThread(() -> {

                Toast.makeText(this,result.getMessage(),Toast.LENGTH_SHORT).show();

                if (result.getError()==0){
                    Metrics.setUserUUID(UUID.fromString((String) result.getData()));

                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }

            });

        }).start();
    }
}