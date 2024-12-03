package ecole.naji.tp4;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class pointsFrag extends Fragment {

    public double money = 10;
    private int pts = 0;
    private TextView points;

    private double valeurPts;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.points_frag, container, false);

        points = view.findViewById(R.id.points);

        calculerPts();
        calculerValeur();

        points.setText(pts + "");

        return view;
    }


    public void calculerPts(){
        pts = (int) money * 10;
    }

    public void calculerValeur(){
        valeurPts = pts * 0.0075 ;
        System.out.println(pts + " points valent " + valeurPts + "$");
    }
}
