package com.company;

import com.googlecode.lanterna.input.KeyStroke;

import java.awt.*;

public class Attack{

    char bullet= '\u05C0';                           //assignar | till “bullet”

    private int posx;
    private int posy;


    public void setBullet(char bullet) {
        this.bullet = bullet;
    }

    public void setPosx() {
        this.posx = posx;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public Attack(int posx, int posy) {             //constructor
        this.posx = posx;
        this.posy = posy;
    }

    public char getBullet() {
        return bullet;
    }

    public int getPosx() {
        return posx;
    }

    public int getPosy() {
        return posy;
    }

   

}