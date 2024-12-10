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

    /**
     * Construcetur sans l'id
     * @param nom : le nom du client
     * @param email : l'email du client
     * @param pw : le mot de passe du client
     * @param adresse : l'adresse du client
     * @param tel : le numero de telephone du client
     * @param point : les points accumulé du client
     */
    public Client(String nom, String email, String pw, String adresse, String tel, int point) {
        this.nom = nom;
        this.email = email;
        this.pw = pw;
        this.adresse = adresse;
        this.tel = tel;
        this.point = point;
    }

    /**
     * constructeur sans paramètres
     */
    public Client() {
    }

    /**
     * getter de l'id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * getter du nom
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * setter du nom
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getter de l'email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * setter de l'email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * getter du mot de passe
     * @return pw
     */
    public String getPw() {
        return pw;
    }

    /**
     * setter du mot de passe
     * @param pw
     */
    public void setPw(String pw) {
        this.pw = pw;
    }

    /**
     * getter de l'adresse
     * @return adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * setter de l'adresse
     * @param adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * getter du telephone
     * @return tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * calculer les points du client lorsqu'il achète une pizza sans utiliser ses points
     * @param monney
     */
    public void calculerPts(double monney){
        this.point += ((int)monney * 10);
    }

    /**
     * setter du telephone
     * @param tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * getter des points
     * @return point
     */
    public int getPoint() {
        return point;
    }

    /**
     * setter des points
     * @param point
     */
    public void setPoint(int point) {
        this.point = point;
    }

    /**
     * toutes les données du client
     * @return client.toString
     */
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
