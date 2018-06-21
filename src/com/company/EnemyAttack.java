package com.company;

import com.googlecode.lanterna.input.KeyStroke;

import java.awt.*;

public class EnemyAttack{

    char Ebullet= '\u058D';                           //assignar | till “bullet”

    private int px;
    private int py;


    public void setEbullet(char Ebullet) {
        this.Ebullet = Ebullet;
    }

    public void setPx() {
        this.px = px;
    }

    public void setPy(int py) {
        this.py = py;
    }

    public EnemyAttack(int px, int py) {             //constructor
        this.px = px;
        this.py = py;
    }

    public char getEbullet() {
        return Ebullet;
    }

    public int getPx() {
        return px;
    }

    public int getPy() {
        return py;
    }



}