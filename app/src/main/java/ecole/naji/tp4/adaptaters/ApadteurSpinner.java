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

    public ApadteurSpinner(Context context, Pizza pizz, CoolAdapater coolAdapater) {
        super(context, R.layout.spinner_prix);
        this.context = context;
        this.pizzasses = pizz;
        this.idk = coolAdapater;
    }

    @Override
    public int getCount() {
        return pizzasses.getOptions().length;
    }

    @Override
    public String[] getItem(int position) {
        return pizzasses.getOptions()[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

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
