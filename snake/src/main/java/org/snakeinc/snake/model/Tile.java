package org.snakeinc.snake.model;

import java.awt.Graphics;
import java.util.Objects;
import lombok.Getter;
import org.snakeinc.snake.GamePanel;


@Getter
public class Tile {

    @Getter
    private int x;

    @Getter
    private int y;

    private int upperPixelX;
    private int upperPixelY;

    public Tile(int x, int y) {
        setX(x);
        setY(y);
    }

    public Tile copy() {
        return new Tile(this.x, this.y);
    }

    public void setX(int X) {
        this.x = X;
        upperPixelX = X * GamePanel.TILE_SIZE;
    }

    public void setY(int Y) {
        this.y = Y;
        upperPixelY = Y * GamePanel.TILE_SIZE;
    }

    public void drawRectangle(Graphics g) {
        g.fillRect(upperPixelX, upperPixelY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
    }

    public void drawOval(Graphics g) {
        g.fillOval(upperPixelX, upperPixelY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
    }

    public boolean isInsideGame() {
        // return (((x > 0) && (x < GamePanel.N_TILES_X)) && ((y > 0) && (y < GamePanel.N_TILES_Y)));
        return (((x >= 0) && (x < GamePanel.N_TILES_X)) && ((y >= 0) && (y < GamePanel.N_TILES_Y)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tile tile = (Tile) o;
        return x == tile.x && y == tile.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
