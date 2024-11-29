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
    private cellule[][] matriceCellules; 
    private int nbLignes; 
    private int nbColonnes; 
    int nbBombes; 

    public GrilleDeJeu(cellule[][] matriceCellules, int nbLignes, int nbColonnes, int nbBombes) {
        this.matriceCellules = matriceCellules;
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
    
    public void placerBombesAleatoirement(){
        Random random = new Random();
        int nbBombesplacees = 0;
        while (nbBombesplacees<=getNbBombes()){
            int i =  random.nextInt(getNbColonnes());
            int j =  random.nextInt(getNbLignes());
            if(!matriceCellules[i][j].getPresenceBombe()){
                matriceCellules[i][j].placerBombe();
                nbBombesplacees+=1; 
            }
        }    
        }
    public void calculerBombesAdjacentes(){
    int nbLignes = getNbLignes();
    int nbColonnes = getNbColonnes();

    for (int i = 0; i < nbColonnes; i++) { // Parcours des colonnes
        for (int j = 0; j < nbLignes; j++) { // Parcours des lignes
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
                        if (voisinX >= 0 && voisinX < nbColonnes && voisinY >= 0 && voisinY < nbLignes) {
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

    public void revelerCellule(int ligne, int colonne){
        
    }
    }
    

