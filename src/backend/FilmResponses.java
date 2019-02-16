package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static backend.WordAnalyzer.positiveOrNegative;
import static backend.WriteToFile.writeToFile;

public class FilmResponses {

    private static double positiveResponse = 0;
    private static double negativeResponse = 0;
    private static double neutralResponse = 0;
    private static int count = 0;

    public static void semantic(File file) throws IOException {
        String s;
        Scanner in = new Scanner(new File(file.getPath()));
        while (in.hasNext()) {
            s = in.next();
            findResponse(s);
            if (positiveOrNegative(s) == 1) count++;
            else if (positiveOrNegative(s) == -1) count--;
        }
        writeToFile(("workFiles/Result.txt"),file.getName(), positiveResponse, negativeResponse,neutralResponse);
        positiveResponse = negativeResponse = neutralResponse = 0;
        in.close();
    }

    private static void findResponse(String s){
        if (s.equals("(с)")) {
            if (count > 0) positiveResponse++;
            else if (count < 0) negativeResponse++;
            else neutralResponse++;
            count = 0;
        }
    }

    public static String getResponseResult(String resultFile, String filmName) throws FileNotFoundException {
        Scanner in = new Scanner(new File(resultFile));
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.contains(filmName)) {
                line = line.replaceAll(".txt", "");
                return line;
            }
        }
        return "Have not responses of this Film";
    }
}