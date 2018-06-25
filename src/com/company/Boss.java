package com.company;

import com.googlecode.lanterna.TextColor;

public class Boss extends Enemy {


    public Boss(int x, double y, int hp) {
        super(x, y, hp);
        super.setShape('ʘ');
    }

    @Override
    public void move() {
        super.setY(super.getY() + 0.1);
    }


    /*
    private int x;
    private int y;
    private int hp;

    private boolean normal;
    private boolean hit;
    private boolean destroyed;

    public Boss(int x, int y, int hp){
        this.x = x;
        this.y = y;
        this.hp = hp;
    }
    public void changeState(){

    }
    */
}
