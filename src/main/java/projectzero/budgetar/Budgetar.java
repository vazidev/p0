package projectzero.budgetar;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Logger;
import io.javalin.Javalin;
//import io.netty.buffer;
//import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Budgetar {
    public static void main(String[] args) {
        Logger LOG = Logger.getAnonymousLogger();
        LOG.info("Starting Application...");
        LOG.info("Initiating server....");
        File filename = new File("cogsFile.csv");
        new Server().initialize(8080);
        Path filepath = filename.toPath();
        GetData a = new GetData().myFile();  //Make sure the Data  File Exists : Create : Working
        new GetData().getFileContent();
        Scanner entry = new Scanner(System.in);
        Double tfa=0.00;

        int x;
        String option = "";
        do {
            //GetData tfa = new GetData().GetBudget();
            System.out.println("Current Budget:$" + tfa);
            new GetData().calcBalance();
            System.out.println("Select an Option"); //working
            System.out.println("1:View List , 2:New List, 3:Edit Budget, 4:Edit Ratings, 0:Exit");
                x = entry.nextInt();
                switch (x) {
                    case 1:
                        option  = "Current list";
                        System.out.println("Retrieving Shopping List.....");
                        new GetData().getFileContent().displayData();
                        //new GetData().streamHttp();
                        break;

                    case 2:
                        option = "New List";
                        GetData c  = new GetData().ShoppingList();
                        break;
                    case 3:
                        option = "Edit Budget";
                        tfa = new GetData().GetBudget().tfa;
                        break;
                    case 4:
                        option = "Index Items";
                        System.out.println(" Retrieving Items List...");
                        GetData e = new GetData().IndexList();
                        break;
                    case 5:
                        option = "Exit";
                        System.out.println("Exiting...");
                        break;
                    default:
                        option = "Invalid Entry";
                        System.out.println("Invalid Selection..");
                        break;
                }
            } while (x != 0 );
        }
}
