package gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

import static backend.FilmResponses.getResponseResult;
import static backend.WriteToFile.arrayFilmNames;

public class AppGUI {
    private JButton infoButton;
    public JPanel panelMain;
    private JButton downloadFilmButton;
    private JProgressBar progressBar;
    private JComboBox<String> comboBox;
    private JTextArea resultTextArea;
    private JButton aboutButton;
    private JButton pieButton;

    public AppGUI() {
        panelMain.setPreferredSize(new Dimension(530, 300));
        progressBar.setPreferredSize(new Dimension(530, 20));
        comboBox.setEditable(true);
        comboBox.setModel(new DefaultComboBoxModel<>(arrayFilmNames));
        AutoCompletion.enable(comboBox);
        infoButton.addActionListener(actionEvent ->
                JOptionPane.showMessageDialog(panelMain, "AnalyzerFilm - програма для аналізу тональності коментарів до фільмів\n" +
                        "-> Для отримання результату, оберіть бажаний фільм у випадаючому списку \n" +
                        "-> Для аналізу нового фільму завантажте txt файл з відгуками про фільм", "Інформація", 1));
        downloadFilmButton.addActionListener(actionEvent -> {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();
            new GUIWorker(progressBar, file).execute();
            comboBox.updateUI();
        });
        comboBox.addActionListener(actionEvent -> {
            resultTextArea.setText("");
            String item = (String) comboBox.getSelectedItem();
            try {
                String resultLine = getResponseResult("workFiles/Result.txt", item);
                resultTextArea.append(resultLine);
                new PieChart(item,resultLine);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        });
        aboutButton.addActionListener(actionEvent ->
                JOptionPane.showMessageDialog(panelMain, "Тема магістерської кваліфікаційної роботи: ЛІНГВОПРАГМАТИЧНІ ОСОБЛИВОСТІ ДИСКУРСУ СОЦІАЛЬНИХ МЕРЕЖ НА ОСНОВІ ВІДГУКІВ ДО ФІЛЬМІВ\n" +
                "Студентка групи ХХХХ - YY\n" +
                "XXXXXX YYYY\n" +
                "Керівник: \n" +
                "к.ф.н., доц. XXXXXX X.Y.\n" +
                "Консультант:\n" +
                "Ст. викладач кафедри XXXXXX\n" +
                "Дата створення:\n" +
                "лютий, 2019", "Про програму", 1));
    }

}
