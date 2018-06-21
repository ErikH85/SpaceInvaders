

package com.company;


public class Enemy {
    int x;
    double y;
    int hp;
    boolean remove = false;
    char shape = '▼';


    private boolean normal;
    private boolean hit;
    private boolean destroyed;

    /*public enum AvailableStates {
        NORMAL,
        HIT,
        DESTROYED;
    }*/

    EnemyState state = EnemyState.NORMAL;

    public void changeState(){
        switch (state) {
            case NORMAL:
                state = EnemyState.NORMAL;
                normal = true;
                hit = false;
                destroyed = false;
                break;
            case HIT:
                state = EnemyState.DESTROYED;
                normal = false;
                hit = true;
                destroyed = false;
                shape = '҉';
                break;
            case DESTROYED:
                normal = false;
                hit = false;
                destroyed = true;
                remove = true;
                shape = '҈';
                break;
        }
    }

    /*
     U+0F36
     0480

     */

    public char getShape() {
        return this.shape;
    }

    public void setState(EnemyState state) {
        this.state = state;
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