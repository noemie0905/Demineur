/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demineur;

/**
 *
 * @author elois
 */
public class cellule {
      // Attributs privés de la classe
    private boolean presenceBombe; // Indique si la cellule contient une bombe
    private boolean devoilee;      // Indique si la cellule a été dévoilée
    private int nbBombesAdjacentes; // Nombre de bombes adjacentes à cette cellule

    // Constructeur de la classe
    public cellule(boolean presenceBombe, boolean devoilee, int nbBombesAdjacentes) {
        this.presenceBombe = presenceBombe; // Initialise si la cellule contient une bombe
        this.devoilee = devoilee;           // Initialise si la cellule est dévoilée
        this.nbBombesAdjacentes = nbBombesAdjacentes; // Initialise le nombre de bombes adjacentes
    }

    // Accesseur en lecture pour savoir si la cellule contient une bombe
    public boolean getPresenceBombe() {
        return presenceBombe;
    }

    // Accesseur en lecture pour obtenir le nombre de bombes adjacentes
    public int getNbBombesAdjacentes() {
        return nbBombesAdjacentes;
    }

    // Accesseur en écriture pour définir le nombre de bombes adjacentes
    public void setNbBombesAdjacentes(int nbBombesAdjacentes) {
        this.nbBombesAdjacentes = nbBombesAdjacentes;
    }

    // Méthode pour placer une bombe dans la cellule
    public void placerBombe() {
        this.presenceBombe = true; // Place une bombe dans la cellule
    }

    // Méthode pour révéler la cellule
    public void revelerCellule() {
        this.devoilee = true; // Marque la cellule comme dévoilée
    }
    

    @Override
    public String toString() {
         if (!devoilee) {
            return "?"; // Cellule non dévoilée
        } else if (presenceBombe) {
            return "B"; // Bombe dans la cellule dévoilée
        } else if (nbBombesAdjacentes > 0) {
            return String.valueOf(nbBombesAdjacentes); // Nombre de bombes adjacentes
        } else {
            return " "; // Cellule dévoilée sans bombe ni bombes adjacentes
        }

    }

    
    
}
