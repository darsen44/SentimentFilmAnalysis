package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class WordAnalyzer {

    public static boolean fileContainsWord(String fileName, String word) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName))).contains(word.toLowerCase());
    }

    public static int positiveOrNegative(String s) throws IOException {
        s = s.replaceAll("[-+.^:,]", "").toLowerCase();
        if (fileContainsWord("Positive.txt", s)) return 1;
        else if (fileContainsWord("Negative.txt", s)) return -1;
        s = lemmatizer(s);
        if ("".equals(s)) return 0;
        if (fileContainsWord("Positive.txt", s)) return 1;
        else if (fileContainsWord("Negative.txt", s)) return -1;
        return 0;
    }

    private static String lemmatizer(String s) throws FileNotFoundException {
        Scanner in = new Scanner(new File("Lemmatizer.txt"));
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.contains(s)) {
                line = line.replaceAll(s, "");
                return line.trim();
            }
        }
        return "";
    }

}
