package ecole.naji.tp4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Objects;

public class MesCommandes extends Fragment {

    private ListView listPidz;
    private TextView prix;
    private CheckBox points;
    private TextView econo;
    private TextView endPrice;
    private Button payer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        listPidz = requireActivity().findViewById(R.id.list_pidz);
        prix = requireActivity().findViewById(R.id.prix);
        points = requireActivity().findViewById(R.id.checkBox);
        econo = requireActivity().findViewById(R.id.econo);
        endPrice = requireActivity().findViewById(R.id.end_price);
        payer = requireActivity().findViewById(R.id.payer);

        payer.setOnClickListener(e -> handlePayer());
        handlePriceCalc();
        return inflater.inflate(R.layout.mes_commandes_frag, container, false);
    }

    private void handlePriceCalc() {

    }

    // TODO! faire en sorte que sa marche genre
    private void handlePayer() {
    }
}
