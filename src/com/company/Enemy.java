package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Enemy {
    int x;
    double y;
    int hp;
    List<Enemy> enemies = new ArrayList<>();

    public Enemy() {
    }

    public Enemy(int x, double y) {
        this.x = x;
        this.y = y;
        this.hp = 100;
    }

    public Enemy(int x, double y, int hp) {
        this.x = x;
        this.y = y;
        this.hp = hp;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }
    public int getYint (){
        return (int)y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}