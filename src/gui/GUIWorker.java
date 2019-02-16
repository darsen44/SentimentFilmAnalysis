package gui;

import javax.swing.*;
import java.io.File;

import static backend.FilmResponses.semantic;

public class GUIWorker extends SwingWorker<Void, Void> {

    private File file;
    private JProgressBar progressBar;

     GUIWorker(JProgressBar progressBar, File file) {
        this.file = file;
        this.progressBar = progressBar;
        progressBar.setStringPainted(true);
        progressBar.setString("Опрацьовування відгуків триває");
        progressBar.setIndeterminate(true);
    }

    @Override
    protected Void doInBackground() throws Exception {
        semantic(file);
        return null;
    }

    @Override
    protected void done() {
        progressBar.setString("Опрацьовування завершено!");
        progressBar.setIndeterminate(false);
    }
}
