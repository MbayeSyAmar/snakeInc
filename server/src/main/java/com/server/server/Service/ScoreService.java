package com.server.server.Service;

import com.server.server.Entity.Score;
import com.server.server.Repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

// import java.util.stream.Collectors;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    public void saveScore(String snake, int score) {
        if (score < 0 || (!snake.equals("snake") && !snake.equals("anaconda") && !snake.equals("boaconstrictor"))) {
            throw new IllegalArgumentException("Invalid score or snake type");
        }

        Score newScore = new Score();
        newScore.setSnake(snake);
        newScore.setScore(score);
        newScore.setDate(LocalDate.now());
        scoreRepository.save(newScore);
    }

    public List<Score> getScoresBySnake(String snake) {
        return scoreRepository.findBySnake(snake);
    }
 
public List<Map<String, Object>> getStatistics() {
    return scoreRepository.findAll().stream()
        .collect(Collectors.groupingBy(Score::getSnake))
        .entrySet().stream()
        .map(entry -> {
            String snake = entry.getKey();
            List<Score> scores = entry.getValue();

            int min = scores.stream().mapToInt(Score::getScore).min().orElse(0);
            int max = scores.stream().mapToInt(Score::getScore).max().orElse(0);
            double avg = scores.stream().mapToInt(Score::getScore).average().orElse(0);

            Map<String, Object> stats = new HashMap<>();
            stats.put("snake", snake);
            stats.put("min", min);
            stats.put("max", max);
            stats.put("average", avg);

            return stats;
        }).collect(Collectors.toList());
}

    
}

