package Projekt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Main extends JFrame{

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
                System.out.println("Right key pressed");
                gameArea.moveRight();
            }
        });

        am.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Left key pressed");
                gameArea.moveLeft();
            }
        });

        am.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Up key pressed");
                gameArea.rotate();
            }
        });

        am.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Down key pressed");
                gameArea.moveDown();
            }
        });
    }



    public void GameStart()
    {
        new GameThread(gameArea).start();
    }
}