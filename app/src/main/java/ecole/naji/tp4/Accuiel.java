package ecole.naji.tp4;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class Accuiel extends Fragment {

    Button conn;
    Button signup;
    DrawerLayout dLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.accueil2, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> dLayout.openDrawer(Gravity.LEFT));
        setNavigationDrawer(view);

        conn = view.findViewById(R.id.conn);
        signup = view.findViewById(R.id.signup);

        conn.setOnClickListener(e -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment, new connexionActivuty())
                    .commit();
        });

        signup.setOnClickListener(e -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment, new incriptiionActivyt())
                    .commit();
        });

        return view;
    }

    private void setNavigationDrawer(View view) {
        dLayout = view.findViewById(R.id.drawer_layout);
        NavigationView navView = view.findViewById(R.id.navigation);
        navView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.accueil_item) getParentFragmentManager().beginTransaction().replace(R.id.fragment, new Accuiel()).commit();
            Toast.makeText(getContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
            dLayout.closeDrawers();
            return true;
        });
    }
}
