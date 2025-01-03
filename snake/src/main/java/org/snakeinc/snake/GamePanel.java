package org.snakeinc.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import org.snakeinc.snake.model.Aliment;
import org.snakeinc.snake.model.Apple;
import org.snakeinc.snake.model.Brocoli;
import org.snakeinc.snake.model.Snake;
import org.snakeinc.snake.model.Anaconda;
import org.snakeinc.snake.model.BoaConstrictor;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    public static final int TILE_SIZE = 20;
    public static final int N_TILES_X = 25;
    public static final int N_TILES_Y = 25;
    public static final int GAME_WIDTH = TILE_SIZE * N_TILES_X;
    public static final int GAME_HEIGHT = TILE_SIZE * N_TILES_Y;

    private Timer timer;
    private Snake snake;
    private Aliment aliment;
    private boolean running = false;
    private char direction = 'R';
    private int score = 0;

    public GamePanel() {
        this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);

        startGame();
    }

    private void startGame() {
        Random random = new Random();
        int choice = random.nextInt(3);

        switch (choice) {
            case 0:
                snake = new Snake();
                break;
            case 1:
                snake = new Anaconda();
                break;
            case 2:
                snake = new BoaConstrictor();
                break;
        }

        spawnNewAliment();

        timer = new Timer(100, this);
        timer.start();
        running = true;
        score = 0; // Réinitialise le score à 0 pour une nouvelle partie
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (running) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString("Score: " + score, 10, 20);
            aliment.draw(g);
            snake.draw(g);
        } else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (GAME_WIDTH - metrics.stringWidth("Game Over")) / 2, GAME_HEIGHT / 2);

        g.setColor(Color.WHITE);
        g.drawString("Press any key to restart", 
                     (GAME_WIDTH - metrics.stringWidth("Press any key to restart")) / 2, 
                     GAME_HEIGHT / 2 + 30);

        sendScoreToServer(); // Envoie le score au serveur après la fin de la partie
    }

    private void sendScoreToServer() {
        try {
            String snakeType = snake.getClass().getSimpleName().toLowerCase();
            URL url = new URL("http://localhost:8080/api/v1/scores");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonInput = String.format("{\"snake\":\"%s\", \"score\":%d}", snakeType, score);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInput.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Score sent successfully.");
            } else {
                System.out.println("Failed to send score: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkCollision() {
        if (snake.checkSelfCollision() || snake.checkWallCollision()) {
            running = false;
            timer.stop();
        }

        if (snake.getHead().equals(aliment.getPosition())) {
            if (aliment instanceof Brocoli) {
                if (snake instanceof Anaconda) {
                    score -= 1;
                } else if (snake instanceof BoaConstrictor) {
                    // Pas de changement de score
                } else {
                    score -= 2;
                }
            } else {
                score += 1;
            }

            aliment.beEatenby(snake);
            spawnNewAliment();
        }
    }

    private void spawnNewAliment() {
        Random random = new Random();
        int choice = random.nextInt(2);

        switch (choice) {
            case 0:
                aliment = new Brocoli();
                break;
            case 1:
                aliment = new Apple();
                break;
        }

        aliment.updateLocation();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            snake.move(direction);
            checkCollision();
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!running) {
            startGame(); // Redémarre la partie si le jeu est terminé
        } else {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Non utilisé
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Non utilisé
    }
}
