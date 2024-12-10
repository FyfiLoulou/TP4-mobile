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

    /**
     *
     * @param sorte
     * @param taille
     * @param prix
     * @param imgId
     */
    public Pizza(String sorte, String taille, double prix, int imgId) {
        this.sorte = sorte;
        this.taille = taille;
        this.prix = prix;
        this.imgId = imgId;
    }

    /**
     *
     */
    public Pizza() {
    }

    /**
     * les options de tailles de pizza
     * @return les options de grosseur
     */
    public String[][] getOptions() {
        String[][] a = {{"pépito", prix*0.5+""},{"Moyenne", prix+""},{"grosse", prix*27.5+""}};
        return a;
    };

    /**
     * getter de l'id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     *  getter de l'image
     * @return imgId
     */
    public int getImgId() {
        return imgId;
    }

    /**
     * setter de l'image
     * @param imgId
     */
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    /**
     * getter de sorte
     * @return sorte
     */
    public String getSorte() {
        return sorte;
    }

    /**
     * setter de sorte
     * @param sorte
     */
    public void setSorte(String sorte) {
        this.sorte = sorte;
    }

    /**
     * getter de taille
     * @return taille
     */
    public String getTaille() {
        return taille;
    }

    /**
     * setter de taille
     * @param taille
     */
    public void setTaille(String taille) {
        this.taille = taille;
    }

    /**
     * getter de prix
     * @return prix
     */
    public double getPrix() {
        return prix;
    }

    /**
     * setter de prix
     * @param prix
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     * affiche les données de la pizza
     * @return pizza.toString
     */
    @Override
    public String toString() {
        return "Pizza{" +
                "sorte='" + sorte + '\'' +
                ", taille='" + taille + '\'' +
                ", prix=" + prix +
                '}';
    }
}
