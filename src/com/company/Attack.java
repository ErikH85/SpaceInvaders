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
        System.out.print(posx + "" + posy);

    }






   /*posx = player.getX;
   posy = (player.getY)-1;
   System.out.print(posx,posy);


   (posx, posy);

   int move = posx, posy -1.





   /*

   char bullet = ‘\u05C0’

   Initiering:
   Bullet initieras på skärmen varje gång spelaren trycker på mellanslag.

   Initierings Pos:
   bullet x = (get.gamerpos.x),
   bullet y = (get.gamerpos.y)-1.
       System.out.print(bullet x, bullet y).


   Bullet Movement = bullet x, bullet y-1.


   If (träff):
       Bullet pos == Enemy Pos
           bullet U+05C0 = U+0489.
               System.out.print(bullet)

        If bullet = U+0489
           then bullet = U+0488.
               System.out.print (bullet)

           If bullet = U+0488
               System.out.print(“DEAD”.


   Else If (! träff):
       bullet pos = (get.gamerpos.x,  y, -1)
           bullet = dead.


    */

}