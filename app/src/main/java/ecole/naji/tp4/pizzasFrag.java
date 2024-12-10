package ecole.naji.tp4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class pizzasFrag extends Fragment {

    /**
     * Called to create the view for the fragment. This method inflates the layout
     * `coolpizzamodetimelayoutk` and returns the view.
     *
     * @param inflater           The LayoutInflater object to inflate the fragment's view.
     * @param container          The container where the view will be placed.
     * @param savedInstanceState A Bundle containing any saved state data (if any).
     * @return The root view of the fragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.coolpizzamodetimelayoutk, container, false);

        return view;
    }
}
