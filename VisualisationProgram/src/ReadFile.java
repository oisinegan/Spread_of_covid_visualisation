import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadFile {
    public static String data;
    public static int worldSize = 0;
    public static int generations = 0;
    //Store worlds from file as strings
    public static ArrayList<String> worlds = new ArrayList<String>();
    public static String world ="";
    
    public void readFile()  {
        try {
            File myObj = new File("./world/Worlds.txt");
            
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                //If data = space then add one generation
                if(data.length()==0) {
                	generations++;
                	//Add world to list
                	worlds.add(world);
                	//Set world String back to default
                	world = "";
                }
                else {
                	//Get world size
                	worldSize = data.length();
                	//Keep adding lines to string until reach blank space(End of file)
                	world = world + data;
                } 
            }
            //2 white spaces at bottom of file, take away one of them 
            generations = generations -1;
           
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

 	
}




