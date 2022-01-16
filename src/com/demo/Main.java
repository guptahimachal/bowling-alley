package com.demo;

import com.demo.service.Application;

public class Main {

    public static void main(String[] args) {
        Application application = new Application();
        application.startGame();
        System.out.println("Game Ended");
    }
}
