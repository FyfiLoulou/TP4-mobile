package ecole.naji.tp4.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "commande")
public class Commande {
    @DatabaseField(columnName = "id", generatedId = true)
    private int id;
    @DatabaseField(columnName = "no_commande")
    private int noCommande;
    @DatabaseField(columnName = "nom_client")
    private String nomClient;
    @DatabaseField(columnName = "montant")
    private double montant;
    @DatabaseField(columnName = "date")
    private String date;

    public Commande(int noCommande, String nomClient, double montant, String date) {
        this.noCommande = noCommande;
        this.nomClient = nomClient;
        this.montant = montant;
        this.date = date;
    }

    public Commande() {
    }

    public int getNoCommande() {
        return noCommande;
    }

    public void setNoCommande(int noCommande) {
        this.noCommande = noCommande;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
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
                "noCommande=" + noCommande +
                ", nomClient='" + nomClient + '\'' +
                ", montant=" + montant +
                ", date='" + date + '\'' +
                '}';
    }
}
