import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


public class Budgetar {



    public static void main(String[] args) {
        String a = new BudgetarFile().CreateDataFile("cogsFile.csv");  //Make sure the Data Storazge File Exists/Create
        double b =  new getData().GetBudget(0);         // Get the Budget Amount
        HashMap c = new getData().GetShoppingList();  // Collect the Items Cost Data
        //Boolean d =  new BudgetarFile().WriteToDataFile(Boolean.TRUE);
        String[] e = new getData().IndexShoppingList();     //Edit the Items Necessity Index

        //String b = new BudgetarFile().CreateDataFile("TestFile.csv");
        //String c = new BudgetarFile().ReadDataFile("TestFile.csv");
        //System.out.print("Budget Amount:" + c);


        //GetShoppingList();
        //IndexShoppingList();
        // CreateShoppingListFile();
    }

}


