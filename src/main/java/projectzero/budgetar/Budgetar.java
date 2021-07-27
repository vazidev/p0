package projectzero.budgetar;

import java.util.HashMap;

public class Budgetar {
    public static void main(String[] args){
        System.out.println("Create Data File"); //working
        String a = new BudgetarFile().CreateDataFile("cogsFile.csv");  //Make sure the Data  File Exists : Create : Working
        double b =  new GetData().GetBudget(0);         // Get the Budget Amount : Working
       HashMap c = new GetData().GetShoppingList();  // Collect the Items Cost Data  and store in file :Working
        String[] d =  new BudgetarFile().ReadDataFile("cogsFile.csv");  //read Current List stored in File : Working
        HashMap e = new GetData().IndexShoppingList();     //Edit the Items Necessity Index (NV).


        new ShoppingList();







         //System.out.print("Budget Amount:" + c);


    }
}
