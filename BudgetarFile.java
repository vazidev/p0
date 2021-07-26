import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.io.FileWriter;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//import org.supercsv.io.*;


public class BudgetarFile extends getData{

        File cogFile = new File("FileCogs.csv");
        String fileName;

        static {};

        String CreateDataFile(String fileName){
                try {
                        if (!(cogFile.exists())) {
                                cogFile.createNewFile();
                                fileName = cogFile.getName();
                               System.out.println(" New File Created: " + fileName);
                        }
                        else if(cogFile.exists()) System.out.println("File: " + fileName + " Already Exists");
                }catch (FileNotFoundException e){
                        System.out.println("File Not Found Error");
                }catch ( IOException e){
                        System.out.println(" Error encountered creating new File");
                }finally {
                        System.out.println("Will output the current values to File");
                }
                return fileName;

        }
        /**
        Boolean WriteToDataFile( Boolean aTrue, String filepath){
                StringWriter dataWriter = new StringWriter();
                HashMap cogZ = new getData().cogs;
                System.out.println("Current List: " + cogZ);

                try (ICsvListWriter listWriter = new CsvListWriter(output, CsvPreference.STANDARD_PREFERENCE)){
                        filepath = cogFile.getPath();
                        ICsvMapWriter csvListWriter = new CsvMapWriter();
                        dataWriter.write(String.valueOf(cogZ));
                        System.out.println(cogZ.size() + " Items of Data Successfully Written");
                }catch (Exception e){
                   System.out.println( "Error: Unable to Write to File");
                }
                return Boolean.TRUE;
        }


        HashMap<String, Double> GetDataFromFile(final String filepath){ ///Stack Overflow sample
                String filePath = cogFile.getName();
                Stream<String> lines = Files.lines(Paths.get(filePath));
                HashMap <String, Double> dataZ =  lines.map(line -> line.split(",")).collect(Collectors.toMap(line -> line[0], line -> line [1]));
                lines.close();
              //

        }
 **/
}