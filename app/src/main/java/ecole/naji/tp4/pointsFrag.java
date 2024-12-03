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

    //l'argent qu'on a dépensé adate (le 10 c'est un exemple c'est psa permanent)
    public double money = 10;
    private TextView points;
    private double valeurPts;
    Client client = new Client();

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.points_frag, container, false);

        points = view.findViewById(R.id.points);

        calculerPts();
        calculerValeur();

        points.setText(client.getPoint() + "");

        return view;
    }

    public void calculerPts(){
        client.setPoint((int) money * 10);
    }

    public void calculerValeur(){
        valeurPts = client.getPoint() * 0.0075 ;
        System.out.println(client.getPoint() + " points valent " + valeurPts + "$");
    }
}
