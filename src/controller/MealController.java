package controller;

import model.Meal;
import model.Meal.*;

import java.util.ArrayList;
import java.util.Random;


/**
 * Controleur gérant les Meal (Singleton)
 * Il contient la liste des Meals, des selectedMeals (repas sélectionnés)
 */
public class MealController {

    /**
     * Attributs du controleur
     * L'instance (pattern singleton), la liste des repas et la liste des repas sélectionnés
     */
    private static MealController instance = null;
    private ArrayList<Meal> meals  = new ArrayList<>();

    /**
     * Constructeur du MealController
     */
    private MealController(){ }

    /**
     * Méthode renvoyant l'instance du controleur (pattern singleton)
     */
    public static MealController getInstance() {
        if(instance == null)
            instance = new MealController();
        return instance;
    }

    /**
     * Méthode renvoyant la liste des repas existants
     */
    public ArrayList<Meal> getMeals(){
        return meals;
    }

    /**
     * Méthode permettant de créer un repas à partir de valeurs données
     */
    public void createMeal(Tray tray, Sandwich sandwich, Fries fries, Drink drink, Dessert dessert, Sauce sauce){
        meals.add(new Meal(tray, sandwich, fries, drink, dessert, sauce));
    }

    /**
     * Méthode permettant de créer un nombre determiné de repas dont la composition est aléatoire
     */
    public void createRandomMeals(int mealsNumber){
        while(getMeals().size() < mealsNumber) {

            // On détermine les composants de manière aléatoire
            int trayPick = new Random().nextInt(Tray.values().length);
            int sandwichPick = new Random().nextInt(Sandwich.values().length);
            int friesPick = new Random().nextInt(Fries.values().length);
            int drinkPick = new Random().nextInt(Drink.values().length);
            int dessertPick = new Random().nextInt(Dessert.values().length);
            int saucePick = new Random().nextInt(Sauce.values().length);

            // On crée le repas
            createMeal(Tray.values()[trayPick], Sandwich.values()[sandwichPick], Fries.values()[friesPick], Drink.values()[drinkPick], Dessert.values()[dessertPick], Sauce.values()[saucePick]);
        }
    }

    /**
     * Méthode permettant d'ajouter un repas aux repas sélectionnés
     */
    public void addSelectedMeal(Meal meal) {
        meal.setSelected(Selected.TRUE);
    }

    /**
     * Méthode permettant de supprimer un repas des repas sélectionnés
     */
    public void removeSelectedMeal(Meal meal) {
        meal.setSelected(Selected.FALSE);
    }
}
