package Projekt;

public class GameThread extends Thread
{
    private GameArea gameArea;

    GUI gui;

    public GameArea getGameArea() {
        return gameArea;
    }

    public void setGameArea(GameArea gameArea) {
        this.gameArea = gameArea;
    }

    @Override
    public void run()
    {
        while(Main.bGameOn) //GAME LOOP
        {
            System.out.println("test");
            gameArea.spawner();
            while(getGameArea().force())
            {
                try {
                    //Thread.sleep(200); //speed
                    //

                    //gui.setPointsTextField(Main.score.toString);
                    Thread.sleep(800 - (50 * Main.getLevel())); //speed
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public GameThread(GameArea gameArea)
    {
        setGameArea(gameArea);
    }
}
