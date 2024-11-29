/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demineur;

import java.util.Scanner;

/**
 *
 * @author elois
 */

public class Partie {
    private GrilleDeJeu grille;  // L'attribut grille de type GrilleDeJeu
    private int nombreDeVies;
    private boolean enCours;

    // Constructeur de la classe Partie
    public Partie(int nbLignes, int nbColonnes, int nbBombes) {
        // Initialisation de la grille avec les dimensions et le nombre de bombes
        this.grille = new GrilleDeJeu(new cellule[nbLignes][nbColonnes], nbLignes, nbColonnes, nbBombes);
        this.nombreDeVies = 3;  // Nombre de vies initial
        this.enCours = true;    // La partie commence en état actif
    }

    // Méthode pour initialiser la partie
    public void initialiserPartie() {
        // Initialiser les cellules et les bombes
        grille.placerBombesAleatoirement();
        grille.calculerBombesAdjacentes();
        this.nombreDeVies = 3;  // Réinitialiser les vies
        this.enCours = true;    // La partie commence
    }

    // Méthode pour gérer un tour de jeu
    public void tourDeJeu(int ligne, int colonne) {
        // Vérification des coordonnées valides
        if (ligne < 0 || ligne >= grille.getNbLignes() || colonne < 0 || colonne >= grille.getNbColonnes()) {
            System.out.println("Coordonnées invalides !");
            return;
        }

        // Révéler la cellule spécifiée
        cellule caseCourante = grille.getMatriceCellules(ligne,colonne);

        // Si la cellule contient une bombe
        if (caseCourante.getPresenceBombe()) {
            System.out.println("Bombe trouvée ! Vous perdez une vie.");
            perdreUneVie();
        } else {
            // Révéler la cellule si ce n'est pas une bombe
            caseCourante.revelerCellule();
            if (caseCourante.getNbBombesAdjacentes() == 0) {
                // Si aucune bombe adjacente, révéler les cellules adjacentes
                grille.revelerCellule(ligne, colonne);
            }
        }
    }

    // Méthode pour perdre une vie
    public void perdreUneVie() {
        if (nombreDeVies > 0) {
            nombreDeVies--;
        }
        if (nombreDeVies == 0) {
            enCours = false;  // La partie se termine si plus de vies
        }
    }

    // Méthode pour vérifier la victoire
    public boolean verifierVictoire() {
        // Si toutes les cellules sûres (sans bombe) sont révélées, c'est la victoire
        return grille.toutesCellulesRevelees();
    }

    // Méthode pour démarrer la partie
    public void demarrerPartie() {
        Scanner scanner = new Scanner(System.in);

        // Boucle principale du jeu
        while (enCours) {
            // Afficher la grille à chaque tour
            System.out.println("Grille actuelle:");
            grille.toString();
            System.out.println("Vies restantes: " + nombreDeVies);

            // Demander au joueur de saisir les coordonnées de la cellule à révéler
            System.out.print("Entrez la ligne de la cellule à révéler (0 à " + (grille.getNbLignes() - 1) + "): ");
            int ligne = scanner.nextInt();
            System.out.print("Entrez la colonne de la cellule à révéler (0 à " + (grille.getNbColonnes() - 1) + "): ");
            int colonne = scanner.nextInt();

            // Gérer le tour de jeu
            tourDeJeu(ligne, colonne);

            // Vérifier si la partie est terminée (victoire)
            if (verifierVictoire()) {
                System.out.println("Félicitations, vous avez gagné !");
                enCours = false;
            }

            // Vérifier si la partie est terminée (perte de toutes les vies)
            if (!enCours) {
                System.out.println("La partie est terminée.");
            }
        }

        scanner.close();  // Fermeture du scanner à la fin de la partie
    }
}




 


