package ecole.naji.tp4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button conexionBtn;
    private Button inscBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.accueil);

        conexionBtn = findViewById(R.id.button);
        inscBtn = findViewById(R.id.button2);

        conexionBtn.setOnClickListener(e->{
            startActivity(new Intent(this, connexionActivuty.class));
        });

        inscBtn.setOnClickListener(e->{
            startActivity(new Intent(this, incriptiionActivyt.class));
        });










    }

}