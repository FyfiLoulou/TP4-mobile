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
    TextView price;
    TextView total;
    TextView qte;
    private Runnable yeah;

    public CommandesAdapter(Context context, DatabaseManger db, List<Commande> comms, Runnable yeah) {
        this.db = db;
        this.commandeList = comms.stream().filter(c -> c.getIdClient() == MainActivity.userConnected).collect(Collectors.toList());
        this.context = context;
        this.yeah = yeah;
    }

    @Override
    public int getCount() {
        return commandeList.size();
    }

    @Override
    public Object getItem(int i) {
        return commandeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return commandeList.get(i).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.cool_commandes_model_pour_les_trucs_cool, parent, false);
        }

        Commande ocm    = commandeList.get(position);

        Pizza pidz = db.readPizzasses().stream()
                .filter(p -> {
                    boolean match = p.getId() == ocm.getPidzId();
                    Log.i("PizzaMatch", String.valueOf(p.getId()));
                    Log.i("PizzaMatch", String.valueOf(commandeList.get(position)));
                    Log.i("PizzaMatch", String.valueOf(commandeList.get(position).getPidzId()));
                    if (match) {
                        Log.d("PizzaMatch", "Found pizza with ID: " + p.getId());
                    } else {
                        Log.d("PizzaMatch", "No match for pizza with ID: " + p.getId());
                    }
                    return match;
                })
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Pizza not found for the given ID"));

        ImageView pidzImg = convertView.findViewById(R.id.imgPidz);
        total = convertView.findViewById(R.id.total_price);
        Button add = convertView.findViewById(R.id.plus);
        Button sub = convertView.findViewById(R.id.moins);
        qte = convertView.findViewById(R.id.qte);
        price = convertView.findViewById(R.id.price);
        pidzImg.setImageResource(pidz.getImgId());
        qte.setText("1");
        price.setText(pidz.getPrix() + "");
        total.setText((pidz.getPrix() * Double.parseDouble((String) qte.getText())) + "");
        add.setOnClickListener(e -> qte.setText(handleAdd((String)qte.getText(), pidz, ocm)));
        sub.setOnClickListener(e -> qte.setText(handleSub((String)qte.getText(), pidz, ocm)));

        return convertView;
    }

    private String handleSub(String text, Pizza pidz, Commande command){
        Log.i("asd", " --- ");
        double value = Double.parseDouble(text);
        total.setText(pidz.getPrix() * (value - 1) + "");
        command.setMontant(pidz.getPrix() * (value - 1));
        db.updateCommmandeClien(command);
        yeah.run();
        return (value - 1) + "";
    }

    private String handleAdd(String text, Pizza pidz, Commande command) {
        Log.i("asd", " +++ ");
        double value = Double.parseDouble(text);
        total.setText(pidz.getPrix() * (value + 1) + "");
        command.setMontant(pidz.getPrix() * (value + 1));
        db.updateCommmandeClien(command);
        yeah.run();
        return (value + 1) + "";
    }
}
