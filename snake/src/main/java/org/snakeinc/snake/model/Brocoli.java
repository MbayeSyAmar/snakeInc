package org.snakeinc.snake.model;

import java.awt.Color;
import java.awt.Graphics;
import org.snakeinc.snake.AlimentEater;


public class Brocoli extends Aliment {
    public Brocoli() {
        super();
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        position.drawOval(g);
    }
    public void beEatenby(AlimentEater alimentEater) {
        alimentEater.eat(this);
    }
}
