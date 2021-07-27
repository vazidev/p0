package projectzero.budgetar;

import java.io.*;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.*;

public class GetData {
    File cogFile = new File("FileCogs.csv");
    Scanner entry = new Scanner(System.in);
    //BufferedWriter brWrite = new BufferedWriter(new FileWriter(cogFile)); //Opens upp the Write to File function
    StringBuilder sbWrite = new StringBuilder();
    HashMap cogs = new HashMap<String, ArrayList<Object>>();
    HashMap nv = new HashMap<String, ArrayList<Object>>();
    int x = 0;
    double tfa = 100.00;   // start value to prove action performed
    String[] dataList =  new String[0];
    ArrayList<String[]> itemList= new ArrayList<String[]>();  //contains a list of all Items osn shopping List.




    double GetBudget(double tfa) {   //get the Total Budget Size
        //Scanner entry = new Scanner(System.in);

        System.out.println(" Enter Total fund Available: $");
        tfa = entry.nextDouble();
        System.out.println("My Budget: $" + tfa);
        return tfa;
    }

    HashMap GetShoppingList() {
        StringBuilder sbWrite = new StringBuilder();
        int x = 0;
        System.out.println("Create a Shopping List");
        do {
            String[] itemX = entry.nextLine().split("\\s+");
            itemList.add(itemX);
            try {
                BufferedWriter brWrite = new BufferedWriter(new FileWriter(cogFile)); //Opens upp the Write to File function
                if (!(itemX.equals(""))) {    //if the scanner entry is NOT empty, continue, else catch exception
                    cogs.put(itemX[0], itemX[1]); // costX);  //Store the data in a hashmap

                    //write the data to File
                        sbWrite.append(itemX[0]);
                        sbWrite.append(", $");                //Data to be written to the File
                        sbWrite.append(itemX[1]);
                        sbWrite.append("\n");
                        brWrite.write(sbWrite.toString());  //Write the String to the File
                        brWrite.close();                //close the File after writing

                }
                x++;

            } catch (Exception e) {
                System.out.println(" Shopping List" + cogs);
                break;
            } finally {
                System.out.println(cogs);
            }
        } while (cogs.size() < 5 || entry.nextLine().equals(""));    //Limits the item list to 10 Items
        //System.out.println("Shopping List: " + cogs);
        return cogs;
    }


    public HashMap<String, Integer> IndexShoppingList() {
        StringBuilder sbWrite = new StringBuilder();
        Scanner entry = new Scanner(System.in);
        String line;
        String tempList="";
        System.out.println("Rate by Necessity Index (Must have): 1-5 : ");
        try {
            BufferedReader brRead = new BufferedReader(new FileReader(cogFile)); //opens up the read from Fil Function
            //BufferedWriter brWrite = new BufferedWriter(new FileWriter(cogFile)); //Opens up the Write to File function
            while ((line = brRead.readLine()) != null) {
                System.out.println(x + ". " + line);
                int nvIndex = entry.nextInt();    //collect the Necessity Index Value of Items
                nv.put(line, nvIndex);
                String xtemplist = tempList;
                tempList = (xtemplist + line + "=" + nvIndex + "\n") ; //Store the data to temporary storage
                x++;
            }
            System.out.println(" Temporary hold list data: " + tempList);

            try{
                BufferedWriter brWrite = new BufferedWriter(new FileWriter(cogFile)); //Opens up the Write to File function
                sbWrite.append(tempList);
                sbWrite.append("\n");                //Data to be written to the File

                brWrite.write(sbWrite.toString());  //Write the String to the File
                brWrite.close();                //close the File after writing

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Current List:"  + nv);
        return nv;
    }
}
