public class Joueur {

    public static int compteurId = 0;
    
    // Attributs
    private int id;
    private String name;
    private int nbVictoires;
    
    // Constructeur
    public Joueur(String nom) {
        compteurId++;
        this.id = compteurId;
        this.name = nom;
        this.nbVictoires = 0;
    }

    // Getters/Setters

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getNbVictoires() {
        return this.nbVictoires;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNbVictoires(int nb) {
        this.nbVictoires = nb;
    }

}
