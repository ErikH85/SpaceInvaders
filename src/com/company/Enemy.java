

package com.company;


public class Enemy {
    int x;
    double y;
    int hp;


    private boolean normal;
    private boolean hit;
    private boolean destroyed;

    private enum AvailableStates{
        NORMAL,
        HIT,
        DESTROYED;
    }

    AvailableStates state = AvailableStates.NORMAL;

    public void changeState(){
        switch (state) {
            case NORMAL:
                state = AvailableStates.HIT;
                normal = true;
                hit = false;
                destroyed = false;
                break;
            case HIT:
                state = AvailableStates.DESTROYED;
                normal = false;
                hit = true;
                destroyed = false;
                break;
            case DESTROYED:
                normal = false;
                hit = false;
                destroyed = true;
                break;
        }
    }

    public boolean isNormal() {
        return normal;
    }
    public boolean isHit() {
        return hit;
    }

    public boolean isDestroyed() {
        return destroyed;
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