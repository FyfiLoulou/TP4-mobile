package ecole.naji.tp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ecole.naji.tp4.R;

public class connexionActivuty extends AppCompatActivity {
    private EditText emailInput;
    private EditText pwInput;
    private Button buttonConnection;
    private Button buttonNouveauCOmpet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conexion);
        initEditField();
    }

    private void initEditField() {

        emailInput = findViewById(R.id.editTextText2);
        buttonConnection = findViewById(R.id.button4);
        buttonNouveauCOmpet = findViewById(R.id.button3);
        pwInput = findViewById(R.id.editTextText3);
        emailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!isValidate(emailInput.getText().toString())) {
                    emailInput.setError("Addresse Courriel non valide");
                    buttonConnection.setEnabled(false);
                }

            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        pwInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (pwInput.getText().toString().length() <5) {
                    pwInput.setError("Mot de passe pas bon");
                    buttonConnection.setEnabled(false);
                }

            }
            @Override
            public void afterTextChanged(Editable s) {}
        });


        buttonConnection.setOnClickListener(e->{
            startActivity(new Intent(this, CommandesActivty.class));
            // TODO check account in BD
        });

        buttonNouveauCOmpet.setOnClickListener(e->{
            startActivity(new Intent(this, incriptiionActivyt.class));
        });


    }
    private boolean isValidate(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

}