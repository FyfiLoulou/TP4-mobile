package ecole.naji.tp4;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ecole.naji.tp4.R;

public class UpdateActivite extends Fragment {

    private EditText emailInput;
    private EditText pwInput;
    private EditText nomInput;
    private EditText adressInput;
    private EditText telInput;
    private Button buttonUpdate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.update_dude, container, false);
        initEditField(rootView);
        return rootView;
    }

    private void initEditField(View rootView) {

        nomInput = rootView.findViewById(R.id.nom2);
        adressInput = rootView.findViewById(R.id.adresse2);
        telInput = rootView.findViewById(R.id.tel2);
        emailInput = rootView.findViewById(R.id.email2);
        pwInput = rootView.findViewById(R.id.pw2);
        buttonUpdate = rootView.findViewById(R.id.updateButton);

        emailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isValidate(emailInput.getText().toString())) {
                    emailInput.setError("Addresse Courriel non valide");
                    buttonUpdate.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        nomInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (nomInput.getText().toString().length() <= 2) {
                    nomInput.setError("Nom non valide");
                    buttonUpdate.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        pwInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (pwInput.getText().toString().length() < 3) {
                    pwInput.setError("Mot de passe trop court");
                    buttonUpdate.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        telInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (telInput.getText().toString().length() < 9) {
                    telInput.setError("Numéro de téléphone non valide");
                    buttonUpdate.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        buttonUpdate.setOnClickListener(e -> {
            String nom = nomInput.getText().toString();
            String email = emailInput.getText().toString();
            String pw = pwInput.getText().toString();
            String adresse = adressInput.getText().toString();
            String tel = telInput.getText().toString();

            startActivity(new Intent(getActivity(), connexionActivuty.class));
            // TODO Add database update
        });
    }

    private boolean isValidate(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }
}
