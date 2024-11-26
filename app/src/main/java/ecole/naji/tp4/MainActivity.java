package ecole.naji.tp4;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout dLayout;
    Button conn;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, new Accuiel())
                .commit();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(v -> dLayout.openDrawer(Gravity.LEFT));
        setNavigationDrawer();

        conn = findViewById(R.id.conn);
        signup = findViewById(R.id.signup);

        conn.setOnClickListener(e -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new connexionActivuty()).commit();
        });
    }

    private void setNavigationDrawer() {
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.navigation);
        navView.setNavigationItemSelectedListener(item -> {
            navView.setNavigationItemSelectedListener(menuItem -> {
                Intent intent = null;
                int itemId = menuItem.getItemId();
                if (itemId == R.id.accueil_item) {
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                Toast.makeText(getApplicationContext(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                if (intent != null) {
                    dLayout.closeDrawers();
                    return true;
                }
                return false;
            });
            return false;
        });
    }
}