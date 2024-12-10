package ecole.naji.tp4.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "commande")
public class Commande {
    @DatabaseField(columnName = "id", generatedId = true)
    private int id;
    @DatabaseField(columnName = "nom_client")
    private int idClient;
    @DatabaseField(columnName = "pidz_id")
    private int pidzId;
    @DatabaseField(columnName = "montant")
    private double montant;
    @DatabaseField(columnName = "date")
    private String date;

    /**
     * constructeur sans l'id
     * @param idClient
     * @param montant
     * @param date
     * @param pidz
     */
    public Commande(int idClient, double montant, String date, int pidz) {
        this.idClient = idClient;
        this.montant = montant;
        this.date = date;
        this.pidzId = pidz;
    }

    /**
     * constructeur sans param
     */
    public Commande() {
    }

    /**
     * getter de l'id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * setter de l'id
     * @param id
     */
    private void setId(int id) {
        this.id = id;
    }

    /**
     * getter de l'id du clinet
     * @return idClient
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * setter de l'id du client
     * @param idClient
     */
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     * getter de l'id de la pizza
     * @return pidzId
     */
    public int getPidzId() {
        return pidzId;
    }

    /**
     * setter de l'id de la pizza
     * @param pidzId
     */
    public void setPidzId(int pidzId) {
        this.pidzId = pidzId;
    }

    /**
     * getter du montant
     * @return montant
     */
    public double getMontant() {
        return montant;
    }

    /**
     * setter du montant
     * @param montant
     */
    public void setMontant(double montant) {
        this.montant = montant;
    }

    /**
     * getter de la date
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * setter de la date
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * affiche les données de la commande
     * @return commande.toString
     */
    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", idClient=" + idClient +
                ", pidzId=" + pidzId +
                ", montant=" + montant +
                ", date='" + date + '\'' +
                '}';
    }
}
