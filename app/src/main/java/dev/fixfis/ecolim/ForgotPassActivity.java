package dev.fixfis.ecolim;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import dev.fixfis.ecolim.server.ApiClient;
import dev.fixfis.ecolim.server.Result;
import dev.fixfis.ecolim.server.request.CambiarPassRequest;

public class ForgotPassActivity extends AppCompatActivity {
    EditText oldp,newp;
    Button btn;

    private void loadUI() {
        oldp = findViewById(R.id.reset_old);
        newp = findViewById(R.id.reset_new);
        btn = findViewById(R.id.reset_btn);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot_pass);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        loadUI();

        btn.setOnClickListener(v->{
            reset();
        });
    }

    private void reset() {
        CambiarPassRequest cambiarPassRequest = new CambiarPassRequest();
        cambiarPassRequest.setNewPassword(newp.getText().toString());
        cambiarPassRequest.setOldPassword(oldp.getText().toString());
        new Thread(()->{
            Result result = ApiClient.create("/crerol/users", CambiarPassRequest.class)
                    .putter(cambiarPassRequest).result();
            runOnUiThread(()->{
                Toast.makeText(this,result.getMessage(),Toast.LENGTH_SHORT).show();
            });
        }).start();

    }

}