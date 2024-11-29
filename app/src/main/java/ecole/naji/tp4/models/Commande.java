package ecole.naji.tp4.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "commande")
public class Commande {
    @DatabaseField(columnName = "id", generatedId = true)
    private int id;
    @DatabaseField(columnName = "nom_client")
    private int idClient;
    @DatabaseField(columnName = "montant")
    private double montant;
    @DatabaseField(columnName = "date")
    private String date;

    public Commande(int idClient, double montant, String date) {
        this.idClient = idClient;
        this.montant = montant;
        this.date = date;
    }

    public Commande() {
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
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
                "noCommande=" + id +
                ", nomClient='" + idClient + '\'' +
                ", montant=" + montant +
                ", date='" + date + '\'' +
                '}';
    }
}
