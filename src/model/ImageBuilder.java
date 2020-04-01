package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Classe permettant de générer l'image d'un repas (Singleton)
 */
public class ImageBuilder {
    /**
     * Attributs du controleur
     * L'instance (pattern singleton)
     */
    private static ImageBuilder instance = null;

    /**
     * Méthode renvoyant l'instance du controleur (pattern singleton)
     */
    public static ImageBuilder getInstance() {
        if(instance == null)
            instance = new ImageBuilder();
        return instance;
    }

    /**
     * Méthode renvoyant l'image correspondante aux valeurs données d'un Meal
     */
    public BufferedImage buildImage(Meal.Tray tray, Meal.Dessert dessert, Meal.Drink drink, Meal.Fries fries,
                                    Meal.Sandwich sandwich, Meal.Sauce sauce) {

        // On initialise toutes les URL des images
        BufferedImage trayImg, dessertImg, drinkImg, friesImg, sandwichImg, sauceImg;
        trayImg = dessertImg = drinkImg = friesImg = sandwichImg = sauceImg = null;

        // On récupère toutes les images à superposer (une par composant)
        try {
            trayImg = ImageIO.read(new File("images/tray/" + tray.toString().toLowerCase() + ".png"));
            dessertImg = ImageIO.read(new File("images/dessert/" + dessert.toString().toLowerCase() + ".png"));
            drinkImg = ImageIO.read(new File("images/drink/" + drink.toString().toLowerCase() + ".png"));
            friesImg = ImageIO.read(new File("images/fries/" + fries.toString().toLowerCase() + ".png"));
            sandwichImg = ImageIO.read(new File("images/sandwich/" + sandwich.toString().toLowerCase() + ".png"));
            sauceImg = ImageIO.read(new File("images/sauce/" + sauce.toString().toLowerCase() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // On combine toutes les images récupérées et on retourne l'image ainsi créée
        BufferedImage combined = new BufferedImage(900, 680, BufferedImage.TYPE_INT_ARGB);
        Graphics g = combined.getGraphics();
        g.drawImage(trayImg, 0, 0, null);
        g.drawImage(dessertImg, 0, 0, null);
        g.drawImage(drinkImg, 0, 0, null);
        g.drawImage(friesImg, 0, 0, null);
        g.drawImage(sandwichImg, 0, 0, null);
        g.drawImage(sauceImg, 0, 0, null);

        return combined;
    }
}
