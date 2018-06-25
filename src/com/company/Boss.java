package com.company;

import com.googlecode.lanterna.TextColor;

public class Boss extends Enemy {


    public Boss(int x, double y, int hp) {
        super(x, y, hp);
        super.setShape('\u06DD');
        super.setColor(new TextColor.RGB(0,0,255));
    }

    @Override
    public void move() {
        super.setY(super.getY() + 0.1);
    }

    @Override
    public void changeState() {
        super.changeState();
        switch (state) {
            case NORMAL:
                super.setColor(new TextColor.RGB(0,0,250));
                break;
            case HIT:
                super.setColor(new TextColor.RGB(255,255,0));
                break;
            case DESTROYED:
                super.setColor(new TextColor.RGB(255,140,0));
                break;
        }
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
