package com.server.server.Controller;

import com.server.server.Entity.Score;
import com.server.server.Service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/scores")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    @PostMapping
    public void saveScore(@RequestBody Score score) {
        scoreService.saveScore(score.getSnake(), score.getScore());
    }

    @GetMapping
    public List<Score> getScores(@RequestParam String snake) {
        return scoreService.getScoresBySnake(snake);
    }
    @GetMapping("/stats")
    public List<Map<String, Object>> getStatistics() {
    return scoreService.getStatistics();
}

}
