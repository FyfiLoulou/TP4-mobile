package ecole.naji.tp4;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class incriptiionActivyt extends AppCompatActivity {
    private EditText emailInput;
    private EditText pwInput;
    private EditText nomInpout;
    private EditText adressInput;
    private EditText telInpout;
    private Button buttonCrrer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conexion);
        initEditField();
    }

    private void initEditField() {

        nomInpout = findViewById(R.id.nom);
        adressInput = findViewById(R.id.adresse);
        telInpout = findViewById(R.id.tel);
        emailInput = findViewById(R.id.email);
        pwInput = findViewById(R.id.pw);
        buttonCrrer = findViewById(R.id.creer);

        emailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isValidate(emailInput.getText().toString())) {
                    emailInput.setError("Addresse Courriel non valide");
                    buttonCrrer.setEnabled(false);
                }}
            @Override
            public void afterTextChanged(Editable s) {}
        });

        nomInpout.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (nomInpout.getText().toString().length() > 2) {
                    nomInpout.setError("Nom non pas non valide");
                    buttonCrrer.setEnabled(false);
                }}
            @Override
            public void afterTextChanged(Editable s) {}
        });

        pwInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (pwInput.getText().toString().length() < 3) {
                    pwInput.setError("Pas bon mot passe");
                    buttonCrrer.setEnabled(false);
                }}
            @Override
            public void afterTextChanged(Editable s) {}
        });

        telInpout.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (nomInpout.getText().toString().length() < 9) {
                    nomInpout.setError("Télephone non valide");
                    buttonCrrer.setEnabled(false);
                }}
            @Override
            public void afterTextChanged(Editable s) {}
        });


        buttonCrrer.setOnClickListener(e->{
            startActivity(new Intent(this, connexionActivuty.class));
            // TODO STORE BD
        });

    }
    private boolean isValidate(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }
}