package view;

import controller.MealController;
import model.Meal;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Classe représentant l'affichage des différents repas
 */
public class Window {
    /**
     * Méthode permettant de lancer l'affichage
     */
    public static void start() {
        // Couleurs utilisées pour la selection des repas
        Color colorRed = new Color(239, 154, 154);
        Color colorGreen = new Color(129, 199, 132);

        // Création et configuration de la fenêtre
        JFrame jf = new JFrame();
        jf.setTitle("Application G4b : Informatique decisionelle");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setPreferredSize(new Dimension(1800, 1000));
        jf.setLocation(50,20);
        jf.setResizable(false);

        // Conteneur principal
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());

        // Grille avec les images
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(4, 5));
        MealController mc = MealController.getInstance();
        ArrayList<Meal> mealsList = MealController.getInstance().getMeals();
        for(Meal meal : mealsList) {
            // Pour chaque Meal de la liste, on créer un bouton
            JButton btn = new JButton();
            btn.setBackground(colorRed);
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);

            // Gestion de la selection du repas en question
            btn.addActionListener(e -> {
                if(meal.getSelected().equals(Meal.Selected.TRUE)) {
                    mc.removeSelectedMeal(meal);
                    btn.setBackground(colorRed);
                } else {
                    mc.addSelectedMeal(meal);
                    btn.setBackground(colorGreen);
                }
            });

            // On ajoute un icone au bouton qui correspond à l'image du Meal
            ImageIcon icon = new ImageIcon(meal.getImage());
            Image img = icon.getImage().getScaledInstance(300, 227, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
            btn.setIcon(icon);
            grid.add(btn);
        }

        JButton confirmBtn = new JButton("AFFICHER L'ARBRE");
        confirmBtn.addActionListener(e -> {
            TreeBuilder.buildTree(MealController.getInstance().getMeals());
        });

        // Ajout de tous les composants a la fenêtre
        container.add(grid, BorderLayout.CENTER);
        container.add(confirmBtn, BorderLayout.PAGE_END);
        jf.add(container);

        // Affichage de la fenêtre
        jf.pack();
        jf.setVisible(true);
    }
}
