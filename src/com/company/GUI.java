package com.company;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.googlecode.lanterna.TextColor.ANSI.*;

public class GUI implements UI {

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
        Player player = new Player(40, 23, 10, 100, 0);
        List<Attack> bullets = new ArrayList<>();
        List<EnemyAttack> ebullets = new ArrayList<>();

        KeyStroke keyPressed;
        TerminalSize size = terminal.getTerminalSize();

        List<Enemy> enemies = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 8; i++) {
            enemies.add(new Enemy(r.nextInt(size.getColumns()-2)+1,1));
        }

        boolean runs = true;
        int setlvl = 970;

        List<Enemy> enemiesToRemove = new ArrayList<>();

        HighScore hs = new HighScore();
        hs.chooseFile();
        player.setName(JOptionPane.showInputDialog("Vad heter du?"));

        while (runs) {
            screen.clear();

            TextGraphics tGraphics = screen.newTextGraphics();
            tGraphics.putString(70, 0, "\u25B2 x" + player.getLife());
            tGraphics.putString(70, 1, "HP:" + player.getHp());
            tGraphics.putString(70, 2, "Score:" + player.getScore());

            for (Enemy e: enemies) {
                if(e.remove){
                    enemiesToRemove.add(e);
                }
            }
            for (Enemy f : enemies) {
                if (r.nextInt(1000) > 997) {
                    ebullets.add(new EnemyAttack(f.getX(),f.getYint() +1));
                }

            }
            for (EnemyAttack ebullet : ebullets) {
                ebullet.setPy(ebullet.getPy() + 1);
                screen.setCharacter(ebullet.getPx(), ebullet.getPy(), new TextCharacter(ebullet.getEbullet()).withForegroundColor(YELLOW));
                if (ebullet.getPx() == player.getX() && ebullet.getPy() == player.getY()) {
                    if (player.getHp() > 0) {
                        player.setHp(player.getHp() -10);
                    }
                }
                if (player.getHp() == 0) {
                    player.setLife(player.getLife() - 1);
                    player.setHp(player.getHp() +100);
                }
            }

            List<Enemy> enemiesRemoveBottom = new ArrayList<>();

            if(r.nextInt(1000) > setlvl) {
                enemies.add(new Enemy(r.nextInt(size.getColumns() - 2)+1, 1));
            }

            for (Enemy f : enemies) {
                if (f.y <= size.getRows()) {
                    f.y += 0.04;
                }
                f.changeState();
                TextCharacter enemy = new TextCharacter(f.getShape()).withForegroundColor(f.getColor());
                screen.setCharacter(f.getX(), f.getYint(), enemy);
                if (f.getYint() == 24 && player.getLife() > 0) {
                    enemiesRemoveBottom.add(f);
                    player.setLife(player.getLife() - 1);
                } else if (player.getLife() == 0) {
                    String highScore = hs.readFile();
                    screen.clear();
                    TextGraphics tGraph = screen.newTextGraphics();
                    tGraph.putString(35, 10, "Game Over");
                    tGraph.putString(34, 12, "HIGH SCORE:");
                    tGraph.putString(34, 13, "1. " + highScore);
                    tGraph.putString(34, 14, "2. " + highScore);
                    tGraph.putString(34, 15, "3. " + highScore);
                    tGraph.putString(34, 17, "YOUR SCORE:");
                    tGraph.putString(34, 18, "" + player.getScore());
                    runs = false;
                }
            }

            enemies.removeAll(enemiesRemoveBottom);
            keyPressed = terminal.pollInput();
            if (keyPressed != null) {
                if (keyPressed.getKeyType() == KeyType.ArrowRight && keyPressed.isCtrlDown()) {
                    player.setX(player.getX() + 5);
                } else if (keyPressed.getKeyType() == KeyType.ArrowLeft && keyPressed.isCtrlDown()) {
                    player.setX(player.getX() - 5);
                } else if (keyPressed.getKeyType() == KeyType.ArrowRight) {
                    player.setX(player.getX() + 1);
                } else if (keyPressed.getKeyType() == KeyType.ArrowLeft) {
                    player.setX(player.getX() - 1);
                } else if (keyPressed.getKeyType() == KeyType.Character && keyPressed.getCharacter() == ' ') {
                    bullets.add(new Attack(player.getX(), player.getY() - 1));
                } else if (keyPressed.getKeyType() == KeyType.Escape){
                    System.exit(0);
                }
            }

            TextCharacter playerChar = new TextCharacter('\u25B2').withForegroundColor(GREEN);
            screen.setCharacter(player.getX(), player.getY(), playerChar);


            List<Attack> bulletsToRemove = new ArrayList<>();

            for (Attack bullet : bullets) {
                bullet.setPosy(bullet.getPosy() - 1);
                TextColor textColor = new TextColor.RGB(r.nextInt(255),r.nextInt(255),r.nextInt(255));
                screen.setCharacter(bullet.getPosx(), bullet.getPosy(), new TextCharacter(bullet.getBullet()).withForegroundColor(textColor));
                for (Enemy e : enemies) {
                    if (bullet.getPosx() == e.getX() && bullet.getPosy() == e.getYint()) {
                        e.setState(EnemyState.HIT);
                        System.out.println("träff: " + e.isNormal() + e.isHit() + e.isDestroyed());
                        //enemiesToRemove.add(e);
                        bulletsToRemove.add(bullet);


                        player.setScore(player.getScore() +10);
                        if (player.getScore()% 200==0) {
                            setlvl -= 4;
                        }
                    }
                }
                enemies.removeAll(enemiesToRemove);

            }
            bullets.removeAll(bulletsToRemove);


            screen.refresh();
            TimeUnit.MILLISECONDS.sleep(33);
        }

        hs.printToFile(player.getScore(), player.getName());

    }
}