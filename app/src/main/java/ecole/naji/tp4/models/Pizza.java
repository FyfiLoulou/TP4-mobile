package ecole.naji.tp4.models;

public class Pizza {
    private String sorte;
    private String taille;
    private double prix;

    public Pizza(String sorte, String taille, double prix) {
        this.sorte = sorte;
        this.taille = taille;
        this.prix = prix;
    }

    public Pizza() {
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
