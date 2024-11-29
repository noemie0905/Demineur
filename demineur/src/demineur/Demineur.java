/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package demineur;

/**
 *
 * @author elois
 */
public class Demineur {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Création de cellules pour tester les fonctionnalités
        System.out.println("=== TEST DE LA CLASSE CELLULE ===");

        // Test cellule sans bombe, non dévoilée, avec 0 bombes adjacentes
        cellule cellule1 = new cellule(false, false, 0);
        System.out.println("Cellule 1 (non devoilee) : " + cellule1);

        // Révéler la cellule et tester l'affichage
        cellule1.revelerCellule();
        System.out.println("Cellule 1 (devoilee, pas de bombe, 0 bombes adjacentes) : " + cellule1);

        // Test cellule avec bombe, non dévoilée
        cellule cellule2 = new cellule(true, false, 0);
        System.out.println("Cellule 2 (non devoilee avec bombe) : " + cellule2);

        // Révéler la cellule avec bombe
        cellule2.revelerCellule();
        System.out.println("Cellule 2 (devoilee avec bombe) : " + cellule2);

        // Test cellule sans bombe, avec 3 bombes adjacentes, non dévoilée
        cellule cellule3 = new cellule(false, false, 3);
        System.out.println("Cellule 3 (non devoilee, 3 bombes adjacentes) : " + cellule3);

        // Révéler la cellule avec 3 bombes adjacentes
        cellule3.revelerCellule();
        System.out.println("Cellule 3 (devoilee, 3 bombes adjacentes) : " + cellule3);

        // Test mise à jour du nombre de bombes adjacentes
        cellule3.setNbBombesAdjacentes(5);
        System.out.println("Cellule 3 (apres mise a jour, 5 bombes adjacentes) : " + cellule3);
    }
}
