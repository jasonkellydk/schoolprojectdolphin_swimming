package Enligthen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

}
