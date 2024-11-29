package ecole.naji.tp4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;


import java.util.List;


import android.util.Log;


import ecole.naji.tp4.adaptaters.CoolAdapater;
import ecole.naji.tp4.models.Pizza;

public class ListPizza extends Fragment {

    private ListView PIZZAZ;
    private CoolAdapater pizzaAdapter;
    private List<Pizza> pizzaList;

    private DatabaseManger data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_pizza, container, false);

        data = DatabaseManger.getInstance(getContext());


        PIZZAZ = rootView.findViewById(R.id.idkidkidkidkidk);

        Log.i("runtime", String.valueOf(data));


        pizzaList = data.readPizzasses();
        // SI PAS PIZZA AJOUTER PIZZA DÉFAUT
        if (pizzaList.size() < 3) {
            data.insertPizze(new Pizza("Pepperoni", "Spicy", 40, R.drawable.p1));
            data.insertPizze(new Pizza("Vérégatien", "Viande", 300, R.drawable.guy));
            data.insertPizze(new Pizza("Grosse", "30pouce", 5, R.drawable.p3));
            data.insertPizze(new Pizza("Dick's cheese", "6", 10, R.drawable.p4));
            data.insertPizze(new Pizza("Félix", "medium", 132, R.drawable.p5));
        }

        pizzaAdapter = new CoolAdapater(pizzaList, getContext());
        PIZZAZ.setAdapter(pizzaAdapter);

        return rootView;
    }


}