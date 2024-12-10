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

    /**
     * Private constructor for the DatabaseManager class.
     * Initializes the ORMLite database helper with the provided context.
     *
     * @param context The context to initialize the database manager.
     */
    private DatabaseManger(Context context) {
        super(context, DATABESE_NAME, null, DATABESE_JAIME_DANSER_VERSION);
    }

    /**
     * Returns the singleton instance of the DatabaseManger.
     * If the instance is not already created, it initializes a new one.
     *
     * @param context The context to initialize the database manager.
     * @return The singleton instance of the DatabaseManger.
     */
    public static synchronized DatabaseManger getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseManger(context.getApplicationContext());
        }
        return instance;
    }

    /**
     * Creates the database tables (Pizza, Commande, and Client) when the database is created.
     *
     * @param database The SQLite database instance.
     * @param connSrc  The connection source for the database.
     */
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

    /**
     * Updates the database schema when the database version is upgraded.
     * Drops existing tables and recreates them.
     *
     * @param database   The SQLite database instance.
     * @param connSrc    The connection source for the database.
     * @param oldVersion The old version of the database.
     * @param newVersion The new version of the database.
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connSrc, int oldVersion, int newVersion) {
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

    /**
     * Inserts a new client into the database.
     *
     * @param client The `Client` object to be inserted.
     */
    public void insertClient(Client client) {
        try {
            getDao(Client.class).create(client);
            Log.i("DATABES INSERTED", client.toString());
        } catch (Exception e) {
            Log.e("DATABSES ERROR", "ERROR: po marcehre la jout des scores" + e.getMessage());
        }
    }

    /**
     * Updates an existing client in the database.
     *
     * @param client The `Client` object to be updated.
     */
    public void updateClient(Client client) {
        try {
            getDao(Client.class).update(client);
            Log.i("DATABES INSERTED", "Score inserted" + client.toString());
        } catch (Exception e) {
            Log.e("DATABSES ERROR", "ERROR: po marcehre la jout des scores" + e.getMessage());
        }
    }

    /**
     * Inserts a new order (Commande) into the database.
     *
     * @param commande The `Commande` object to be inserted.
     */
    public void insertCommande(Commande commande) {
        try {
            getDao(Commande.class).create(commande);
            Log.i("DATABES INSERTED", "Score inserted" + commande.toString());
        } catch (Exception e) {
            Log.e("DATABSES ERROR", "ERROR: po marcehre la jout des scores" + e.getMessage());
        }
    }

    /**
     * Inserts a new pizza into the database.
     *
     * @param imAPizzaBoy The `Pizza` object to be inserted.
     */
    public void insertPizze(Pizza imAPizzaBoy) {
        try {
            getDao(Pizza.class).create(imAPizzaBoy);
            Log.i("DATABES INSERTED", "Pizza inserted" + imAPizzaBoy.toString());
        } catch (Exception e) {
            Log.e("DATABSES ERROR", "ERROR:" + e.getMessage());
        }
    }

    /**
     * Updates an existing order (Commande) in the database.
     *
     * @param cliencommande The `Commande` object to be updated.
     */

    public void updateCommmandeClien(Commande cliencommande) {
        try {
            getDao(Commande.class).update(cliencommande);
            Log.i("DATABES INSERTED", "Score inserted" + cliencommande.toString());
        } catch (Exception e) {
            Log.e("DATABSES ERROR", "ERROR: po marcehre la jout des scores" + e.getMessage());
        }
    }

    /**
     * Deletes an existing order (Commande) from the database.
     *
     * @param cliencommande The `Commande` object to be deleted.
     */
    public void deelteupdateCommmandeClien(Commande cliencommande) {
        try {
            getDao(Commande.class).delete(cliencommande);
            Log.i("DATABES INSERTED", "Score inserted" + cliencommande.toString());
        } catch (Exception e) {
            Log.e("DATABSES ERROR", "ERROR: po marcehre la jout des scores" + e.getMessage());
        }
    }

    /**
     * Reads and retrieves all pizzas from the database.
     *
     * @return A list of `Pizza` objects.
     */
    public List<Pizza> readPizzasses() {
        try {
            Dao<Pizza, Integer> dao = getDao(Pizza.class);
            return dao.queryForAll();
        } catch (Exception e) {
            Log.e("DATABSES ERROR", "ERROR:" + e.getMessage());
            return null;
        }
    }

    /**
     * Reads and retrieves all orders (Commandes) from the database.
     *
     * @return A list of `Commande` objects.
     */
    public List<Commande> readCommandesOmg() {
        try {
            Dao<Commande, Integer> dao = getDao(Commande.class);
            return dao.queryForAll();
        } catch (Exception e) {
            Log.e("DATABSES ERROR", "ERROR:" + e.getMessage());
            return null;
        }
    }

    /**
     * Reads and retrieves all clients from the database.
     *
     * @return A list of `Client` objects.
     */
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