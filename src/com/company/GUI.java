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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.googlecode.lanterna.TextColor.ANSI.GREEN;
import static com.googlecode.lanterna.TextColor.ANSI.RED;
import static com.googlecode.lanterna.TextColor.ANSI.YELLOW;

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

        while (runs) {
            screen.clear();

            for (Enemy e: enemies) {
                if(e.remove){
                    enemiesToRemove.add(e);
                }
            }

            TextGraphics tGraphics = screen.newTextGraphics();
            tGraphics.putString(70, 0, "\u25B2 x" + player.getLife());
            tGraphics.putString(70, 1, "HP:" + player.getHp());
            tGraphics.putString(70, 2, "Score:" + player.getScore());

            List<Enemy> enemiesRemoveBottom = new ArrayList<>();

            if(r.nextInt(1000) > setlvl) {
                enemies.add(new Enemy(r.nextInt(size.getColumns() - 2)+1, 1));
            }

            for (Enemy f : enemies) {
                if (f.y <= size.getRows()) {
                    f.y += 0.04;
                }
                f.changeState();
                TextCharacter enemy = new TextCharacter(f.getShape()).withForegroundColor(new TextColor.RGB(255, 0, 0));
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
                    tGraph.putString(34, 14, "2. ");
                    tGraph.putString(34, 15, "3. ");
                    tGraph.putString(34, 17, "YOUR SCORE:");
                    tGraph.putString(34, 18, "" + player.getScore());
                    runs = false;
                }
            }

            enemies.removeAll(enemiesRemoveBottom);
            keyPressed = terminal.pollInput();
            if (keyPressed != null) {
                if (keyPressed.getKeyType() == KeyType.ArrowRight) {
                    player.setX(player.getX() + 1);
                } else if (keyPressed.getKeyType() == KeyType.ArrowLeft) {
                    player.setX(player.getX() - 1);
                } else if (keyPressed.getKeyType() == KeyType.Character && keyPressed.getCharacter() == ' ') {
                    bullets.add(new Attack(player.getX(), player.getY() - 1));
                }
            }

            TextCharacter playerChar = new TextCharacter('\u25B2').withForegroundColor(GREEN);
            screen.setCharacter(player.getX(), player.getY(), playerChar);


            List<Attack> bulletsToRemove = new ArrayList<>();

            for (Attack bullet : bullets) {
                bullet.setPosy(bullet.getPosy() - 1);
                screen.setCharacter(bullet.getPosx(), bullet.getPosy(), new TextCharacter(bullet.getBullet()).withForegroundColor(YELLOW));
                for (Enemy e : enemies) {
                    if (bullet.getPosx() == e.getX() && bullet.getPosy() == e.getYint()) {
                        e.setState(EnemyState.HIT);
                        System.out.println("träff: " + e.isNormal() + e.isHit() + e.isDestroyed());
                        //enemiesToRemove.add(e);
                        bulletsToRemove.add(bullet);


                        player.setScore(player.getScore() +10);
                        if (player.getScore()% 200==0) {
                            setlvl -= 30;
                        }
                    }
                }
                enemies.removeAll(enemiesToRemove);

            }
            bullets.removeAll(bulletsToRemove);
            /*{  List<Player> ToRemove = new ArrayList<>();
            /*{

            Enemy ska ha bullets.
            Enemy ska kunna skjuta bullets.
            Enemy ska skjuta en åt gången med lagom intervall.
            Vilken enemy som ska skjuta avgörs genom random rand.
            Enemys bullets ska vara RED.
            Om enemy bullets pos = player = player (!alive)


            List<Player> ToRemove = new ArrayList<>();

                for (Attack bullet: bullets) {
                    bullet.setYint(bullet.getYint()-1);
                    screen.setCharacter(bullet.getX(),bullet.getYint(), new TextCharacter(bullet.getBullet()).withForegroundColor(RED));
                    for(Player p: player) {
                        if(bullet.getX() == p.getX() && bullet.getYint() == p.getPosy()) {
                            playerToRemove.add(p);
                            enemies.setScore(enemies.getScore() +3);
            }
                    }
                    player.removeAll(playerToRemove);
                }*/

            screen.refresh();
            TimeUnit.MILLISECONDS.sleep(33);
        }

        hs.printToFile(player.getScore());

        /* screen.clear();
        Attack bullet = new Attack(int posx, int posy);
        KeyStroke keyPressed;

        keyPressed = terminal.pollInput();

        if {
            screen.setpo();*/
    }
}