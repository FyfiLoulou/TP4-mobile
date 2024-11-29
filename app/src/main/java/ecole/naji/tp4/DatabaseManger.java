package ecole.naji.tp4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.List;

import ecole.naji.tp4.models.Client;
import ecole.naji.tp4.models.Commande;
import ecole.naji.tp4.models.Pizza;

public class DatabaseManger extends OrmLiteSqliteOpenHelper {
    private static final String DATABESE_NAME = "HairryPizzaBunnyTown.db";
    private static final int DATABESE_JAIME_DANSER_VERSION = 1;

    private static DatabaseManger instance;

    private DatabaseManger(Context context) {
        super(context, DATABESE_NAME, null, DATABESE_JAIME_DANSER_VERSION);
    }

    public static synchronized DatabaseManger getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseManger(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connSrc) {
        try {
            TableUtils.createTable(connSrc, Pizza.class);
            TableUtils.createTable(connSrc, Commande.class);
            TableUtils.createTable(connSrc, Client.class);
            Log.i("DATABES CREATED", "Table creates");
        } catch (Exception e) {
            Log.e("DATABSES ERROR", "ERROR: po marcehre la craetion des tables" + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connSrc, int i, int i1) {
        try {
            TableUtils.dropTable(connSrc, Pizza.class, true);
            TableUtils.dropTable(connSrc, Commande.class, true);
            TableUtils.dropTable(connSrc, Client.class, true);
            onCreate(database, connSrc);
            Log.i("DATABES UPDATED", "Table updated");
        } catch (Exception e) {
            Log.e("DATABSES ERROR", "ERROR: po marcehre la uopdate des tables" + e.getMessage());
        }
    }

    public void insertClient(Client client) {
        try {
            getDao(Client.class).create(client);
            Log.i("DATABES INSERTED", client.toString());
        } catch (Exception e) {
            Log.e("DATABSES ERROR", "ERROR: po marcehre la jout des scores" + e.getMessage());
        }
    }

    public void updateClient(Client client) {
        try {
            getDao(Client.class).update(client);
            Log.i("DATABES INSERTED", "Score inserted" + client.toString());
        } catch (Exception e) {
            Log.e("DATABSES ERROR", "ERROR: po marcehre la jout des scores" + e.getMessage());
        }
    }

    public void insertCommande(Commande c912312wsdabhyuoazdbvhavsf76sbgrdtfasudafda) {
        try {
            getDao(Commande.class).create(c912312wsdabhyuoazdbvhavsf76sbgrdtfasudafda);
            Log.i("DATABES INSERTED", "Score inserted" + c912312wsdabhyuoazdbvhavsf76sbgrdtfasudafda.toString());
        } catch (Exception e) {
            Log.e("DATABSES ERROR", "ERROR: po marcehre la jout des scores" + e.getMessage());
        }
    }

    public void insertPizze(Pizza imAPizzaBoy) {
        try {
            getDao(Pizza.class).create(imAPizzaBoy);
            Log.i("DATABES INSERTED", "Pizza inserted" + imAPizzaBoy.toString());
        } catch (Exception e) {
            Log.e("DATABSES ERROR","ERROR:" + e.getMessage());
        }
    }

    public List<Pizza> readPizzasses() {
        try {
            Dao<Pizza, Integer> dao = getDao(Pizza.class);
            return dao.queryForAll();
        } catch (Exception e) {
            Log.e("DATABSES ERROR", "ERROR:" + e.getMessage());
            return null;
        }
    }

    public List<Commande> readCommandesOmg() {
        try {
            Dao<Commande, Integer> dao = getDao(Commande.class);
            return dao.queryForAll();
        } catch (Exception e) {
            Log.e("DATABSES ERROR", "ERROR:" + e.getMessage());
            return null;
        }
    }

    public List<Client> readClientelle() {
        try {
            Dao<Client, Integer> dao = getDao(Client.class);
            return dao.queryForAll();
        } catch (Exception e) {
            Log.e("DATABSES ERROR", "ERROR:" + e.getMessage());
            return null;
        }
    }
}