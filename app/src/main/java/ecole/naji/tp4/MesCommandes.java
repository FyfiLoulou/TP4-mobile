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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View routeviou = inflater.inflate(R.layout.mes_commandes_frag, container, false);
        listPidz = routeviou.findViewById(R.id.list_pidz);
        prix = routeviou.findViewById(R.id.prix);
        points = routeviou.findViewById(R.id.checkBox);
        econo = routeviou.findViewById(R.id.econo);
        endPrice = routeviou.findViewById(R.id.end_price);
        payer = routeviou.findViewById(R.id.payer);
        payer.setOnClickListener(e -> handlePayer());
        this.gestureDetector = new GestureDetectorCompat(getContext(),this);

        miam = DatabaseManger.getInstance(getContext());
        List<Commande> commandespeutplustard = miam.readCommandesOmg();

        commandespeutplustard.forEach(c -> Log.w("lol", String.valueOf(c)));
        handlePriceCalc();

        points.setOnClickListener(e->{
            Client connectedClient = miam.readClientelle().stream().filter(u -> u.getId() == MainActivity.userConnected).findFirst().get();
            double valeurPts = connectedClient.getPoint() * 0.0075;
            if (points.isChecked()) {
                endPrice.setText((Double.parseDouble((String) prix.getText())) - valeurPts + "");
                econo.setText(valeurPts+"");
            }
            else {
                endPrice.setText(prix.getText() + "");
                econo.setText("RIEN!!");
            }
        });

        CommandesAdapter coolAdapater = new CommandesAdapter(getContext(), miam, commandespeutplustard, ()->{handlePriceCalc();});
        Log.i("asd", "coolAdapter affichage trop cool sick wu-chang");
        listPidz.setAdapter(coolAdapater);
        return routeviou;
    }

    private void handlePriceCalc() {
        double priteotal =  miam.readCommandesOmg().stream().filter(c->c.getIdClient()==MainActivity.userConnected).map(commande -> commande.getMontant()).reduce((double) 0, (a, b)->a+b);
        prix.setText(priteotal+"");
        Client connectedClient = miam.readClientelle().stream().filter(u -> u.getId() == MainActivity.userConnected).findFirst().get();
        double valeurPts = connectedClient.getPoint() * 0.0075;

        if (points.isChecked()) endPrice.setText(priteotal - valeurPts+"");
        else endPrice.setText(priteotal +"");
    }

    private void handlePayer() {
        double payeMoi = Double.parseDouble((String) endPrice.getText());
        Client connectedClient = miam.readClientelle().stream().filter(u -> u.getId() == MainActivity.userConnected).findFirst().get();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("ADD to CARDE");
        builder.setMessage("Passer avais la caisse pour payement par Carde -> coutage: "+payeMoi+"$$$$");
        builder.setNeutralButton("Je ne sais pas", (dialog, which) -> {
            Log.w("lol", "PAYER");
            connectedClient.calculerPts(payeMoi);
            miam.updateClient(connectedClient);
            miam.readCommandesOmg().stream().filter(c->c.getIdClient()==MainActivity.userConnected).forEach(om->miam.deelteupdateCommmandeClien(om));
            connectedClient.setPoint(0);// (LOL YOU LMAO)
            getParentFragmentManager().beginTransaction().replace(R.id.fragment, new pointsFrag()).commit();
        });
        builder.setNegativeButton("NON", (dialog, which) -> {
            Log.w("lol", "PAS PAYER");
        });
        builder.setPositiveButton("moui", (dialog, which) -> {
            connectedClient.calculerPts(payeMoi);
            miam.updateClient(connectedClient);
            miam.readCommandesOmg().stream().filter(c->c.getIdClient()==MainActivity.userConnected).forEach(om->miam.deelteupdateCommmandeClien(om));
            connectedClient.setPoint(0);// (LOL YOU LMAO)
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
