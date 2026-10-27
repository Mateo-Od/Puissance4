import java.util.*;

public class Jeu {

    // Classés par ordre de priorité
    // TODO: Dans grille, faire une version récursive de la fonction aGagne
    // TDOD: Reprendre la documentation du code
    // TODO: Clean le code

    public static void main(String[] args) {
        
        // Init du scanner
        Scanner scanner = new Scanner(System.in);

        // Init de la grille
        Grille grille = new Grille();

        // Init des Pions
        Pion Pionrouge = new Pion(Pion.Couleur.ROUGE);
        Pion Pionjaune = new Pion(Pion.Couleur.JAUNE);

        // Init des couleurs
        Pion.Couleur couleurRouge = Pion.Couleur.ROUGE;
        Pion.Couleur couleurJaune = Pion.Couleur.JAUNE;

        System.out.println("Entrez le nom du premier joueur (ROUGE) :");
        String nomJoueur1 = scanner.nextLine().trim();
        System.out.println("Entrez le nom de deuxième joueur (JAUNE) :");
        String nomJoueur2 = scanner.nextLine().trim();

        // Initialisation des joueurs : 
        Joueur joueur1 = new Joueur(nomJoueur1);
        Joueur joueur2 = new Joueur(nomJoueur2);

       
        boolean valide = false;
        int i = 0;


        while (!valide) { // On joue tant que personne na gagné ou que la grille n'est pas pleine.
            
            // Le joueur 1 joue en premier (ROUGE).
            // Ensuite le joueur 2 (JAUNE) et ainsi de suite.

            // On affiche notre grille
            System.out.println(grille);

            if (i % 2 == 0) {
                System.out.println("A " + joueur1.getName() + " de jouer " + "(ROUGE)." );
            } else {
                System.out.println("A " + joueur2.getName() + " de jouer " + "(JAUNE).");
            }

            if (!grille.aGagne(couleurJaune) && !grille.aGagne(couleurRouge) && !grille.grillePleine()) {
                // Peronne n'a gagné et la grille n'est pas pleine

                int y = 0;

                do { // Le joueur choisis la colonne dans laquelle il va placer son pion
                    System.out.println("Choisissez une colonne pour placer votre piont : ");
                    y = scanner.nextInt();
                    scanner.nextLine();
                } while (!grille.colonneValide(y) && grille.debordeDeLaGrille(y));

                if (i % 2 == 0) {
                    // Le jouer 1 joue en premier
                    grille.placerPion(Pionrouge, y);
                    System.out.println("Pion placé par le joueur 1 en y = " + y);
                } else {
                    grille.placerPion(Pionjaune, y);
                    System.out.println("Pion placé par le joueur 2 en y = " + y);
                }
                
                i++; // On incrémente de 1 la variable qui nous permet de faire varier le joueur.

            } else if (grille.aGagne(Pion.Couleur.JAUNE)) { // Le joueur 2 a gagné
                joueur2.setNbVictoires(joueur2.getNbVictoires()+1);
                System.out.println(" " + joueur2.getName() + " a gagné la manche, il à " + joueur2.getNbVictoires() + " point(s).");
                valide = true;
            } else if (grille.aGagne(Pion.Couleur.ROUGE)) { // Le joueur 1 a gagné
                joueur1.setNbVictoires(joueur1.getNbVictoires()+1);
                System.out.println(" " + joueur1.getName() + " a gagné la manche, il à " + joueur1.getNbVictoires() + " point(s).");
                valide = true;
            } else { // Mathc nul On propose de rejouer
                System.out.println("Match nul : ");
                System.out.println("Souhaitez-vous rejouez : (O/N)");
                String choix = scanner.nextLine();
                if (choix.equals("O")) {
                    grille.resetGrille();
                } else {
                    valide = true;
                }
            }
        }
    }
    
}
