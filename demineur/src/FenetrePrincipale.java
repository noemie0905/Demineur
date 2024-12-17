 
import demineur.Cellule;
import demineur.GrilleDeJeu;
import java.awt.GridLayout;
import javax.swing.JButton;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author elois  
 */

    
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * Classe pour gérer l'interface graphique du démineur.
 */
public class FenetrePrincipale extends javax.swing.JFrame {

    private GrilleDeJeu grilleDeJeu;
    private JButton[][] boutonsGrille;
    private Cellule Cellule;
    private boolean premiereCaseCliquee = false;  // Suivre si la première case a été cliquée

    public FenetrePrincipale(int nbLignes, int nbColonnes, int nbBombes) {
        initComponents();

        // Initialiser la logique du jeu
        grilleDeJeu = new GrilleDeJeu(new Cellule[nbLignes][nbColonnes], nbLignes, nbColonnes, nbBombes);
        grilleDeJeu.placerBombesAleatoirement();
        grilleDeJeu.calculerBombesAdjacentes();

        // Création de la grille dans le panneau
        PanneauGrille.setLayout(new GridLayout(nbLignes, nbColonnes));
        boutonsGrille = new JButton[nbLignes][nbColonnes];

        // Double boucle pour créer les boutons
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                JButton bouton_cellule = new JButton();
                boutonsGrille[i][j] = bouton_cellule; // Stocker les boutons pour un accès futur
                
                // Ajouter un ActionListener pour gérer les clics
                int ligne = i; // Capturer la valeur pour l'utiliser dans le lambda
                int colonne = j;
                bouton_cellule.addActionListener(e -> boutonClique(ligne, colonne));

                PanneauGrille.add(bouton_cellule);
            }
        }

        pack(); // Ajuster la fenêtre
    }

    /**
     * Méthode appelée lorsqu'un bouton est cliqué.
     * @param ligne La ligne du bouton cliqué
     * @param colonne La colonne du bouton cliqué
     */
private void boutonClique(int ligne, int colonne) {
    if (!premiereCaseCliquee) {
            // Si c'est la première case cliquée, on vérifie qu'elle est vide (sans bombe et sans bombes adjacentes)
            Cellule cellule = grilleDeJeu.getMatriceCellules(ligne, colonne);
            while (cellule.getPresenceBombe() || cellule.getNbBombesAdjacentes() > 0) {
                // Si la cellule a une bombe ou des bombes adjacentes, on demande à l'utilisateur de choisir une autre case
                JOptionPane.showMessageDialog(this, "Choisissez une autre case !", "Erreur case minée", JOptionPane.WARNING_MESSAGE);
                return;
            }
            premiereCaseCliquee = true;  // La première case a été choisie, on passe à l'étape suivante
        }
    Cellule cellule = grilleDeJeu.getMatriceCellules(ligne, colonne);

    //if (cellule.getRevelerCellule()) {
        //return; // Cellule déjà dévoilée
    //}

    grilleDeJeu.revelerCellule(ligne, colonne);  // Révéler la cellule

    mettreAJourAffichageCellule(ligne, colonne);  // Mettre à jour l'affichage de cette cellule

   if (cellule.getPresenceBombe()) {
    boutonsGrille[ligne][colonne].setText("B");
    JOptionPane.showMessageDialog(this, "Oh non ! Vous avez cliqué sur une bombe !", "Défaite", JOptionPane.ERROR_MESSAGE);
    afficherGrilleComplete();
    desactiverBoutons(); // Désactiver tous les boutons
    System.exit(0); // Fermer l'application
    return;
}
// Vérifier si toutes les cellules non-bombes sont révélées
if (verifierVictoire()) {
    JOptionPane.showMessageDialog(this, "Bravo ! Vous avez gagné !", "Victoire", JOptionPane.INFORMATION_MESSAGE);
    System.exit(0); // Fermer l'application
}
    // Mettre à jour l'affichage et propager si nécessaire
    revelerCelluleEtPropager(ligne, colonne);  // Propagation si la cellule est vide
}


   
     
    private void revelerCelluleEtPropager(int ligne, int colonne) {
    Cellule cellule = grilleDeJeu.getMatriceCellules(ligne, colonne);

    // Si la cellule est vide (pas de bombes adjacentes), on propage la révélation
    if (cellule.getNbBombesAdjacentes() == 0) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nouvelleLigne = ligne + i;
                int nouvelleColonne = colonne + j;

                 //Vérifier si la nouvelle cellule est valide et non déjà révélée
                if (nouvelleLigne >= 0 && nouvelleLigne < grilleDeJeu.getNbLignes() &&
                   nouvelleColonne >= 0 && nouvelleColonne < grilleDeJeu.getNbColonnes() &&
                   !(i == 0 && j == 0)) {
                    
                    Cellule celluleVoisine = grilleDeJeu.getMatriceCellules(nouvelleLigne, nouvelleColonne);
                    // Ne pas révéler la cellule si elle est déjà révélée
                        mettreAJourAffichageCellule(nouvelleLigne, nouvelleColonne);  // Mettre à jour l'affichage de la cellule// Révéler la cellule voisine
                       
                }
            }
        }
    }
}


    private void mettreAJourAffichageCellule(int ligne, int colonne) {
    Cellule cellule = grilleDeJeu.getMatriceCellules(ligne, colonne);
    JButton bouton = boutonsGrille[ligne][colonne];

    if (cellule.getRevelerCellule()) {
        if (cellule.getPresenceBombe()) {
            bouton.setText("B");
        } else if (cellule.getNbBombesAdjacentes() > 0) {
            bouton.setText(String.valueOf(cellule.getNbBombesAdjacentes()));
        } else {
            bouton.setText(" ");  // Cellule vide
        }
        bouton.setEnabled(false);  // Désactive le bouton après le clic
    }
    bouton.revalidate();  // Forcer la mise à jour
    bouton.repaint();     // Repeindre le bouton pour l'affichage
}


private void desactiverBoutons() {
    for (int i = 0; i < boutonsGrille.length; i++) {
        for (int j = 0; j < boutonsGrille[i].length; j++) {
            boutonsGrille[i][j].setEnabled(false); // Désactiver tous les boutons
        }
    }
}
    
    private void afficherGrilleComplete() {
        for (int i = 0; i < grilleDeJeu.getNbLignes(); i++) {
            for (int j = 0; j < grilleDeJeu.getNbColonnes(); j++) {
                mettreAJourAffichageCellule(i, j);
            }
        }
    }
    
    private boolean verifierVictoire() {
    for (int i = 0; i < grilleDeJeu.getNbLignes(); i++) {
        for (int j = 0; j < grilleDeJeu.getNbColonnes(); j++) {
            Cellule cellule = grilleDeJeu.getMatriceCellules(i, j);
            // Si une cellule sans bombe n'est pas révélée, ce n'est pas encore gagné
            if (!cellule.getPresenceBombe() && !cellule.getRevelerCellule()) {
                return false;
            }
        }
    }
    return true; // Toutes les cellules non-bombes sont révélées
}

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanneauGrille = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanneauGrille.setBackground(new java.awt.Color(51, 51, 255));
        PanneauGrille.setLayout(new java.awt.GridLayout(10, 10));
        getContentPane().add(PanneauGrille, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
java.awt.EventQueue.invokeLater(() -> {
            int nbLignes = 10, nbColonnes = 10, nbBombes = 10;
            new FenetrePrincipale(nbLignes, nbColonnes, nbBombes).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanneauGrille;
    // End of variables declaration//GEN-END:variables
}
