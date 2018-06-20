package com.company;

public class Player {
    private int x, y, life, hp, score;
    private boolean playerAlive;

    public Player(int x, int y, int life, int hp, int score) {
        this.x = x;
        this.y = y;
        this.life = life;
        this.hp = hp;
        this.score = score;

    }

    public int getX() {
        return x;
    }

    public void setX(int x){
        if(x > 0 && x < 79){
        this.x = x;
        }
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

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isPlayerAlive() {
        return playerAlive;
    }

    public void setPlayerAlive(boolean playerAlive) {
        this.playerAlive = playerAlive;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}