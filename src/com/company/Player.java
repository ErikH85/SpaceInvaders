package com.company;

public class Player {
    private int x, y, hp;
    private boolean playerAlive;

    public Player(int x, int y, int hp) {
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

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

}