//import java.util.*;

import java.util.HashMap;
import java.util.Scanner;



class Budgetar{
	
    public static void main(String[] args) {
        int x = 0;      //Counter value
        HashMap<String, Double> cogs = new HashMap<String, Double>();  //Cost of goods table {Item, Cost}
        HashMap<String, Double> cv = new HashMap<String, Double>();    // Stores table {Item, Critial Value}
        Scanner item = new Scanner(System.in);   // Java Data Input

        System.out.println("Enter Total $ Funds Available:");
        Double tfa = item.nextDouble();
        System.out.println("My Budget: $" + tfa);

        System.out.println(" Please populate the Items List");
        String ItemX;
        do {
            //ItemX = item.next();
            //Double cogX = item.nextDouble();       //Cost of Goods
            
            cogs.put(item.next(), item.nextDouble());
            //cogs.put(ItemX, cogX);                //Update Cost of Goods Hashmap, Key items
            System.out.println(cogs);
            x++;
            /* if (ItemX.isBlank() || ItemX.isEmpty() || ItemX.toLowerCase().contains("Exit")) {    //will detect an Empty 0r Zero values,
                x = 0;
            } */
        } while (!(item.next().isBlank() || item.next().isEmpty()) || (x<=5) || ItemX.contains("Exit"));
        System.out.println(cogs);
    }


}


