package ecole.naji.tp4.adaptaters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

import ecole.naji.tp4.DatabaseManger;
import ecole.naji.tp4.MainActivity;
import ecole.naji.tp4.R;
import ecole.naji.tp4.models.Commande;
import ecole.naji.tp4.models.Pizza;

public class CommandesAdapter extends BaseAdapter {
    private List<Commande> commandeList;
    private Context context;
    private DatabaseManger db;
    private Runnable yeah;

    /**
     * Constructor for the CommandesAdapter.
     *
     * @param context The context where the adapter is used (typically the Activity or Fragment).
     * @param db      The database manager for interacting with the app's database.
     * @param comms   The list of orders (`Commande`).
     * @param yeah    A Runnable callback to run when an update occurs (like refreshing the UI).
     */
    public CommandesAdapter(Context context, DatabaseManger db, List<Commande> comms, Runnable yeah) {
        this.db = db;
        this.commandeList = comms.stream().filter(c -> c.getIdClient() == MainActivity.userConnected).collect(Collectors.toList());
        this.context = context;
        this.yeah = yeah;
    }

    /**
     * Returns the number of orders in the list.
     *
     * @return The number of orders in the list.
     */
    @Override
    public int getCount() {
        return commandeList.size();
    }

    /**
     * Returns the order at the specified position.
     *
     * @param i The position of the item.
     * @return The order at the given position.
     */
    @Override
    public Object getItem(int i) {
        return commandeList.get(i);
    }

    /**
     * Returns the ID of the order at the specified position.
     *
     * @param i The position of the item.
     * @return The ID of the order.
     */
    @Override
    public long getItemId(int i) {
        return commandeList.get(i).getId();
    }

    /**
     * This method inflates the order item layout and sets the pizza details in the ListView.
     * It also handles increasing and decreasing the pizza quantity and updating the total price.
     *
     * @param position    The position of the item to display in the list.
     * @param convertView The recycled view (if any).
     * @param parent      The parent view group.
     * @return The custom view for the order item.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.cool_commandes_model_pour_les_trucs_cool, parent, false);
        }

        // Get the current order (Commande) at the specified position
        Commande ocm = commandeList.get(position);
        double initPrix = ocm.getMontant();

        // Find the pizza associated with this order
        Pizza pidz = db.readPizzasses().stream().filter(p -> p.getId() == ocm.getPidzId()).findFirst().orElseThrow(() -> new IllegalArgumentException("Pizza not found for the given ID"));

        ImageView pidzImg = convertView.findViewById(R.id.imgPidz);
        TextView total = convertView.findViewById(R.id.total_price);
        Button add = convertView.findViewById(R.id.plus);
        Button sub = convertView.findViewById(R.id.moins);
        TextView qte = convertView.findViewById(R.id.qte);
        TextView price = convertView.findViewById(R.id.price);
        pidzImg.setImageResource(pidz.getImgId());
        qte.setText("1");
        price.setText(initPrix + "");
        total.setText(initPrix + "");

        // Set the click listeners for increasing and decreasing the quantity
        add.setOnClickListener(e -> qte.setText(handleAdd(total, (String) qte.getText(), initPrix, ocm)));
        sub.setOnClickListener(e -> qte.setText(handleSub(total, (String) qte.getText(), initPrix, ocm)));

        return convertView;
    }

    /**
     * Handles decreasing the pizza quantity and updates the total price.
     * If the quantity is 1, it doesn't allow further decrease.
     *
     * @param total        The TextView displaying the total price.
     * @param text         The current quantity of the pizza.
     * @param pidzInitPrix The initial price of the pizza.
     * @param command      The current order (`Commande`).
     * @return The updated quantity as a string.
     */
    private String handleSub(TextView total, String text, double pidzInitPrix, Commande command) {
        Log.i("asd", " --- ");
        double value = Double.parseDouble(text);
        int newQuan_tities = (int) (value - 1);
        total.setText(pidzInitPrix * newQuan_tities + "");
        command.setMontant(pidzInitPrix * newQuan_tities);
        db.updateCommmandeClien(command);
        yeah.run();
        return newQuan_tities + "";
    }

    /**
     * Handles increasing the pizza quantity and updates the total price.
     *
     * @param total        The TextView displaying the total price.
     * @param text         The current quantity of the pizza.
     * @param pidzInitPrix The initial price of the pizza.
     * @param command      The current order (`Commande`).
     * @return The updated quantity as a string.
     */
    private String handleAdd(TextView total, String text, double pidzInitPrix, Commande command) {
        Log.i("asd", " +++ ");
        double value = Double.parseDouble(text);
        int newQuan_tities = (int) (value + 1);
        total.setText(pidzInitPrix * newQuan_tities + "");
        command.setMontant(pidzInitPrix * newQuan_tities);
        db.updateCommmandeClien(command);
        yeah.run();
        return newQuan_tities + "";
    }
}
