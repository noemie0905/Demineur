/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package demineur;

import java.util.Scanner;

/**
 *
 * @author elois
 */
public class Demineur {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
        
        System.out.println("Bienvenue dans le jeu Super Démineur !");
        System.out.print("Entrez le nombre de lignes pour la grille : ");
        int nbLignes = scanner.nextInt();

        System.out.print("Entrez le nombre de colonnes pour la grille : ");
        int nbColonnes = scanner.nextInt();

        System.out.print("Entrez le nombre de bombes : ");
        int nbBombes = scanner.nextInt();

        // Initialisation de la partie
        Partie partie = new Partie(nbLignes, nbColonnes, nbBombes);
        partie.initialiserPartie();

        System.out.println("La partie commence !");
        boolean victoire = false;
        boolean defaite = false;

        // Boucle principale du jeu
        while (!victoire && !defaite) {
            System.out.println(partie.getGrille().toString());
            System.out.print("Entrez le numéro de ligne : ");
            int ligne = scanner.nextInt();

            System.out.print("Entrez le numéro de colonne : ");
            int colonne = scanner.nextInt();

            try {
                partie.tourDeJeu(ligne, colonne);
                victoire = partie.verifierVictoire();
            } catch (Exception e) {
                System.out.println("Oh non ! Vous avez déclenché une bombe !");
                defaite = true;
            }
        }

        // Résultat final
        if (victoire) {
            System.out.println("Félicitations ! Vous avez gagné !");
        } else {
            System.out.println("Dommage ! Vous avez perdu.");
        }

        scanner.close();
    }
}  
    


