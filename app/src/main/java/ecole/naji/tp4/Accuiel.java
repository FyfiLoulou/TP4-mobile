package ecole.naji.tp4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Accuiel extends Fragment implements Animation.AnimationListener {

    Button conn;
    Button signup;
    DrawerLayout dLayout;

    ImageView img1;
    ImageView imgPoulet;
    ImageView imgSliced;
    Animation animZoomIn;
    Animation togetherAnim;
    Animation zoomOut;
    private boolean isBig = false;

    /**
     * Toggles the zoomed-in state of an image.
     * It switches between zoomed-in and zoomed-out states.
     */
    private void toggleBigBoi() {
        if (isBig) {
            isBig = false;
        } else {
            isBig = true;
        }
    }

    /**
     * Called to inflate the fragment's layout and set up UI interactions. Initializes
     * the buttons, image views, animations, and sets on-click listeners for each of them.
     *
     * @param inflater           Inflates the layout for the fragment.
     * @param container          The parent view that the fragment's UI will be attached to.
     * @param savedInstanceState Bundle containing any saved state of the fragment.
     * @return The view for the fragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.accueil2, container, false);
        //Toolbar toolbar = view.findViewById(R.id.toolbar);
        //toolbar.setNavigationOnClickListener(v -> dLayout.openDrawer(Gravity.LEFT));
        //setNavigationDrawer(view);

        conn = view.findViewById(R.id.conn);
        signup = view.findViewById(R.id.signup);

        img1 = view.findViewById(R.id.papaJohns);
        imgPoulet = view.findViewById(R.id.pouletmmm);
        imgSliced = view.findViewById(R.id.sliced);

        animZoomIn = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
        togetherAnim = AnimationUtils.loadAnimation(getContext(), R.anim.togethger);
        zoomOut = AnimationUtils.loadAnimation(getContext(), R.anim.zoomout);


        // Set on-click listener for img1 to apply a combined animation
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                img1.startAnimation(togetherAnim);
            }
        });

        // Set on-click listener for imgPoulet to toggle zoom-in/zoom-out
        imgPoulet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBig) {
                    imgPoulet.startAnimation(zoomOut);
                } else {
                    imgPoulet.startAnimation(animZoomIn);
                }
                toggleBigBoi();
            }
        });

        // Set on-click listener for imgSliced to toggle zoom-in/zoom-out
        imgSliced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isBig) {
                    imgSliced.startAnimation(zoomOut);
                } else {
                    imgSliced.startAnimation(animZoomIn);
                }
                toggleBigBoi();
            }
        });

        // Set on-click listener for the connection button to navigate to the login fragment
        conn.setOnClickListener(e -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment, new connexionActivuty())
                    .commit();
        });

        // Set on-click listener for the signup button to navigate to the registration fragment
        signup.setOnClickListener(e -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment, new incriptiionActivyt())
                    .commit();
        });


        return view;
    }

    /**
     * Sets up the navigation drawer for the fragment. This method is not currently used in
     * the fragment as the drawer layout and navigation view are commented out. It can be
     * used if needed for a more complex navigation system.
     *
     * @param view The root view of the fragment, used to initialize navigation components.
     */
    private void setNavigationDrawer(View view) {
        dLayout = view.findViewById(R.id.drawer_layout);
        NavigationView navView = view.findViewById(R.id.navigation);
        navView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.accueil_item)
                getParentFragmentManager().beginTransaction().replace(R.id.fragment, new Accuiel()).commit();
            Toast.makeText(getContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
            dLayout.closeDrawers();
            return true;
        });
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
