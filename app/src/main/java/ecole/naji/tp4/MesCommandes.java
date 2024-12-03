package ecole.naji.tp4;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.List;

import java.util.Objects;

import ecole.naji.tp4.adaptaters.CommandesAdapter;
import ecole.naji.tp4.adaptaters.CoolAdapater;
import ecole.naji.tp4.models.Commande;

public class MesCommandes extends Fragment {

    private ListView listPidz;
    private TextView prix;
    private CheckBox points;
    private TextView econo;
    private TextView endPrice;
    private Button payer;

    private DatabaseManger miam;

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
        handlePriceCalc();

        miam = DatabaseManger.getInstance(getContext());
        List<Commande> commandespeutplustard = miam.readCommandesOmg();

        commandespeutplustard.forEach(c -> Log.w("lol", String.valueOf(c)));

        CommandesAdapter coolAdapater = new CommandesAdapter(getContext(), miam, commandespeutplustard);
        Log.i("asd", "coolAdapter affichage trop cool sick wutang");
        listPidz.setAdapter(coolAdapater);
        return routeviou;
    }

    private void handlePriceCalc() {

    }

    // TODO! faire en sorte que sa marche genre
    private void handlePayer() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("ADD to CARDE");
        builder.setMessage("Passer avais la caisse pour payement par Carde");
        builder.setNeutralButton("Je ne sais pas", (dialog, which) -> {
            Log.w("lol", "PAYER");
        });
        builder.setNegativeButton("NON", (dialog, which) -> {
            Log.w("lol", "PAS PAYER");
        });
        builder.setPositiveButton("moui", (dialog, which) -> {
            Log.w("lol", "PAYER");
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
