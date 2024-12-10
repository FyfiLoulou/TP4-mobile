package ecole.naji.tp4;

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

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ecole.naji.tp4.models.Client;

public class UpdateProfileTime extends Fragment {

    private EditText emailInput;
    private EditText pwInput;
    private EditText nomInput;
    private EditText adressInput;
    private EditText telInput;
    private Button updateButn;
    private DatabaseManger data;
    private Client connectedClient;


    /**
     * Called when the fragment's view is created. This method initializes the UI components,
     * sets the current client's data into the input fields, and sets up input validation for each field.
     *
     * @param inflater           The LayoutInflater object used to inflate the fragment's layout.
     * @param container          The container in which the fragment's view will be placed.
     * @param savedInstanceState A Bundle containing any saved state data (if any).
     * @return The root view of the fragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.update_dude, container, false);
        data = DatabaseManger.getInstance(getContext());

        // Fetch the currently logged-in client from the database
        connectedClient = data.readClientelle().stream().filter(u -> u.getId() == MainActivity.userConnected).findFirst().get();

        initEditField(rootView);
        return rootView;
    }

    /**
     * Initializes the input fields with the current user's data and sets up text change listeners
     * for validation of each field (name, email, password, phone number).
     *
     * @param rootView The root view of the fragment.
     */
    private void initEditField(View rootView) {

        nomInput = rootView.findViewById(R.id.nom2);
        adressInput = rootView.findViewById(R.id.adresse2);
        telInput = rootView.findViewById(R.id.tel2);
        emailInput = rootView.findViewById(R.id.email2);
        pwInput = rootView.findViewById(R.id.pw2);
        updateButn = rootView.findViewById(R.id.updateButton);
        updateButn.setEnabled(false);

        nomInput.setText(connectedClient.getNom());
        adressInput.setText(connectedClient.getAdresse());
        telInput.setText(connectedClient.getTel());
        emailInput.setText(connectedClient.getEmail());
        pwInput.setText(connectedClient.getPw());

        // Add validation logic for email
        emailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isValidate(emailInput.getText().toString())) {
                    emailInput.setError("Addresse Courriel non valide");
                    updateButn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Add validation logic for name
        nomInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (nomInput.getText().toString().length() <= 2) {
                    nomInput.setError("Nom non valide");
                    updateButn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Add validation logic for password
        pwInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (pwInput.getText().toString().length() < 3) {
                    pwInput.setError("Mot de passe trop court");
                    updateButn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Add validation logic for phone number
        telInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateButn.setEnabled(true);
                if (telInput.getText().toString().length() < 9) {
                    telInput.setError("Numéro de téléphone non valide");
                    updateButn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Set the button click action to update the user's data
        updateButn.setOnClickListener(e -> {
            String nom = nomInput.getText().toString();
            String email = emailInput.getText().toString();
            String pw = pwInput.getText().toString();
            String adresse = adressInput.getText().toString();
            String tel = telInput.getText().toString();

            // Update the connected client's data
            connectedClient.setNom(nom);
            connectedClient.setEmail(email);
            connectedClient.setPw(pw);
            connectedClient.setAdresse(adresse);
            connectedClient.setTel(tel);

            // Save the updated client data to the database
            data.updateClient(connectedClient);

            getParentFragmentManager().beginTransaction().replace(R.id.fragment, new ListPizza()).commit();
        });
    }

    private boolean isValidate(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }
}
