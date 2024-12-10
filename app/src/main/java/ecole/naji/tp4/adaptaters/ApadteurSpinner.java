package ecole.naji.tp4.adaptaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import ecole.naji.tp4.R;
import ecole.naji.tp4.models.Pizza;

public class ApadteurSpinner extends ArrayAdapter<String[]> {
    private Context context;
    private Pizza pizzasses;
    private CoolAdapater idk;

    /**
     * Constructor for the adapter. Initializes the context, pizza object, and CoolAdapater instance.
     *
     * @param context      The context in which the adapter is being used.
     * @param pizz         The Pizza object that contains the available size and price options.
     * @param coolAdapater The CoolAdapater instance to be updated with the selected price.
     */
    public ApadteurSpinner(Context context, Pizza pizz, CoolAdapater coolAdapater) {
        super(context, R.layout.spinner_prix);
        this.context = context;
        this.pizzasses = pizz;
        this.idk = coolAdapater;
    }

    /**
     * Returns the number of options available for the pizza.
     *
     * @return The count of options in the pizza.
     */
    @Override
    public int getCount() {
        return pizzasses.getOptions().length;
    }

    /**
     * Returns the item at a specific position. Each item is an array containing the size and price.
     *
     * @param position The position of the item to retrieve.
     * @return A string array representing the pizza option at the given position.
     */
    @Override
    public String[] getItem(int position) {
        return pizzasses.getOptions()[position];
    }

    /**
     * Returns the item ID for the specified position. In this case, the position itself is returned.
     *
     * @param position The position of the item.
     * @return The ID of the item (position).
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * This method inflates and returns the custom dropdown view for each pizza option.
     * It displays both the size and the price in the Spinner dropdown.
     *
     * @param position    The position of the item.
     * @param convertView The recycled view (if any).
     * @param parent      The parent view group.
     * @return The custom dropdown view for the pizza option at the given position.
     */
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    /**
     * This method inflates and returns the custom view for the selected pizza option in the Spinner.
     * It displays both the size and the price in the main Spinner view.
     *
     * @param position    The position of the item.
     * @param convertView The recycled view (if any).
     * @param parent      The parent view group.
     * @return The custom view for the selected pizza option.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    /**
     * This method creates and populates a custom view for each pizza option (both in the dropdown and main view).
     * It sets the pizza size and price into the corresponding TextViews.
     *
     * @param position    The position of the item.
     * @param convertView The recycled view (if any).
     * @param parent      The parent view group.
     * @return The populated custom view for the pizza option.
     */
    private View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.spinner_prix, parent, false);

        TextView size = convertView.findViewById(R.id.spinner_taolle);
        TextView price = convertView.findViewById(R.id.spinner_prix);

        String[] option = pizzasses.getOptions()[position];
        size.setText(option[0]);
        price.setText(option[1]);

        idk.prixe = Double.parseDouble(option[1]);

        return convertView;
    }
}
