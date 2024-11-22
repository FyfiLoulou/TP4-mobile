package ecole.naji.tp4;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateActivite extends AppCompatActivity {
    private EditText emailInput;
    private EditText pwInput;
    private EditText nomInpout;
    private EditText adressInput;
    private EditText telInpout;
    private Button buttonUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_dude);
        initEditField();
    }

    private void initEditField() {

        nomInpout = findViewById(R.id.nom2);
        adressInput = findViewById(R.id.adresse2);
        telInpout = findViewById(R.id.tel2);
        emailInput = findViewById(R.id.email2);
        pwInput = findViewById(R.id.pw2);
        buttonUpdate = findViewById(R.id.updateButton);

        emailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isValidate(emailInput.getText().toString())) {
                    emailInput.setError("Addresse Courriel non valide");
                    buttonUpdate.setEnabled(false);
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
                    buttonUpdate.setEnabled(false);
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
                    buttonUpdate.setEnabled(false);
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
                    buttonUpdate.setEnabled(false);
                }}
            @Override
            public void afterTextChanged(Editable s) {}
        });


        buttonUpdate.setOnClickListener(e->{
            String nom = nomInpout.getText().toString(), email = emailInput.getText().toString(), pw = pwInput.getText().toString(), adresse = adressInput.getText().toString(), tel = telInpout.getText().toString();

            startActivity(new Intent(this, connexionActivuty.class));
            // TODO UPDATE STORE BD
        });

    }
    private boolean isValidate(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }
}