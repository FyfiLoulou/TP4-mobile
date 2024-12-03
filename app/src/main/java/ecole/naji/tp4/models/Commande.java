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

    public Commande(int idClient, double montant, String date, int pidz) {
        this.idClient = idClient;
        this.montant = montant;
        this.date = date;
        this.pidzId = pidz;
    }

    public Commande() {
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getPidzId() {
        return pidzId;
    }

    public void setPidzId(int pidzId) {
        this.pidzId = pidzId;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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
