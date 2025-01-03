package org.snakeinc.snake.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import lombok.Getter;
import org.snakeinc.snake.AlimentEater;

import org.snakeinc.snake.GamePanel;

public abstract class Aliment {

    @Getter
    protected Tile position;
    private final Random random;

    public Aliment() {
        random = new Random();
        updateLocation();
    }

    public void updateLocation() {
        position = new Tile(random.nextInt(0, (GamePanel.GAME_WIDTH / GamePanel.TILE_SIZE)),
                random.nextInt(0, (GamePanel.GAME_HEIGHT / GamePanel.TILE_SIZE)));

    }
    
    public abstract void beEatenby(AlimentEater alimentEater);
      

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        position.drawOval(g);
    }

}
