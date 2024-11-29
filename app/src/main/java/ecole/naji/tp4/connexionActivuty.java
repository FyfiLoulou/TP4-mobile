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

public class connexionActivuty extends Fragment {

    private EditText emailInput;
    private EditText pwInput;
    private Button buttonConnection;
    private Button buttonNouveauCompet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.conexion, container, false);

        initEditField(rootView);
        return rootView;
    }

    private void initEditField(View rootView) {

        emailInput = rootView.findViewById(R.id.editTextText2);
        buttonConnection = rootView.findViewById(R.id.button4);
        buttonNouveauCompet = rootView.findViewById(R.id.button3);
        pwInput = rootView.findViewById(R.id.editTextText3);

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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (pwInput.getText().toString().length() < 5) {
                    pwInput.setError("Mot de passe trop court");
                    buttonConnection.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        buttonConnection.setOnClickListener(e -> {

            //startActivity(new Intent(getActivity(), CommandesActivty.class));
        });

        buttonNouveauCompet.setOnClickListener(e -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment, new CommandesActivty())
                    .commit();
        });
    }

    private boolean isValidate(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }
}
