package Projekt;

import Blocks.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Main extends JFrame {

    static GUI gui;
    static Connection conn;
    static GameArea gameArea;
    static BrickArea brickArea;
    static GameThread thread;
    static AudioManager audio;
    static int score, level, lines;
    static boolean bMusic, bGameOn;
    static Brick[] bricks;
    static Brick nxtBlock;

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

    public static void addLevel(int added) {
        Main.level += added;
    }

    public static int getScore() {
        return Main.score;
    }

    public static void setScore(int score) {
        Main.score = score;
    }

    public static void addScore(int added) {
        Main.score += added;
    }

    public static void addLines(int added) {
        Main.lines += added;
    }

    public static void main(String[] args) throws InterruptedException {
        new Main();
    }

    public Main() throws InterruptedException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(
                    "jdbc:sqlserver://labyjava.database.windows.net:1433;database=laboratoria;user=adminJava@labyjava;password=ABcd12#$;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;"
            );
            System.out.println("Connection established successfully.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC Driver not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed: " + e.getMessage());
        }

        Thread.sleep(2000);

        gui = new GUI();
        gameArea = gui.getGamePanel();
        brickArea = gui.getNextBrickPanel();

        bricks = new Brick[]{new LBlock(), new JBlock(), new ZBlock(), new SBlock(), new TBlock(), new OBlock()};
        Random random = new Random();
        nxtBlock = bricks[random.nextInt(bricks.length)];
        gameArea.setNxtBlock(nxtBlock);

        gui.pointsTextField.setText(String.valueOf("Score: " + Main.getScore()));
        gui.levelTextField.setText(String.valueOf("Level: " + Main.getLevel()));
        gui.lineTextField.setText(String.valueOf("Lines: " + Main.getLines()));

        audio = new AudioManager();

        bMusic = true;
        bGameOn = true;

        Main.score = 0;
        Main.level = 1;
        Main.lines = 0;

        Controls();

        GameStart();

        if (bGameOn) {
            audio.playBgMusic();
        }
    }

    public static void GameOver() {
        bGameOn = false;

        if (bMusic) {
            audio.stopBgMusic();
        }

        String name = JOptionPane.showInputDialog("Name/Imie:");
        int score = Main.score;

        try {
            String sql = "INSERT INTO Scores (name, score) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setInt(2, score);
                pstmt.executeUpdate();

                System.out.println("Data inserted successfully.");
                gui.populateTableModel();
                clearAndRestart();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void clearAndRestart() {
        gameArea.removeFallenOnes();
        Main.score = 0;
        Main.level = 1;
        Main.lines = 0;

        if (bMusic) {
            audio.playBgMusic();
        }
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

    public void GameStart() {
        thread = new GameThread(gameArea);
        thread.start();
    }
}
