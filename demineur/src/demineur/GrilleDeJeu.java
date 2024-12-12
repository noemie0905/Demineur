/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demineur;

import java.util.Random;

/**
 *
 * @author phili
 */
public class GrilleDeJeu {

    private Cellule[][] matriceCellules;
    private int nbLignes;
    private int nbColonnes;
    int nbBombes;

    public GrilleDeJeu(Cellule[][] matriceCellules, int nbLignes, int nbColonnes, int nbBombes) {
        this.matriceCellules = matriceCellules;
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                matriceCellules[i][j] = new Cellule(false, false, 0);
            }
        }
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.nbBombes = nbBombes;
    }

    public int getNbLignes() {
        return nbLignes;
    }

    public int getNbBombes() {
        return nbBombes;
    }

    public int getNbColonnes() {
        return nbColonnes;
    }

    public Cellule getMatriceCellules(int i, int j) {
        return matriceCellules[i][j];
    }

    public void placerBombesAleatoirement() {
        Random random = new Random();
        int nbBombesplacees = 0;
        while (nbBombesplacees < getNbBombes()) {
            int j = random.nextInt(getNbColonnes());
            int i = random.nextInt(getNbLignes());
            if (!matriceCellules[i][j].getPresenceBombe()) {
                matriceCellules[i][j].placerBombe();
                nbBombesplacees++;
            }
        }

    }

    public void calculerBombesAdjacentes() {
        int nbLignes = getNbLignes();
        int nbColonnes = getNbColonnes();

        for (int i = 0; i < nbLignes; i++) { // Parcours des colonnes
            for (int j = 0; j < nbColonnes; j++) { // Parcours des lignes
                // Vérifie si la cellule actuelle n'est pas une bombe
                if (!matriceCellules[i][j].getPresenceBombe()) {
                    int bombesAdjacentes = 0;

                    // Parcourt les 8 cellules adjacentes
                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            // Ignore la cellule elle-même
                            if (x == 0 && y == 0) {
                                continue;
                            }

                            // Coordonnées de la cellule adjacente
                            int voisinX = i + x;
                            int voisinY = j + y;

                            // Vérifie que les coordonnées sont dans les limites de la grille
                            if (voisinX >= 0 && voisinX < nbLignes && voisinY >= 0 && voisinY < nbColonnes) {
                                if (matriceCellules[voisinX][voisinY].getPresenceBombe()) {
                                    bombesAdjacentes++;
                                }
                            }
                        }
                    }
                    matriceCellules[i][j].setNbBombesAdjacentes(bombesAdjacentes);
                }
            }
        }
    }

    public void revelerCellule(int ligne, int colonne) {
        if (ligne < 0 || ligne >= getNbLignes() || colonne < 0 || colonne >= getNbColonnes()) {
            return;
        }
        Cellule cellule = matriceCellules[ligne][colonne];

        if (cellule.getRevelerCellule()) {
            return;
        }
        cellule.revelerCellule();

        if (cellule.getPresenceBombe()) {
            return;
        }

        if (cellule.getNbBombesAdjacentes() == 0) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i != 0 || j != 0) {
                        revelerCellule(ligne + i, colonne + j);
                    }
                }
            }
        }
    }

    public boolean getPresenceBombe(int i, int j) {
        if (matriceCellules[i][j].getPresenceBombe()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean toutesCellulesRevelees() {
        for (int i = 0; i < getNbColonnes(); i++) {
            for (int j = 0; j < getNbLignes(); j++) {
                Cellule cellule = matriceCellules[i][j];
                if (!cellule.getPresenceBombe() && !cellule.getRevelerCellule()) {
                    return false;
                }
            }
        }
        return true;
    }

@Override
public String toString() {
    StringBuilder grille = new StringBuilder();
    
    for (int i = 0; i < nbLignes; i++) {
        for (int j = 0; j < nbColonnes; j++) {
            Cellule cellule = matriceCellules[i][j];
            
            if (!cellule.getRevelerCellule()) {
                grille.append("?"); // Cellule non révélée
            } else if (cellule.getPresenceBombe()) {
                grille.append("B"); // Cellule contenant une bombe
            } else if (cellule.getNbBombesAdjacentes() > 0) {
                grille.append(cellule.getNbBombesAdjacentes()); // Nombre de bombes adjacentes
            } else {
                grille.append(" "); // Cellule vide sans bombes adjacentes
            }
        }
        // Ajouter un saut de ligne après chaque ligne
        grille.append("\n");
    }
    
    return grille.toString();
}

}
