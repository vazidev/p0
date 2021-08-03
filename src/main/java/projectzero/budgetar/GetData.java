package projectzero.budgetar;

import io.javalin.Javalin;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;
import io.javalin.Javalin;
//import io.netty.buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class GetData {
    //public ArrayList httpList;
    File filename = new File("cogsFile.csv");
    Path filepath = filename.toPath();
    Scanner entry = new Scanner(System.in);
    HashMap cogs = new HashMap<String, ArrayList<Object>>();
    int x = 0;
    double tfa = 0.00;   // Total Funds Available
    double sumCost=0.00;  //Total Cost of Goods
    double cashBalance =0.00;
    String[] dataList =  new String[0];
    ArrayList<String> itemList= new ArrayList<>();  //contains a list of all Items osn shopping List.
    ArrayList<String> httpList= new ArrayList<>();  //contains a list of all Items osn shopping List.

    //update the budget value
    public GetData  GetBudget() {   //get the Total Budget Size
        System.out.println(" Enter Total funds Available: $");
        this.tfa = entry.nextDouble();
        //System.out.println(" TFA Wallet Updated: $" + this.tfa);
        return this;
    }

    //Crete anew Shopping list and discard the old one.
    public GetData ShoppingList() {
        StringBuilder sbWrite = new StringBuilder();
        int x = 0;
        System.out.println("Create a Shopping List");
        ArrayList<String> itemList = this.itemList;
        do {
            String[] itemX = entry.nextLine().split("\\s+");
            try {
                if (!(itemX.equals(""))) {    //if the scanner entry is NOT empty, continue, else catch exception
                    cogs.put(itemX[0], itemX[1]); // costX);  //Store the data in a hashmap
                }
                x++;
            } catch (Exception e) {
                break;
            }
        } while (cogs.size() < 5 || entry.nextLine().equals(""));    //Limits the item list to 10 ItemsSystem.out.println(br);
        MapToArray(); //Convert Hashmap to Array List
        nvIndex(); //index the items
        System.out.println("Update Shopping list: Y/N)");
        if ((entry.nextLine().toString().toUpperCase(Locale.ROOT).equals("Y"))) {
            this.toFile(); //call the File Writer
        }
        return this;
    }

    //will edit the items in the File.
    public GetData IndexList() {
        String line;
        String tempList="";
        System.out.println("Rate by Necessity Index (Must have): 1-5 : ");
        try {
            BufferedReader brRead = new BufferedReader(new FileReader(this.filename)); //opens up the read from File Function
            while ((line = brRead.readLine()) != null) {
                System.out.println(line.split(",")[0]);
                int nvIndex = entry.nextInt();    //collect the Necessity Index Value of Items
                itemList.add(line.split(",")[0] + "," + line.split(",")[1] + "," + nvIndex);
                x++;
            }

        }catch (FileNotFoundException e){
                e.printStackTrace();
        }catch (IOException e){

                e.printStackTrace();
        }
        //System.out.println(itemList);
        System.out.println("Update Values? Y/N");

        if (entry.next().toString().toUpperCase(Locale.ROOT).equals("Y")) {
            //call the File Writer
            this.toFile();
        }else {
            System.out.println(" Something a gwan");
        }
        return this;
    }

    //Edits the Iteme on the itemList Array
    public GetData nvIndex(){
       for (int x=itemList.size()-1; x >= 0; x--) {
           System.out.println(itemList.get(x));
           itemList.add( itemList.get(x) + ","  + entry.nextInt());
       }
        return this;
    }

    //Verifies True if file Exists, or will create a new file
    public GetData myFile(){
        filename = this.filename;
        try {
            if (!(filename.exists())) {
                filename.createNewFile();
                //filepath = filepath.getName();
                System.out.println(" Success:... " + filename);
            }
            else if(filename.exists()) System.out.println("File: " + filename + " Already Exists");
        }catch (FileNotFoundException e){
            System.out.println("File Not Found Error:..." + filename);
        }catch ( IOException e){
            System.out.println(" Error encountered creating new File:...." + filename);
        }finally {
            System.out.println("");
        }
        return this;
    }

    //Writes Data to File
    public GetData toFile() {
        System.out.println("Writing to File");   // will be replaced with logger.
        String line;
        HashMap data = this.cogs;
        int x =0;
        try {
            BufferedWriter brWrite = new BufferedWriter(new FileWriter(filename)); //Opens up the Write to File function
            for (String t: itemList){
                brWrite.write(t + "\n");  //Write the String to the File
                x++;
            }
            brWrite.close();                //close the File after writing **/
        } catch (FileNotFoundException e) {
            //Error message out
        } catch (IOException e) {
            //Error Message out:  use logger
        }
        return this;
    }

    //display the contenct of the .csv file
    public GetData getFileContent(){
        Iterator<String> iter = itemList.iterator();
        try(Stream<String> lines = Files.lines(this.filepath)){
           lines.forEach(s -> itemList.add(s));
            //System.out.println(" Items Count:" + itemList.size());
            //itemList.forEach(l -> System.out.println((l.split(",")[0] + ", $" + l.split(",")[1])));
        }catch (IOException e){
            System.err.println("Failed to stream File");
        }
        return this;
    }

    //Data Display Method
    public GetData displayData(){
       itemList.forEach(s -> System.out.println((s.split(",")[0] + ", $" + s.split(",")[1])));
        return this;
    }


    public void calcBalance(){
        String line;
        try {
            itemList = getFileContent().itemList;
            sumCost = itemList.stream().mapToDouble(s -> Double.parseDouble(s.split(",")[1])).sum();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Total Est Cost: $" + sumCost);
        System.out.println("Account Balance after Purchases: $" + (cashBalance = this.tfa - sumCost));
    }

    public void streamHttp(){
        this.getFileContent();
        //final byte[] x = new GetData().stream();
        Javalin app = Javalin.create().start(8080);
        app.get("/", ctx -> ctx.result(String.valueOf("Success: Server Connection....\n" )));
    }


  /**  public GetData StreamSortedList(){  /// goal here is to stream a sorted list directly from the file.
        List<Item> tempList = new ArrayList<>();
        try (Stream<String> lines = Files.lines(filepath).spliterator(",")){
            sortedList(Comparator.comparing(lines[2] -> lines[2] ));
            collect(Collectors.toList(tempList));
            System.out.println(tempList);

        }catch (IOException e){
            e.printStackTrace();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
            return this;
    } **/

    void MapToArray() {  // fucntion will convert current Hashmap to Array List
       Iterator iCogs = cogs.entrySet().stream().iterator();;
       while (iCogs.hasNext()) {
           Map.Entry pairs = (Map.Entry)iCogs.next();
           itemList.add(pairs.getKey() + "," + pairs.getValue() + ",");
       }
       this.itemList = itemList;
       System.out.println(itemList);
    }



}

