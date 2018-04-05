import com.sun.corba.se.impl.encoding.BufferManagerWrite;


import java.awt.color.ICC_Profile;
import java.nio.*;
import java.util.*;
import java.io.*;
import java.lang.*;


public class a1ReadFile {
    public static Scanner path;

    public void openFile(){
    try {
        path = new Scanner(new File("doc/books.csv"));
    } catch(Exception e) {
        System.out.println("No such Files!");
        }
    }
    public List<List<String>> readFile() {
        List<List<String>> bookList = new ArrayList<>();
        while (path.hasNext()) {
            String line = path.nextLine();
            String[] value = line.split(",");
            bookList.add(Arrays.asList(value));
        }
        return bookList;
    }

    public static void saveFile(List<List<String>> data) {
        try{
        FileWriter file = new FileWriter("doc/books.csv");
            String newData = data.toString();
            String[] lines = newData.split("],");
            for(String line:lines){
                file.write(line.replace("[","").replace("]","").replace(" ","")+"\n");
            }
        file.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void closeFile(){
        path.close();
    }
}