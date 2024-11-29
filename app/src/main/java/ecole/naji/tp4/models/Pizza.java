package ecole.naji.tp4.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DatabaseTable(tableName = "pizza")
public class Pizza {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "sorte")
    private String sorte;
    @DatabaseField(columnName = "taille")
    private String taille;
    @DatabaseField(columnName = "prix")
    private double prix;

    @DatabaseField(columnName = "imgId")
    private int imgId;

    public Pizza(String sorte, String taille, double prix, int imgId) {
        this.sorte = sorte;
        this.taille = taille;
        this.prix = prix;
        this.imgId = imgId;
    }

    public Pizza() {
    }

    public String[][] getOptions() {
        String[][] a = {{"pépito", prix*0.5+""},{"Moyenne", prix+""},{"grosse", prix*27.5+""}};
        return a;
    };

    public int getId() {
        return id;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getSorte() {
        return sorte;
    }

    public void setSorte(String sorte) {
        this.sorte = sorte;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "sorte='" + sorte + '\'' +
                ", taille='" + taille + '\'' +
                ", prix=" + prix +
                '}';
    }
}
