package org.snakeinc.snake.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import org.snakeinc.snake.AlimentEater;

public class Snake implements AlimentEater {

    protected final ArrayList<Tile> body;

    public Snake() {
        body = new ArrayList<>();
        body.add(new Tile(5, 5)); // La tête du serpent
    }

    public ArrayList<Tile> getBody() {
        return body;
    }

    public Tile getHead() {
        return body.getFirst();
    }

    public void eat(Apple apple) {
        body.add(apple.getPosition());
    }
    public void eat(Brocoli brocoli) {
        body.removeLast();
        body.removeLast();
    }

    public void move(char direction) {
        Tile newHead = getHead().copy();

        switch (direction) {
            case 'U':
                newHead.setY(newHead.getY() - 1);
                break;
            case 'D':
                newHead.setY(newHead.getY() + 1);
                break;
            case 'L':
                newHead.setX(newHead.getX() - 1);
                break;
            case 'R':
                newHead.setX(newHead.getX() + 1);
                break;
        }

        body.addFirst(newHead);
        body.removeLast(); // Supprime le dernier segment pour simuler le déplacement
    }

    public void draw(Graphics g) {
        for (Tile t : body) {
            g.setColor(Color.GREEN);
            t.drawRectangle(g);
        }
    }
    public boolean checkSelfCollision() {
        for (int i = 1; i < body.size(); i++) {
            if (getHead().equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }
// 
    public boolean checkWallCollision() {
        return !getHead().isInsideGame();
    }

}


