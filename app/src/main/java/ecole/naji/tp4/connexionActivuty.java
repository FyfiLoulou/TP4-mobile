package ecole.naji.tp4;

import android.annotation.SuppressLint;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.navigation.NavigationView;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import ecole.naji.tp4.models.Client;

public class connexionActivuty extends Fragment {

    private EditText emailInput;
    private EditText pwInput;
    private TextView error;
    private Button buttonConnection;
    private Button buttonNouveauCompet;
    private DatabaseManger data;

    /**
     * Inflates the layout and initializes the view elements, including setting up
     * the email and password validation logic, the login button functionality,
     * and the navigation to the registration screen.
     *
     * @param inflater           The LayoutInflater used to inflate the fragment's layout.
     * @param container          The parent view that the fragment will be attached to.
     * @param savedInstanceState A bundle containing the saved state of the fragment, if any.
     * @return The root view for the fragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.conexion, container, false);
        data = DatabaseManger.getInstance(getContext());
        initEditField(rootView);
        return rootView;
    }

    /**
     * Initializes the input fields, buttons, and their respective event listeners.
     * It also validates the user input for email and password fields.
     *
     * @param rootView The root view of the fragment, used to access the UI components.
     */
    private void initEditField(View rootView) {

        emailInput = rootView.findViewById(R.id.editTextText2);
        buttonConnection = rootView.findViewById(R.id.button44);
        buttonNouveauCompet = rootView.findViewById(R.id.button3);
        pwInput = rootView.findViewById(R.id.editTextText3);
        error = rootView.findViewById(R.id.textView122);

        // Disable connection button initially
        buttonConnection.setEnabled(false);

        // Set up email validation
        emailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isValidate(emailInput.getText().toString())) {
                    emailInput.setError("Addresse Courriel non valide");
                    buttonConnection.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Set up password validation (minimum length of 5 characters)
        pwInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buttonConnection.setEnabled(true);
                if (pwInput.getText().toString().length() < 5) {
                    pwInput.setError("Mot de passe trop court");
                    buttonConnection.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Set up login button click listener
        buttonConnection.setOnClickListener(e -> {
            String emailValue = emailInput.getText().toString(), pwValue = String.valueOf(pwInput.getText());

            MainActivity a = (MainActivity) getActivity();
            FragmentManager aa = getParentFragmentManager();

            // Check if the email and password match a user in the database
            Optional<Client> client = data.readClientelle().stream().filter(u -> Objects.equals(u.getEmail(), emailValue) && Objects.equals(u.getPw(), pwValue)).findFirst();
            if (client.isPresent()) {
                Client c = client.get();
                MainActivity.userConnected = c.getId();

                // Replace the current fragment with the ListPizza fragment
                aa.beginTransaction().replace(R.id.fragment, new ListPizza()).commit();
                Log.i("lol", c.getId() + " (←clientId) is connected");

                // Update the navigation drawer and show the user's profile button
                DrawerLayout dLayout = a.findViewById(R.id.drawer_layout);
                NavigationView navView = a.findViewById(R.id.navigation);
                navView.inflateMenu(R.menu.connected_nav_item);
                a.findViewById(R.id.proifileincontochange).setVisibility(View.VISIBLE);
                a.findViewById(R.id.proifileincontochange).setOnClickListener(ee -> {
                    aa.beginTransaction().replace(R.id.fragment, new UpdateProfileTime()).commit();
                });

                // Handle navigation item selection
                navView.setNavigationItemSelectedListener(item -> {
                    Fragment fragment = MainActivity.getFragment(item);
                    Toast.makeText(a.getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                    if (fragment != null) {
                        a.getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();
                        dLayout.closeDrawers();
                        return true;
                    }
                    return false;
                });

            } else {
                error.setText("Le compte n'existe pas");
            }
        });

        // Set up the "new user" button to navigate to the registration screen
        buttonNouveauCompet.setOnClickListener(e -> {
            getParentFragmentManager().beginTransaction().replace(R.id.fragment, new incriptiionActivyt()).commit();
        });
    }

    /**
     * Validates the email address using a regular expression.
     *
     * @param email The email address entered by the user.
     * @return true if the email matches the valid pattern, false otherwise.
     */
    private boolean isValidate(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }
}
