package org.snakeInc.snake;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.snakeinc.snake.model.Apple;
import org.snakeinc.snake.model.Snake;

public class SnakeTest {

    @Test
    public void snakeEatApples_ReturnsCorrectBodySize() {
        Snake snake = new Snake();
        Apple apple = new Apple();
        snake.eat(apple);
        Assertions.assertEquals(2, snake.getBody().size());

    }

    @Test void snakeMovesUp_ReturnCorrectHead() {
        Snake snake = new Snake();
        snake.move('U');
        Assertions.assertEquals(5, snake.getHead().getX());
        Assertions.assertEquals(4, snake.getHead().getY());
        // Assertions.assertEquals(5, snake.getHead().getY());
    }

}
