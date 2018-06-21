package com.company;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class HighScore {

    String fileName;

    public HighScore() {
    }

    public void chooseFile() throws IOException {
        JFileChooser fc = new JFileChooser();
        int option = fc.showOpenDialog(null);
        fileName = fc.getSelectedFile().getAbsolutePath();

        if (option != JFileChooser.APPROVE_OPTION) {
            System.out.println("No file was chosen");
        }
    }
    public void printToFile(int score) throws IOException {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
        pw.println(score);
        pw.close();
    }
}
