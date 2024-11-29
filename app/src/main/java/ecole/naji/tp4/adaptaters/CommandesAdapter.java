package ecole.naji.tp4.adaptaters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

    public CommandesAdapter(Context context, DatabaseManger db) {
        this.db = db;
        this.commandeList = commandeList.stream().filter(c -> c.getIdClient() == MainActivity.userConnected).collect(Collectors.toList());
        this.context = context;
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

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.cool_commandes_model_pour_les_trucs_cool, viewGroup);
        }
        Pizza pidz = db.readPizzasses().stream().filter(p->p.getId()==commandeList.get(i).getPidzId()).findFirst().get();
        ImageView pidzImg = view.findViewById(R.id.imgPidz);
        TextView price = view.findViewById(R.id.price);
        TextView total = view.findViewById(R.id.total_price);
        Button add = view.findViewById(R.id.plus);
        Button sub = view.findViewById(R.id.moins);
        TextView qte = view.findViewById(R.id.qte);
        pidzImg.setImageResource(pidz.getImgId());
        price.setText(pidz.getPrix() + "");
        qte.setText("1");
        total.setText((pidz.getPrix() * Double.parseDouble((String) qte.getText())) + "");
        add.setOnClickListener(e -> qte.setText(handleAdd((String) qte.getText())));
        sub.setOnClickListener(e -> qte.setText(handleSub((String) qte.getText())));
        return view;
    }

    private String handleSub(String text) {
        double value = Double.parseDouble(text);
        return (value - 1) + "";
    }

    private String handleAdd(String text) {
        double value = Double.parseDouble(text);
        return (value + 1) + "";
    }
}
