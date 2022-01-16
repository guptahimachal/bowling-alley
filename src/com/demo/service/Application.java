package com.demo.service;

import com.demo.constants.BowlingConstants;
import com.demo.models.Player;
import com.demo.models.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Application {

    private List<Player> playerList;
    private Scanner scanner;

    public void startGame() {
        scanner = new Scanner(System.in);

        System.out.println("Enter number of Players : ");
        int numPlayers = scanner.nextInt();
        initializePlayers(numPlayers);

        for(int curRound = 1; curRound <= BowlingConstants.MAX_ROUND ; curRound++){
            System.out.printf("Round : %s%n%n", curRound);
            for(Player curPlayer : playerList){
                BowlingConstants.ScoreType scoreType = generateRound(curPlayer);

                if(curRound == BowlingConstants.MAX_ROUND &&
                        (scoreType == BowlingConstants.ScoreType.STRIKE || scoreType == BowlingConstants.ScoreType.SPARE) ){
                    BowlingConstants.ScoreType scoreType1 = generateRound(curPlayer);
                    if(scoreType1 == BowlingConstants.ScoreType.STRIKE){
                        generateRound(curPlayer);
                    }
                }

                showScoreBoard();
            }
        }
        
        showFinalScore();
    }

    private void showFinalScore() {
        System.out.println("\n\nFinal Score : ");
        Collections.sort(playerList);
        for(int i=0;i<playerList.size();i++){
            System.out.println(String.format("Player : %s , Score : %s",i+1, playerList.get(i).getTotalScore()));
        }
    }

    private void showScoreBoard() {
        System.out.println("\nScoreboard :");
        for(int i=0;i<playerList.size();i++){
            System.out.println(String.format("Player : %s -", i+1));
            for(Score curScore : playerList.get(i).getScores()){
                System.out.printf(String.format("%s, ",curScore));
            }
            System.out.println(String.format(" -> %s",playerList.get(i).getTotalScore()));
        }
    }

    private BowlingConstants.ScoreType generateRound(Player curPlayer) {
        Integer curScore = 0, x , curChance;
        Score curRoundScore = new Score();
        BowlingConstants.ScoreType scoreType = BowlingConstants.ScoreType.NORMAL;

        for(curChance = 1 ; curChance <= BowlingConstants.MAX_CHANCE ; curChance++){
            System.out.printf("Enter Score for Player : %s , Chance : %s%n", curPlayer.getId() , curChance);
            x = scanner.nextInt();
            curRoundScore.getChancePerRoundScore().set(curChance-1, x);

            curScore += x;

            if(x.equals(BowlingConstants.MAX_SCORE)){
                curScore += BowlingConstants.STRIKE_POINTS;
                scoreType = BowlingConstants.ScoreType.STRIKE;
                break;
            }

            if(curScore.equals(BowlingConstants.MAX_SCORE)) {
                curScore += BowlingConstants.SPARE_POINTS;
                scoreType = BowlingConstants.ScoreType.SPARE;
                break;
            }
        }

        for(int i=0;i<2; i++){
            if(curRoundScore.getChancePerRoundScore().get(i) != 0){
                System.out.printf("Player : %s  Score Chance : %s  -> %s%n", curPlayer.getId() , i+1 , curRoundScore.getChancePerRoundScore().get(i));
            }
        }

        curRoundScore.setTotalRoundScore(curScore);
        curPlayer.getScores().add(curRoundScore);
        curPlayer.setTotalScore(curPlayer.getTotalScore() + curRoundScore.getTotalRoundScore());
        return scoreType;
    }

    private void initializePlayers(int numPlayers) {
        playerList = new ArrayList<>();

        // Initialize All players with 0 score
        for(int i=1 ; i <= numPlayers ; i++){
            playerList.add(new Player(i,0));
        }
    }
}
