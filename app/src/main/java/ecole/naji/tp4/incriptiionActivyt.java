package ecole.naji.tp4;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ecole.naji.tp4.R;
import ecole.naji.tp4.models.Client;

public class incriptiionActivyt extends Fragment {

    private EditText emailInput;
    private EditText pwInput;
    private EditText nomInput;
    private EditText adressInput;
    private EditText telInput;
    private Button buttonCrrer;
    private DatabaseManger data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.inscription, container, false);
        data = DatabaseManger.getInstance(getContext());

        initEditField(rootView);
        return rootView;
    }

    private void initEditField(View rootView) {

        nomInput = rootView.findViewById(R.id.nom);
        adressInput = rootView.findViewById(R.id.adresse);
        telInput = rootView.findViewById(R.id.tel);
        emailInput = rootView.findViewById(R.id.email);
        pwInput = rootView.findViewById(R.id.pw);
        buttonCrrer = rootView.findViewById(R.id.creer);
        buttonCrrer.setEnabled(false);

        emailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isValidate(emailInput.getText().toString())) {
                    emailInput.setError("Addresse Courriel non valide");
                    buttonCrrer.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        nomInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (nomInput.getText().toString().length() <= 2) {
                    nomInput.setError("Nom non valide");
                    buttonCrrer.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        pwInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (pwInput.getText().toString().length() < 3) {
                    pwInput.setError("Mot de passe trop court");
                    buttonCrrer.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        telInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buttonCrrer.setEnabled(true);
                if (telInput.getText().toString().length() < 9) {
                    telInput.setError("Numéro de téléphone non valide");
                    buttonCrrer.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        buttonCrrer.setOnClickListener(e -> {
            String nom = nomInput.getText().toString();
            String email = emailInput.getText().toString();
            String pw = pwInput.getText().toString();
            String adresse = adressInput.getText().toString();
            String tel = telInput.getText().toString();

            Client newCLient = new Client(nom, email, pw, adresse, tel, 10);
            Log.w("lol", newCLient.toString());
            data.insertClient(newCLient);

            getParentFragmentManager().beginTransaction().replace(R.id.fragment, new connexionActivuty()).commit();
        });
    }

    private boolean isValidate(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }
}
