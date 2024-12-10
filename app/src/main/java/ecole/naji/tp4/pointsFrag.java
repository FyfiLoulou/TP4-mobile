package ecole.naji.tp4;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import ecole.naji.tp4.models.Client;

public class pointsFrag extends Fragment {
    private double valeurPts;

    TextView points;

    DatabaseManger batadase;

    /**
     * Called to create the view for the fragment. This method retrieves the currently connected client,
     * fetches their point balance from the database, and sets the value to the points TextView.
     *
     * @param inflater           The LayoutInflater object to inflate the fragment's view.
     * @param container          The container where the view will be placed.
     * @param savedInstanceState A Bundle containing any saved state data (if any).
     * @return The root view of the fragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.points_frag, container, false);
        batadase = DatabaseManger.getInstance(getContext());
        Client connectedClient = batadase.readClientelle().stream().filter(u -> u.getId() == MainActivity.userConnected).findFirst().get();
        points = view.findViewById(R.id.points);
        points.setText(connectedClient.getPoint() + "");
        return view;
    }

}
