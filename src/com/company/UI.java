package com.company;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public interface UI {

    void runGUI() throws IOException, InterruptedException, LineUnavailableException, UnsupportedAudioFileException;
}