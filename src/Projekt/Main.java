package Projekt;

public class Main {

    GUI gui;
    GameArea gameArea;

    public static void main(String[] args)
    {
         new Main();
    }

    public Main()
    {
        gui = new GUI();
        gameArea = gui.getGamePanel();

        GameStart();
    }


    public void GameStart()
    {
        new GameThread(gameArea).start();
    }
}