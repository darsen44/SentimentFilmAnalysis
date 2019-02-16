import gui.AppGUI;

import javax.swing.*;

import static backend.WriteToFile.writeFilmNames;

public class App {
    public static void main(String[] args) {
        writeFilmNames("Result.txt");
        JFrame frame = new JFrame("Аналізатор тональності");
        frame.setContentPane(new AppGUI().panelMain);
        frame.setBounds(600,300,600,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
