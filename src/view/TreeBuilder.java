package view;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;

import javax.swing.JFrame;

import model.Meal;

import weka.classifiers.trees.J48;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

/**
 * Classe de création de l'arbre
 */
public class TreeBuilder {
    /**
     * méthode générant un arbre d'après la liste des Meal généré aléatoirement et choisi par l'utilisateur
     */
    public static void buildTree(ArrayList<Meal> alMeal) {
        
        // Create attribute + values
        ArrayList<String> alDessert = new ArrayList<String>(3);
        alDessert.add("MILKSHAKE");
        alDessert.add("PIE");
        alDessert.add("SUNDAY");
        Attribute attDessert = new Attribute("Dessert", alDessert);
        
        // Create attribute + values
        ArrayList<String> alDrink = new ArrayList<String>(3);
        alDrink.add("COFFEE");
        alDrink.add("JUICE");
        alDrink.add("WATER");
        Attribute attDrink = new Attribute("Drink", alDrink);
        
        // Create attribute + values
        ArrayList<String> alFries = new ArrayList<String>(2);
        alFries.add("BIG");
        alFries.add("SMALL");
        Attribute attFries = new Attribute("Fries", alFries);
        
        // Create attribute + values
        ArrayList<String>  alSandwich = new ArrayList<String>(3);
        alSandwich.add("BIGMAC");
        alSandwich.add("CHEESE");
        alSandwich.add("FISH");
        Attribute attSandwich = new Attribute("Sandwich", alSandwich);
        
        // Create attribute + values
        ArrayList<String> alSauce = new ArrayList<String>(3);
        alSauce.add("BARBECUE");
        alSauce.add("CURRY");
        alSauce.add("KETCHUP");
        Attribute attSauce = new Attribute("Sauce", alSauce);
        
        // Create attribute + values
        ArrayList<String> alTray = new ArrayList<String>(4);
        alTray.add("BLACK");
        alTray.add("BLUE");
        alTray.add("GREEN");
        alTray.add("RED");
        Attribute attTray = new Attribute("Tray", alTray);

        // Create class + values
        ArrayList<String> alClassVal = new ArrayList<String>(2);
        alClassVal.add("TRUE");
        alClassVal.add("FALSE");
        Attribute ClassAttribute = new Attribute("theClass", alClassVal);

        // Stock in list
        ArrayList<Attribute> alWekaAttributes = new ArrayList<Attribute>(7);
        alWekaAttributes.add(attDessert);
        alWekaAttributes.add(attDrink);
        alWekaAttributes.add(attFries);
        alWekaAttributes.add(attSandwich);
        alWekaAttributes.add(attSauce);
        alWekaAttributes.add(attTray);
        alWekaAttributes.add(ClassAttribute);

        // Create an empty training set
        Instances data = new Instances("Rel", alWekaAttributes, 7);

        // Set class index (last one)
        data.setClassIndex(data.numAttributes() - 1);

        // loop to fill the data
        for(Meal meal : alMeal) {
            Instance instance = new DenseInstance(7);
            instance.setValue((Attribute)alWekaAttributes.get(0), meal.getDessert().name());
            instance.setValue((Attribute)alWekaAttributes.get(1), meal.getDrink().name());
            instance.setValue((Attribute)alWekaAttributes.get(2), meal.getFries().name());
            instance.setValue((Attribute)alWekaAttributes.get(3), meal.getSandwich().name());
            instance.setValue((Attribute)alWekaAttributes.get(4), meal.getSauce().name());
            instance.setValue((Attribute)alWekaAttributes.get(5), meal.getTray().name());
            instance.setValue((Attribute)alWekaAttributes.get(6), meal.getSelected().name());
            data.add(instance);
        }
        
        // Create tree
        J48 tree = new J48();
        tree.setUnpruned(true);

        try{
            // Build the tree
            tree.buildClassifier(data);
            System.out.println(tree);

            // Create the window
            final JFrame jf = new JFrame("J48");
            jf.setSize(900,700);
            jf.getContentPane().setLayout(new BorderLayout());
            
            // Create the Display of the tree
            TreeVisualizer tv = new TreeVisualizer(null, tree.graph(), new PlaceNode2());
            
            // Add the tree to the window
            jf.getContentPane().add(tv, BorderLayout.CENTER);
            jf.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    jf.dispose();
                }
            });
            
            // Display the tree
            jf.setVisible(true);
            tv.fitToScreen();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
