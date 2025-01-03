package org.snakeinc.snake.model;
import java.awt.Color;
import java.awt.Graphics;


public class Anaconda extends Snake {

    public Anaconda() {
        super();
    }

    @Override
    public void eat(Apple apple) {
        body.add(apple.getPosition());
        body.add(apple.getPosition());
    }
       public void eat(Brocoli brocoli) {
        body.removeLast();
    }
      public void draw(Graphics g) {
        for (Tile t : body) {
            g.setColor(Color.GRAY);
            t.drawRectangle(g);
        }
    }
}

