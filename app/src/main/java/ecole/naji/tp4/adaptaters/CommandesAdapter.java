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
        double initPrix = ocm.getMontant();

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
        total.setText(initPrix +"");
        add.setOnClickListener(e -> qte.setText(handleAdd(total, (String)qte.getText(), initPrix, ocm)));
        sub.setOnClickListener(e -> qte.setText(handleSub(total, (String)qte.getText(), initPrix, ocm)));

        return convertView;
    }

    private String handleSub(TextView total, String text, double pidzInitPrix, Commande command){
        Log.i("asd", " --- ");
        double value = Double.parseDouble(text);
        int newQuan_tities = (int) (value - 1);
        total.setText(pidzInitPrix * newQuan_tities + "");
        command.setMontant(pidzInitPrix * newQuan_tities);
        db.updateCommmandeClien(command);
        yeah.run();
        return newQuan_tities + "";
    }

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
