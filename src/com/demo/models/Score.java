package com.demo.models;

import com.demo.constants.BowlingConstants;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Score {

    Integer totalRoundScore;

    List<Integer> chancePerRoundScore;

    @Override
    public String toString() {
        // Case 1 - Strike (10,anything)
        if(Objects.equals(chancePerRoundScore.get(0), BowlingConstants.MAX_SCORE))
            return "{" + "X"           + "," +       "}";
        // Case 2 - Spare (a,b) s.t a+b = MAX_SCORE
        if(chancePerRoundScore.get(0) + chancePerRoundScore.get(1) == BowlingConstants.MAX_SCORE)
            return "{" + chancePerRoundScore.get(0) + "," + "/" + "}";
        // Case 3 - Any other score
        return "{" + chancePerRoundScore.get(0) + "," + chancePerRoundScore.get(0) + "}";
    }

    public Score() {
        this.totalRoundScore = 0;
        this.chancePerRoundScore = Arrays.asList(0,0);
    }

    public List<Integer> getChancePerRoundScore() {
        return chancePerRoundScore;
    }

    public void setChancePerRoundScore(List<Integer> chancePerRoundScore) {
        this.chancePerRoundScore = chancePerRoundScore;
    }

    public void setTotalRoundScore(Integer totalRoundScore) {
        this.totalRoundScore = totalRoundScore;
    }

    public Integer getTotalRoundScore() {
        return totalRoundScore;
    }
}
