package ecole.naji.tp4.adaptaters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import ecole.naji.tp4.DatabaseManger;
import ecole.naji.tp4.MainActivity;
import ecole.naji.tp4.R;
import ecole.naji.tp4.models.Commande;
import ecole.naji.tp4.models.Pizza;

public class CoolAdapater extends BaseAdapter {

    private List<Pizza> pizzaList;
    private Context context;
    private DatabaseManger data;

    public double prixe;

    /**
     * Constructor for the CoolAdapater.
     *
     * @param pizzaList The list of pizzas to be displayed in the ListView.
     * @param context   The context in which the adapter is being used (typically the Activity or Fragment).
     */
    public CoolAdapater(List<Pizza> pizzaList, Context context) {
        this.pizzaList = pizzaList;
        this.context = context;
        this.data = DatabaseManger.getInstance(context);
    }

    /**
     * Returns the number of items in the list (the number of pizzas).
     *
     * @return The number of pizzas in the list.
     */
    @Override
    public int getCount() {
        return pizzaList.size();
    }

    /**
     * Returns the pizza object at the specified position in the list.
     *
     * @param position The position of the item in the list.
     * @return The pizza at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return pizzaList.get(position);
    }

    /**
     * Returns the ID of the pizza at the specified position.
     *
     * @param position The position of the item in the list.
     * @return The ID of the pizza at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * This method inflates the pizza item layout and binds the pizza data (image, name, size) to the views.
     * It also sets up a spinner for selecting the pizza size and a button to add the pizza to the order.
     *
     * @param position    The position of the item in the list.
     * @param convertView The recycled view (if any).
     * @param parent      The parent view group.
     * @return The custom view for the pizza item.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.coolpizzamodetimelayoutk, parent, false);
        }

        // Get the pizza object at the current position
        Pizza pizza = pizzaList.get(position);

        // Bind the pizza data to the views
        ImageView pizzaImg = convertView.findViewById(R.id.imageView);
        TextView sorte = convertView.findViewById(R.id.pizzaName);
        TextView taille = convertView.findViewById(R.id.pizzaType);
        Spinner spinner = convertView.findViewById(R.id.spinnerCool);
        Button buttonAjouter = convertView.findViewById(R.id.button);

        pizzaImg.setImageResource(pizza.getImgId());
        sorte.setText(pizza.getSorte());
        taille.setText(pizza.getTaille());

        // Store the selected pizza's price for later use
        prixe = pizza.getPrix();

        ApadteurSpinner adapter = new ApadteurSpinner(context, pizza, this);
        spinner.setAdapter(adapter);

        // Set up the button click listener to add the pizza to the order
        buttonAjouter.setOnClickListener(e -> {
            // Insert the order into the database
            data.insertCommande(new Commande(MainActivity.userConnected, prixe, new Date().toString(), pizza.getId()));
            Log.w("lol", "added command: " + prixe);
        });
        return convertView;
    }
}
