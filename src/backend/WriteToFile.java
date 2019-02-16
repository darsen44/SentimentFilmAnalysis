package backend;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class WriteToFile {

    public static Vector<String> arrayFilmNames = new Vector<>();

    public static void writeToFile(String toFile,String fromFile, double positiveResponse, double negativeResponse,double neutralResponse) throws IOException {
        filmExist(toFile,fromFile);
        fromFile = fromFile.replaceAll(".txt","");
        try(FileWriter writer = new FileWriter(toFile,true)){
            writer.write(fromFile+ " : "+ "Позитивні:" + Math.round ((positiveResponse/(positiveResponse + negativeResponse + neutralResponse)) * 100) + "%"
                    + " Негативні:" + Math.round((negativeResponse/(positiveResponse + negativeResponse + neutralResponse)) * 100) + "%"+""
                    + " Нейтральні:" + Math.round((neutralResponse/(positiveResponse + negativeResponse + neutralResponse)) * 100) +"%\n" );
        }
        catch (IOException ex){
            System.out.println(ex.getMessage() + "Can't write to result backend");
        }
        arrayFilmNames.clear();
        writeFilmNames(toFile);
    }

    private static void filmExist(String resultFile,String filmName) throws IOException {
        filmName = filmName.replaceAll(".txt","");
        List<String> lines = FileUtils.readLines(new File(resultFile), (String) null);
        String finalFilmName = filmName;
        List<String> updatedLines = lines.stream().filter(s -> !s.contains(finalFilmName)).collect(Collectors.toList());
        FileUtils.writeLines(new File(resultFile), updatedLines, false);
    }
    public static void writeFilmNames(String fileName){
        try {
            String s;
            //InputStream inputStream = WriteToFile.class.getResourceAsStream(fileName);
            FileReader fr = new FileReader(fileName);
            Scanner in = new Scanner(fr);
            while(in.hasNextLine()){
               s = in.nextLine();
               s = s.split(":")[0];
               arrayFilmNames.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
