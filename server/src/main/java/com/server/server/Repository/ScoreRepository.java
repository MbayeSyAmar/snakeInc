package com.server.server.Repository;

import com.server.server.Entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findBySnake(String snake);
}
