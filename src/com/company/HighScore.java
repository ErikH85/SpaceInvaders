package com.company;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class HighScore {

    File file = new File(save_file);
    if (file.exists()) {
        PrintWriter a = new PrintWriter(new FileOutputStream(save_file, true));
        a.append("test--test--test");
    }
}
