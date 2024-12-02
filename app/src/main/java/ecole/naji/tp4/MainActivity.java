package ecole.naji.tp4;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout dLayout;
    public static int userConnected = -1; // -1 when no user connected else id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new Accuiel()).commit();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> dLayout.openDrawer(Gravity.LEFT));
        setNavigationDrawer();
    }

    public void setNavigationDrawer() {
        dLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.navigation);
        Log.w("lol2", userConnected + "");
        if (userConnected > 0) navView.inflateMenu(R.menu.connected_nav_item);

        navView.setNavigationItemSelectedListener(item -> {
            Fragment fragment = getFragment(item);
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
            if (fragment != null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment, fragment);
                ft.commit();
                dLayout.closeDrawers();
                return true;
            }
            return false;
        });
    }

    public static Fragment getFragment(MenuItem item) {
        Fragment fragment = null;
        int itemId = item.getItemId();
        if (itemId == R.id.accueil_item) {
            fragment = new Accuiel();
        } else if (itemId == R.id.profil_item) {
            fragment = new UpdateProfileTime();
        } else if (itemId == R.id.commandes_item) {
            fragment = new ListPizza();
        } else if (itemId == R.id.points_item) {
            fragment = new pointsFrag();
        }
        return fragment;
    }
}