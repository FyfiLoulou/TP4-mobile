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

    public CoolAdapater(List<Pizza> pizzaList, Context context) {
        this.pizzaList = pizzaList;
        this.context = context;
        this.data = DatabaseManger.getInstance(context);
    }

    @Override
    public int getCount() {
        return pizzaList.size();
    }

    @Override
    public Object getItem(int position) {
        return pizzaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.coolpizzamodetimelayoutk, parent, false);
        }

        Pizza pizza = pizzaList.get(position);

        ImageView pizzaImg = convertView.findViewById(R.id.imageView);
        TextView sorte = convertView.findViewById(R.id.pizzaName);
        TextView taille = convertView.findViewById(R.id.pizzaType);
        Spinner spinner = convertView.findViewById(R.id.spinnerCool);
        Button buttonAjouter = convertView.findViewById(R.id.button);

        pizzaImg.setImageResource(pizza.getImgId());
        sorte.setText(pizza.getSorte());
        taille.setText(pizza.getTaille());

        ApadteurSpinner adapter = new ApadteurSpinner(context, pizza);
        spinner.setAdapter(adapter);

        buttonAjouter.setOnClickListener(e -> {
            data.insertCommande(new Commande(MainActivity.userConnected, pizza.getPrix(), new Date().toString(), pizza.getId()));
            Log.w("lol", "added command");
        });

        return convertView;
    }
}
