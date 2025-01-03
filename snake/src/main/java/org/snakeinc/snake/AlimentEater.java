package org.snakeinc.snake;

import org.snakeinc.snake.model.Apple;
import org.snakeinc.snake.model.Brocoli;

public interface AlimentEater {
    void eat(Apple apple);
    void eat(Brocoli brocoli);
}