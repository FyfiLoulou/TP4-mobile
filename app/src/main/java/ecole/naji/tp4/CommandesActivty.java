package ecole.naji.tp4;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ecole.naji.tp4.adaptaters.CoolAdapater;
import ecole.naji.tp4.models.Pizza;

public class CommandesActivty extends Fragment {

    private ListView PIZZAZ;
    private CoolAdapater pizzaAdapter;
    private List<Pizza> pizzaList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.commande, container, false);




        PIZZAZ = rootView.findViewById(R.id.idkidkidkidkidk);

        pizzaList = new ArrayList<>();
        pizzaList.add(new Pizza("Pepperoni", "Spicy", 40, R.drawable.p1));
        pizzaList.add(new Pizza("Vérégatien", "Viande", 300, R.drawable.guy));
        pizzaList.add(new Pizza("Grosse", "30pouce", 5, R.drawable.p3));
        pizzaList.add(new Pizza("Dick's cheese", "6", 10, R.drawable.p4));
        pizzaList.add(new Pizza("Félix", "medium rare", 132, R.drawable.p5));

        pizzaAdapter = new CoolAdapater(pizzaList, getContext());
        PIZZAZ.setAdapter(pizzaAdapter);

        return rootView;
    }


}