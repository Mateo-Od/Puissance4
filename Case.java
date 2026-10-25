public class Case {

    // Attributs
    // Coordonnées (x,y) d'une case dans le tableau
    private int x;
    private int y;
    private Pion pion;

    // Constructeur avec un Pion
    public Case(int x, int y, Pion pion) {
        this.x = x;
        this.y = y;
        this.pion = pion;
    }

    // Constructeur avec une couleur
    public Case(int x, int y, Pion.Couleur color) {
        this(x, y, new Pion(color));
    }
    
    // Constructeur sans Pion (null)
    public Case(int x, int y) {
        this.x = x;
        this.y = y;
        this.pion = null;
    }

    // Accesseurs
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Pion getPion() {
        return this.pion;
    }

    // Setters
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void setPion(Pion pion) {
        this.pion = pion;
    }

    // Méthodes
    public boolean estVide() {
        /* Permet de déterminer si une case est vide. */
        return this.pion == null;
    }

    // 


}
