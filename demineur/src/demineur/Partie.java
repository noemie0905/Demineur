/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demineur;

/**
 *
 * @author elois
 */


public class Partie {
    private GrilleDeJeu grille;
    private int nombreDeVies;
    private boolean enCours;

    public Partie(int nbLignes, int nbColonnes, int nbBombes) {
        this.grille = new GrilleDeJeu(nbLignes, nbColonnes, nbBombes);
        this.nombreDeVies = 3;
        this.enCours = true;
    }

    public void initialiserPartie() {
        grille.initialiserGrille();
        grille.melangerBombes();
        this.nombreDeVies = 3;
        this.enCours = true;
    }

    // Méthode pour gérer un tour de jeu
    public void tourDeJeu(int ligne, int colonne) {
        // Vérifier si la partie est en cours
        if (!enCours) {
            System.out.println("La partie est terminée. Veuillez redémarrer une nouvelle partie.");
            return;
        }

        // Vérifier si les coordonnées sont valides
        if (ligne < 0 || ligne >= grille.getNbLignes() || colonne < 0 || colonne >= grille.getNbColonnes()) {
            System.out.println("Coordonnées invalides !");
            return;
        }

        cellule caseCourante = grille.getCellule(ligne, colonne);

        // Si la cellule contient une bombe
        if (caseCourante.estUneBombe()) {
            System.out.println("Bombe trouvée ! Vous perdez une vie.");
            perdreUneVie();
        } else {
            System.out.println("Pas de bombe ici. Vous êtes en sécurité.");
        }

        // Marquer la cellule comme révélée
        caseCourante.reveler();

        // Vérifier si la partie est terminée
        if (!enCours) {
            System.out.println("Vous avez perdu toutes vos vies. Partie terminée !");
        }
    }

    public void perdreUneVie() {
        if (nombreDeVies > 0) {
            nombreDeVies--;
        }
        if (nombreDeVies == 0) {
            enCours = false;
        }
    }

    public boolean isEnCours() {
        return enCours;
    }

    public void afficherGrille() {
        grille.afficherGrille();
    }
}




 


