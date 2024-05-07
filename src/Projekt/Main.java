package Projekt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Main extends JFrame{

    GUI gui;
    GameArea gameArea;


    static int score, level, lines;

    public static int getLines() {
        return lines;
    }

    public static void setLines(int lines) {
        Main.lines = lines;
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        Main.level = level;
    }

    public static void addLevel(int added)
    {
        Main.level += added;
    }

    public static int getScore() {
        return Main.score;
    }

    public static void setScore(int score) {
        Main.score = score;
    }

    public static void addScore(int added)
    {
        Main.score += added;
    }


    public static void main(String[] args)
    {
         new Main();
    }

    public Main()
    {
        gui = new GUI();
        gameArea = gui.getGamePanel();

        addScore(0);
        //System.out.print(Main.score);

        Controls();

        GameStart();
    }

    public void Controls() {
        InputMap im = gameArea.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = gameArea.getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "right");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "left");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "up");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "down");

        am.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.moveRight();
            }
        });

        am.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.moveLeft();
            }
        });

        am.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.rotate();
            }
        });

        am.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.moveDown();
            }
        });
    }



    public void GameStart()
    {
        new GameThread(gameArea).start();
    }
}