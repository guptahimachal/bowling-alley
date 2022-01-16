package com.demo.models;

import java.util.ArrayList;
import java.util.List;

public class Player implements Comparable<Player>{

    private int id;
    private int totalScore;
    private List<Score> scores;

    public Player(int id, int totalScore) {
        this.id = id;
        this.totalScore = totalScore;
        scores = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    @Override
    public int compareTo(Player o) {
        if(o.getTotalScore() > totalScore)
            return 1;
        if(o.getTotalScore() < totalScore)
            return -1;
        return 0;
    }
}
