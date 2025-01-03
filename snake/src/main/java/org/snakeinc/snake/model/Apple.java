package org.snakeinc.snake.model;

import java.awt.Color;
import java.awt.Graphics;
import org.snakeinc.snake.AlimentEater;

public class Apple extends Aliment {

    public Apple() {
        super();
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        position.drawOval(g);
    }
    @Override
    public void beEatenby(AlimentEater alimentEater) {
        alimentEater.eat(this);
    }

}
