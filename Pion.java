public class Pion {

    public enum Couleur {
        JAUNE,ROUGE
    }

    // Attribut
    private Couleur color;

    // Constructeur
    public Pion(Couleur col) {
        this.color = col;
    }

    // Accesseur et Setter
    public Couleur getColor() {
        return this.color;
    }

    public void setColor(Couleur col) {
        this.color = col;
    }

    public boolean estJaune() {
        return  this.color == Couleur.JAUNE;
    }

    public boolean estRouge() {
        return  this.color == Couleur.ROUGE;
    }
    
}
