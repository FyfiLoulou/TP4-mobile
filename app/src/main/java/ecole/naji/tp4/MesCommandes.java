package ecole.naji.tp4;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;

import java.util.List;

import java.util.Objects;

import ecole.naji.tp4.adaptaters.CommandesAdapter;
import ecole.naji.tp4.adaptaters.CoolAdapater;
import ecole.naji.tp4.models.Client;
import ecole.naji.tp4.models.Commande;

public class MesCommandes extends Fragment implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener {

    private ListView listPidz;
    private TextView prix;
    private CheckBox points;
    private TextView econo;
    private TextView endPrice;
    private Button payer;

    private DatabaseManger miam;
    private GestureDetectorCompat gestureDetector;

    /**
     * Called to create the view for the fragment. This method initializes the UI components,
     * retrieves the list of orders for the user, and calculates the total price.
     * It also handles the application of loyalty points and displays the final price after applying any discounts.
     *
     * @param inflater           The LayoutInflater object to inflate the fragment's view.
     * @param container          The container where the view will be placed.
     * @param savedInstanceState A Bundle containing any saved state data (if any).
     * @return The root view of the fragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View routeviou = inflater.inflate(R.layout.mes_commandes_frag, container, false);
        listPidz = routeviou.findViewById(R.id.list_pidz);
        prix = routeviou.findViewById(R.id.prix);
        points = routeviou.findViewById(R.id.checkBox);
        econo = routeviou.findViewById(R.id.econo);
        endPrice = routeviou.findViewById(R.id.end_price);

        // Set click listener for the payer button
        payer = routeviou.findViewById(R.id.payer);
        payer.setOnClickListener(e -> handlePayer());
        this.gestureDetector = new GestureDetectorCompat(getContext(),this);

        // Initialize database manager and retrieve the user's orders
        miam = DatabaseManger.getInstance(getContext());
        List<Commande> commandespeutplustard = miam.readCommandesOmg();

        // Calculate the total price
        handlePriceCalc();

        // Handle loyalty points checkbox toggle
        points.setOnClickListener(e -> {
            Client connectedClient = miam.readClientelle().stream().filter(u -> u.getId() == MainActivity.userConnected).findFirst().get();
            double valeurPts = connectedClient.getPoint() * 0.0075;
            if (points.isChecked()) {
                endPrice.setText((Double.parseDouble((String) prix.getText())) - valeurPts + "");
                econo.setText(valeurPts + "");
            } else {
                endPrice.setText(prix.getText() + "");
                econo.setText("RIEN!!");
            }
        });

        // Set up the adapter to display orders in the ListView
        CommandesAdapter coolAdapater = new CommandesAdapter(getContext(), miam, commandespeutplustard, () -> {
            handlePriceCalc();
        });
        listPidz.setAdapter(coolAdapater);
        return routeviou;
    }

    /**
     * Calculates the total price of all orders placed by the currently logged-in user.
     * It updates the UI with the calculated total price and applies any discounts based on loyalty points.
     */
    private void handlePriceCalc() {
        double priteotal = miam.readCommandesOmg().stream().filter(c -> c.getIdClient() == MainActivity.userConnected).map(commande -> commande.getMontant()).reduce((double) 0, (a, b) -> a + b);
        prix.setText(priteotal + "");
        Client connectedClient = miam.readClientelle().stream().filter(u -> u.getId() == MainActivity.userConnected).findFirst().get();
        double valeurPts = connectedClient.getPoint() * 0.0075;
        if (points.isChecked()) endPrice.setText(priteotal - valeurPts + "");
        else endPrice.setText(priteotal + "");
    }

    /**
     * Handles the payment process when the user clicks the "payer" (pay) button.
     * It shows an alert dialog for confirmation, updates the user's points, and clears their orders.
     */
    private void handlePayer() {
        double payeMoi = Double.parseDouble((String) endPrice.getText());
        Client connectedClient = miam.readClientelle().stream().filter(u -> u.getId() == MainActivity.userConnected).findFirst().get();

        // Show an alert dialog for payment confirmation
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("ADD to CARDE");
        builder.setMessage("Passer avais la caisse pour payement par Carde -> coutage: " + payeMoi + "$$$$");

        // Handle the dialog buttons
        builder.setNeutralButton("Je ne sais pas", (dialog, which) -> {
            Log.w("lol", "PAYER");
            connectedClient.calculerPts(payeMoi);
            miam.updateClient(connectedClient);
            miam.readCommandesOmg().stream().filter(c -> c.getIdClient() == MainActivity.userConnected).forEach(om -> miam.deelteupdateCommmandeClien(om));
            connectedClient.setPoint(0);// Reset points after payment
            getParentFragmentManager().beginTransaction().replace(R.id.fragment, new pointsFrag()).commit();
        });
        builder.setNegativeButton("NON", (dialog, which) -> {
            Log.w("lol", "PAS PAYER");
        });
        builder.setPositiveButton("moui", (dialog, which) -> {
            if(points.isChecked()){
                connectedClient.calculerPts(0);
                connectedClient.setPoint(0);
            }
            else {
                connectedClient.calculerPts(payeMoi);
            }

            miam.updateClient(connectedClient);
            miam.readCommandesOmg().stream().filter(c->c.getIdClient()==MainActivity.userConnected).forEach(om->miam.deelteupdateCommmandeClien(om));
            getParentFragmentManager().beginTransaction().replace(R.id.fragment, new pointsFrag()).commit();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    public boolean onDown(@NonNull MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(@Nullable MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(@Nullable MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Bonjour Naji!");
        builder.setMessage("Allo!");
        builder.setNeutralButton("Je ne sais pas", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.setPositiveButton("Bye", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.setNegativeButton("Ou suis-je?", (dialog, which) -> {
            dialog.dismiss();
        });
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(@NonNull MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(@NonNull MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(@NonNull MotionEvent motionEvent) {
        return false;
    }
}
