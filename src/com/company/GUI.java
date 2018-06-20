package com.company;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.googlecode.lanterna.TextColor.ANSI.GREEN;
import static com.googlecode.lanterna.TextColor.ANSI.YELLOW;

public class GUI implements UI{

    @Override
    public void runGUI() throws IOException, InterruptedException {
        Terminal terminal = null;
        try {
            terminal = new DefaultTerminalFactory().createTerminal();
        } catch (IOException e) {
            System.out.println("createTerminal error");
            e.printStackTrace();
        }
        Screen screen = null;
        try {
            screen = new TerminalScreen(terminal);
        } catch (IOException e) {
            System.out.println("terminalScreen error");
            e.printStackTrace();
        }
        try {
            screen.startScreen();
        } catch (IOException e) {
            System.out.println("startScreen error");
            e.printStackTrace();
        }

        screen.clear();
        Player player = new Player(40, 23, 3, 100, 0);
        List<Attack> bullets= new ArrayList<>();

        KeyStroke keyPressed;

        while(true){
            screen.clear();

            TextGraphics tGraphics = screen.newTextGraphics();
            tGraphics.putString(70, 0, "\u25B2 x" + player.getLife());
            tGraphics.putString(70, 1, "HP:" + player.getHp());
            tGraphics.putString(70, 2, "Score:" + player.getScore());

            keyPressed = terminal.pollInput();
            if (keyPressed != null) {
                System.out.println(keyPressed);
                if (keyPressed.getKeyType() == KeyType.ArrowRight) {
                    player.setX(player.getX() + 1);
                } else if (keyPressed.getKeyType() == KeyType.ArrowLeft) {
                    player.setX(player.getX() -1);
                }else if (keyPressed.getKeyType() == KeyType.Character && keyPressed.getCharacter() == ' '){
                    bullets.add(new Attack(player.getX(), player.getY()-1));
                    bullets.add(new Attack(player.getX(), player.getY()-2));
                }
            }

            TextCharacter playerChar = new TextCharacter('\u25B2').withForegroundColor(GREEN);
            screen.setCharacter(player.getX(), player.getY(), playerChar);

            for (Attack bullet: bullets) {
                bullet.setPosy(bullet.getPosy()-1);
                screen.setCharacter(bullet.getPosx(),bullet.getPosy(), new TextCharacter(bullet.getBullet()).withForegroundColor(YELLOW));
                /*for(Enemy e:enemies) {
                    if(bullet.getPosx() == e.getPosx() && bullet.getPosy() == e.getPosy) {
                        enemies.remove(e);
                    }
                }*/
            }

            screen.refresh();
            TimeUnit.MILLISECONDS.sleep(33);
        }


        /* screen.clear();
        Attack bullet = new Attack(int posx, int posy);
        KeyStroke keyPressed;

        keyPressed = terminal.pollInput();

        if {
            screen.setpo();*/
    }
}