package com.company;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class HighScore {

    private String fileName;
    private boolean scoreAdded = false;

    public boolean isScoreAdded() {
        return scoreAdded;
    }

    public void setScoreAdded(boolean scoreAdded) {
        this.scoreAdded = scoreAdded;
    }

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
    public void printToFile(int score, String name) throws IOException {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
        pw.println(name + " - " + score);
        pw.close();
    }

    public String readFile() throws IOException {
        Scanner sc = new Scanner(new File(fileName));

        String s = "";
        while(sc.hasNext()){
            s = sc.nextLine();
        }
        sc.close();
        return s;
    }

}
