package ecole.naji.tp4.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "client")
public class Client {
    @DatabaseField(columnName = "id", generatedId = true)
    private int id;
    @DatabaseField(columnName = "nom")
    private String nom; // min length 5
    @DatabaseField(columnName = "email")
    private String email;
    @DatabaseField(columnName = "pw")
    private String pw; // min length 6
    @DatabaseField(columnName = "adresse")
    private String adresse;
    @DatabaseField(columnName = "tel")
    private String tel;
    @DatabaseField(columnName = "point")
    private int point; // 10$ commande vaut 100 point

    public Client(String nom, String email, String pw, String adresse, String tel, int point) {
        this.nom = nom;
        this.email = email;
        this.pw = pw;
        this.adresse = adresse;
        this.tel = tel;
        this.point = point;
    }

    public Client() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "Client{" +
                "nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", pw='" + pw + '\'' +
                ", adresse='" + adresse + '\'' +
                ", tel='" + tel + '\'' +
                ", point=" + point +
                '}';
    }
}
