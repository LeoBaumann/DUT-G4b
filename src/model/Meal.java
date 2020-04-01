package model;

import java.awt.image.BufferedImage;

/**
 * Classe qui représente un repas, chaque repas a plusieurs composants :
 * Plateau (Tray), Sandwich, Frites (Fries), Boisson (Drink), Dessert et Sauce
 *
 * Chaque repas est également représenté par une image (attribut Image) et un attribut selectionné ou non
 */
public class Meal {
    /**
     * Énumérations qui contiennent les différentes valeurs possibles pour chacun des composants d'un Meal
     */
    public enum Tray {BLUE, BLACK, RED, GREEN};
    public enum Sandwich {BIGMAC, CHEESE, FISH};
    public enum Fries {BIG, SMALL};
    public enum Drink {COFFEE, JUICE, WATER};
    public enum Dessert {MILKSHAKE, PIE, SUNDAY};
    public enum Sauce {BARBECUE, CURRY, KETCHUP};
    public enum Selected {TRUE, FALSE};

    /**
     * Attributs de la classe Meal (tous les composants + l'image)
     */
    private Tray tray;
    private Sandwich sandwich;
    private Fries fries;
    private Drink drink;
    private Dessert dessert;
    private Sauce sauce;
    private Selected selected;
    private BufferedImage image;

    /**
     * Constructeur de la classe Meal
     */
    public Meal(Tray tray, Sandwich sandwich, Fries fries, Drink drink, Dessert dessert, Sauce sauce) {
        this.tray = tray;
        this.sandwich = sandwich;
        this.fries = fries;
        this.drink = drink;
        this.dessert = dessert;
        this.sauce = sauce;
        this.selected = Selected.FALSE;
        this.image = ImageBuilder.getInstance().buildImage(tray, dessert, drink, fries, sandwich, sauce);
    }
    
    /**
     * Méthode renvoyant le type de Tray
     */
    public Tray getTray() {
        return this.tray;
    }
    
    /**
     * Méthode renvoyant le type de Sandwich
     */
    public Sandwich getSandwich() {
        return this.sandwich;
    }
    
    /**
     * Méthode renvoyant le type de Fries
     */
    public Fries getFries() {
        return this.fries;
    }
    
    /**
     * Méthode renvoyant le type de Drink
     */
    public Drink getDrink() {
        return this.drink;
    }
    
    /**
     * Méthode renvoyant le type de Dessert
     */
    public Dessert getDessert() {
        return this.dessert;
    }
    
    /**
     * Méthode renvoyant le type de Sauce
     */
    public Sauce getSauce() {
        return this.sauce;
    }
    
    /**
     * Méthode renvoyant si le Meal est sélectionné ou non
     */
    public Selected getSelected() {
        return this.selected;
    }
    
    /**
     * Méthode pour choisir ou non un Meal
     */
    public void setSelected(Selected selected) {
        this.selected = selected;
    }

    /**
     * Méthode renvoyant l'image du Meal
     */
    public BufferedImage getImage() {
        return image;
    }
}

