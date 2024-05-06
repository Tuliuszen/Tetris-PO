package Projekt;

public class GameThread extends Thread
{
    private GameArea gameArea;

    public GameArea getGameArea() {
        return gameArea;
    }

    public void setGameArea(GameArea gameArea) {
        this.gameArea = gameArea;
    }

    @Override
    public void run()
    {
        while(true)
        {


            try {
                getGameArea().force();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public GameThread(GameArea gameArea)
    {
        setGameArea(gameArea);
    }
}
