import java.util.Random;

public class Test {

    public static void main(String[] args) {
        
        Grille grille = new Grille();
        Pion rouge = new Pion(Pion.Couleur.ROUGE);
        Pion jaune = new Pion(Pion.Couleur.JAUNE);
        // grille.placerPion(rouge, 1);
        // grille.placerPion(jaune, 1);
        // grille.placerPion(jaune, 1);
        // grille.placerPion(jaune, 1);
        // grille.placerPion(jaune, 1);
        // grille.placerPion(jaune, 1);
        // grille.placerPion(rouge, 2);
        // grille.placerPion(jaune, 2);
        // grille.placerPion(rouge, 3);
        grille.remplirAléatoire();
        System.out.println(grille);
        System.out.println(grille.aGagne(Pion.Couleur.JAUNE));
        System.out.println(grille.matchNul());

        // Test
        // StringBuilder sb = new StringBuilder();
        // sb.append("Salut").append(1).append("\n").append("Deuxième ligne");
        // System.out.println(sb);

        // Random random = new Random();
        // System.out.println(random.nextInt(0, 2));


    }
    
}
