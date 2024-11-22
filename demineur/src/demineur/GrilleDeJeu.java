/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demineur;

/**
 *
 * @author phili
 */
public class GrilleDeJeu {cellule matriceCellules; int nbLignes; int nbColonnes; int nbBombes; 

    public GrilleDeJeu(cellule matriceCellules, int nbLignes, int nbColonnes, int nbBombes) {
        this.matriceCellules = matriceCellules;
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.nbBombes = nbBombes;
    }
    
}
