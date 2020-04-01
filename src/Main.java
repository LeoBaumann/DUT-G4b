import controller.MealController;
import view.Window;

/**
 * Programme principal
 * Il initie la création des repas et ouvre l'affichage
 */
public class Main {
    public static void main(String[] args) {
        MealController.getInstance().createRandomMeals(20);
        Window.start();
    }
}