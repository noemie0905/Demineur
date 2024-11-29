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
    
    
    
    // Partie pour verfifierVictoire
    
    
    public boolean verifierVictoire() {
    for (int i = 0; i < grille.getNbLignes(); i++) {
        for (int j = 0; j < grille.getNbColonnes(); j++) {
            cellule caseCourante = grille.getCellule(i, j);

            // Si la cellule ne contient pas de bombe et n'est pas révélée
            if (!caseCourante.estUneBombe() && !caseCourante.estRevelee()) {
                // Si on trouve une cellule sûre non révélée, le joueur n'a pas encore gagné
                return false;
            }
        }
    }
    // Si toutes les cellules sûres ont été révélées, le joueur a gagné
    return true;
}
    
public void demarrerPartie() {
        Scanner scanner = new Scanner(System.in);

        // Boucle principale du jeu
        while (enCours) {
            // Afficher la grille à chaque tour
            System.out.println("Grille actuelle:");
            grille.afficherGrille();

            // Afficher le nombre de vies restantes
            System.out.println("Vies restantes: " + nombreDeVies);

            // Demander au joueur de saisir les coordonnées
            System.out.print("Entrez la ligne de la cellule à révéler (0 à " + (grille.getNbLignes() - 1) + "): ");
            int ligne = scanner.nextInt();
            System.out.print("Entrez la colonne de la cellule à révéler (0 à " + (grille.getNbColonnes() - 1) + "): ");
            int colonne = scanner.nextInt();

            // Gérer le tour de jeu
            tourDeJeu(ligne, colonne);

            // Vérifier la victoire
            if (verifierVictoire()) {
                System.out.println("Félicitations, vous avez gagné !");
                enCours = false;
            }

            // Vérifier si la partie est terminée
            if (!enCours) {
                System.out.println("La partie est terminée.");
            }
        }

}}




 


