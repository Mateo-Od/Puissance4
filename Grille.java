import java.util.Random;

public class Grille {

    // Attribut
    private Case[][] grille;

    // Constructeur
    public Grille() {
        this.grille = new Case[6][7]; // Init du tableau
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                grille[i][j] = new Case(i, j);
            }
        }
    }

    // Méthodes

    public void placerPion(Pion p, int y) {
        /* Permet de placer un pion dans notre grille
        A condition de :
        1) Que la position en y soit valide
        2) Que la grille ne déborde pas */
        int x = this.derniereLigneVide(y);
        if (x != -1) {
            // On place notre pion
            this.grille[x][y].setPion(p);
            // System.out.println("Pion placé");
        } 
    } 

    public int derniereLigneVide(int y) {
        /* Renvoie la position en y de la dernière ligne vide */
        if (this.colonneValide(y) && !this.debordeDeLaGrille(y)) { // Colonne valide et ne déborde pas
            for (int i = this.grille.length-1; i >= 0; i--) {
                if (this.grille[i][y].estVide()) {
                    // Ligne/colonne vide
                    return i; // On renvoie le position en y de la ligne valide
                }
            }
        }
        return -1;
    }

    public boolean colonneValide(int y) {
        /* Détermine si la position est valide pour
        placer un pion dans la grille */
        return y >= 0 && y < grille[0].length;
    }

    public boolean debordeDeLaGrille(int y) {
        /* Si on à un pion en haut de notre grille en y
        alors on ne peut pas placer de pion 
        true = déborde
        false = ne déborde pas*/
        if (!this.colonneValide(y)) {
            return true; // Déborde de la grille
        }
        return !this.grille[0][y].estVide();
    }

    public void resetGrille() { 
        /* Renvoie une nouvelle grille vide */
        this.grille = new Case[6][7];
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                grille[i][j] = new Case(i, j);
            }
        }
    }

    // Version itérative
    public boolean aGagne(Pion.Couleur color) {
        /* Permet de déterminer si le joueur
        avec les pions de couleur color
        à gagné. */

        int nbLignes = grille.length;
        int nbColonnes = grille[0].length;

        // On parcourt chaque case de la grille
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j< grille[i].length; j++) {
                
                // Cas case vide ou couleur non recherché
                if (grille[i][j].estVide() || grille[i][j].getPion().getColor() != color) {
                    continue; // On passe à la case suivante
                }

                // Horizontale
                if (j + 3 < nbColonnes && !grille[i][j+1].estVide() && grille[i][j+1].getPion().getColor() == color &&
                    !grille[i][j+2].estVide() && grille[i][j+2].getPion().getColor() == color &&
                    !grille[i][j+3].estVide() && grille[i][j+3].getPion().getColor() == color) {
                    return true;
                }

                if (i + 3 < nbLignes &&
                    !grille[i+1][j].estVide() && grille[i+1][j].getPion().getColor() == color &&
                    !grille[i+2][j].estVide() && grille[i+2][j].getPion().getColor() == color &&
                    !grille[i+3][j].estVide() && grille[i+3][j].getPion().getColor() == color) {
                    return true;
                }

                // 3. Vérification DIAGONALE DESCENTE (vers le bas-droite)
                if (i + 3 < nbLignes && j + 3 < nbColonnes &&
                    !grille[i+1][j+1].estVide() && grille[i+1][j+1].getPion().getColor() == color &&
                    !grille[i+2][j+2].estVide() && grille[i+2][j+2].getPion().getColor() == color &&
                    !grille[i+3][j+3].estVide() && grille[i+3][j+3].getPion().getColor() == color) {
                    return true;
                }

                // 4. Vérification DIAGONALE MONTANTE (vers le haut-droite)
                if (i - 3 >= 0 && j + 3 < nbColonnes &&
                    !grille[i-1][j+1].estVide() && grille[i-1][j+1].getPion().getColor() == color &&
                    !grille[i-2][j+2].estVide() && grille[i-2][j+2].getPion().getColor() == color &&
                    !grille[i-3][j+3].estVide() && grille[i-3][j+3].getPion().getColor() == color) {
                    return true;
                }
            }
        }
        return false;
    }  

    public boolean grillePleine() {
        /*Renvoie true si la grille est pleine 
        et false sinon */
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                if (grille[i][j].estVide()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean matchNul() {
        /*Renvoie true si personne n'a gagné et si la grille est pleine */
        return !aGagne(Pion.Couleur.JAUNE) && !aGagne(Pion.Couleur.ROUGE) && grillePleine();
    }

    public void remplirAléatoire() {
        /*Rempli de façon aléatoire la grille de jeu */
        Random random = new Random();
        boolean rempli = false;
        int y = 0;
        int val = 0;
        while (!rempli) { // On joue tant que ce la grille n'est pas pleine
            y = random.nextInt(grille[0].length); // entre 0 et grille.length
            val = random.nextInt(2); // 0 ou 1
            if (val == 0) { // Pion rouge
                placerPion(new Pion(Pion.Couleur.ROUGE), y);
            } else {
                placerPion(new Pion(Pion.Couleur.JAUNE), y);
            }
            // Si grille pleine
            if (this.grillePleine()) {
                rempli = true;
            }
        }
    }


    @Override
    public String toString() {
        /* Fonction d'affichage de la grille 
        Case vide = V
        Rouge = R
        Jaune = J*/

        // Déclarations
        StringBuilder sb = new StringBuilder();
        int nbLignes = grille.length;
        int nbColonnes = grille[0].length;
        String ANSI_JAUNE = "\u001B[93m"; // Jaune 
        String ANSI_ROUGE = "\u001B[91m"; // Rouge 
        String ANSI_RESET = "\u001B[0m";  // Remet la couleur par défaut

        // Numéro de lignes
        int numColonnes = 0;
        sb.append("    ");
        for (int i = 0; i < nbColonnes; i++) {
            sb.append(numColonnes).append("   ");
            numColonnes++;
        }
        sb.append("\n");

        char lettreLigne = 'A';

        // Création grille
        for (int l = 0; l < nbLignes; l++) {

            // Lignes
            sb.append("  ");
            for (int i = 0; i < nbColonnes; i++) {
                sb.append("+---");
            }
            sb.append("+").append("\n");

            // Colonnes
            sb.append(lettreLigne).append(" ");
            for (int i = 0; i < nbColonnes; i++) {
                if (grille[l][i].estVide()) { // Case vide
                    sb.append("|   ");
                } else if (grille[l][i].getPion().estJaune()) {
                    sb.append("| ").append(ANSI_JAUNE).append("O").append(ANSI_RESET).append(" ");
                } else {
                    sb.append("| ").append(ANSI_ROUGE).append("O").append(ANSI_RESET).append(" ");
                }
            }
            lettreLigne++;
            sb.append("|").append("\n");

        }
        
        sb.append("  ");
        for (int i = 0; i < nbColonnes; i++) {
            sb.append("+---");
        }
        sb.append("+\n");

        int numeroColonnes2 = 0;
        sb.append("    ");
        for (int i = 0; i < nbColonnes; i++) {
            sb.append(numeroColonnes2).append("   ");
            numeroColonnes2++;
        }

        return sb.toString(); 
        
    }

}