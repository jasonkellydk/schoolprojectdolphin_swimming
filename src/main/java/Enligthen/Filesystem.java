package Enligthen;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

/*
 * This is the enlighten Filesystem class. It may be used to do CRUD operations on the filesystem.
 * Made with <3 by Jason kelly.
 */
public class Filesystem {

    public boolean Create(String content ,String workingDir, String fileName) throws IOException {

        File file = new File(workingDir);
        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(),true); // true for at appende ?
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        if (!file.exists()) {
            file.createNewFile();
        }

        bufferedWriter.append(content);
        bufferedWriter.close();

        try {
            Scanner input = new Scanner(new File(workingDir));
            while (input.hasNextLine()) {
                System.out.println(input.nextLine());
            }
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }


    public String Read(String filename) throws IOException{

        String content = null;


        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile()); //for ex foo.txt
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader !=null){reader.close();}
        }
        return content;

    }



    public Properties Config() throws IOException{

        ClassLoader classLoader = getClass().getClassLoader();
        FileInputStream input = new FileInputStream(classLoader.getResource(".env").getFile()); //get the .env file

        Properties prop = new Properties();
        // load a properties file
        prop.load(input);

        // get the property value and print it out
        return prop;
    }
}
