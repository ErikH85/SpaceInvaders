package com.company;

import javafx.print.Collation;

import javax.swing.*;
import java.io.*;
import java.util.*;

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
    public List<String> sortList(List<String> hsList) {
        Collections.sort(hsList, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return extractInt(o1) - extractInt(o2);
            }

            int extractInt(String s) {
                String num = s.replaceAll("\\D", "");
                return num.isEmpty() ? 0 : Integer.parseInt(num);
            }
        });
        return hsList;
    }

    public void printToFile(List<String> hsList) throws IOException {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName, false)));
        for (int i = hsList.size() -1; i >= 0; i--) {
            pw.println(hsList.get(i));
        }
        pw.close();
    }

    public List<String> readFile() throws IOException {
        List<String> hsList = new ArrayList<>();
        Scanner sc = new Scanner(new File(fileName));
        String s;
        while(sc.hasNext()){
            s = sc.nextLine();
            hsList.add(s);
        }
        sc.close();

        return hsList;
    }
}
