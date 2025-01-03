package org.snakeinc.snake.model;
import java.awt.Color;
import java.awt.Graphics;


public class BoaConstrictor extends Snake {

    public BoaConstrictor() {
        super();
    }

    @Override
    public void eat(Apple apple) {
        body.add(apple.getPosition());
        body.add(apple.getPosition());
        body.add(apple.getPosition());
    }
       public void eat(Brocoli brocoli) {
       
    }
      public void draw(Graphics g) {
        for (Tile t : body) {
            g.setColor(Color.BLUE);
            t.drawRectangle(g);
        }
    }
}

