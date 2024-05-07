package Projekt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class GUI extends JFrame{

    private JFrame frame;
    private JPanel mainPanel, firstColumnPanel, secondColumnPanel, thirdColumnPanel, nextBrickPanel;

    private GameArea gamePanel;
    private JTable table;
    static JTextField pointsTextField, lineTextField, levelTextField;
    private JButton musicButton;
    private JButton startStopButton;
    private ImageIcon logoIcon;


    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public void setFirstColumnPanel(JPanel firstColumnPanel) {
        this.firstColumnPanel = firstColumnPanel;
    }

    public void setSecondColumnPanel(JPanel secondColumnPanel) {
        this.secondColumnPanel = secondColumnPanel;
    }

    public void setThirdColumnPanel(JPanel thirdColumnPanel) {
        this.thirdColumnPanel = thirdColumnPanel;
    }

    public void setNextBrickPanel(JPanel nextBrickPanel) {
        this.nextBrickPanel = nextBrickPanel;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public static void setPointsTextField(JTextField pointsTextField) {
        pointsTextField = pointsTextField;
    }

    public void setLineTextField(JTextField lineTextField) {
        this.lineTextField = lineTextField;
    }

    public void setLevelTextField(JTextField levelTextField) {
        this.levelTextField = levelTextField;
    }
    public JFrame getFrame() {
        return frame;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JPanel getFirstColumnPanel() {
        return firstColumnPanel;
    }

    public JPanel getSecondColumnPanel() {
        return secondColumnPanel;
    }

    public GameArea getGamePanel() {
        return gamePanel;
    }

    public JPanel getThirdColumnPanel() {
        return thirdColumnPanel;
    }

    public JPanel getNextBrickPanel() {
        return nextBrickPanel;
    }

    public JTable getTable() {
        return table;
    }

    public JTextField getPointsTextField() {
        return pointsTextField;
    }

    public JTextField getLineTextField() {
        return lineTextField;
    }

    public JTextField getLevelTextField() {
        return levelTextField;
    }

    public JButton getMusicButton() {
        return musicButton;
    }

    public JButton getStartStopButton() {
        return startStopButton;
    }

    public ImageIcon getLogoIcon() {
        return logoIcon;
    }
    public GUI()
    {
        frame = new JFrame("Three Column Layout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        // Create the main panel with a GridLayout for 3 columns
        mainPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        frame.add(mainPanel);

        // First Column
        firstColumnPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        mainPanel.add(firstColumnPanel);

        // Logo (Row 1)
        logoIcon = createImageIcon("logo.png");
        if (logoIcon != null) {
            JLabel logoLabel = new JLabel(logoIcon);
            firstColumnPanel.add(logoLabel);
        } else {
            System.err.println("Could not load logo image.");
        }

        // Data from Table (Row 2)
        String[][] tableData = {{"1201"}, {"103"}};
        String[] columnNames = {"Ranking"};
        table = new JTable(tableData, columnNames);
        firstColumnPanel.add(new JScrollPane(table));

        // Second Column
        secondColumnPanel = new JPanel();
        secondColumnPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mainPanel.add(secondColumnPanel);

        // ----- GAME AREA -----
        gamePanel = new GameArea(10);
        gamePanel.setPreferredSize(new Dimension(300, 550));  // Set preferred size for game area
        //System.out.println(this.getSize());
        //System.out.println(gamePanel.getX());
        secondColumnPanel.add(gamePanel);

        // ----- GAME AREA -----
        thirdColumnPanel = new JPanel(new GridLayout(6, 1, 0, 10));
        mainPanel.add(thirdColumnPanel);

        // Next Brick
        nextBrickPanel = new JPanel();
        nextBrickPanel.setBackground(Color.BLUE);  // Set background color for game area
        nextBrickPanel.setPreferredSize(new Dimension(100, 100));
        thirdColumnPanel.add(nextBrickPanel);

        // Text Fields
        pointsTextField = new JTextField();
        pointsTextField.setText("0");
        thirdColumnPanel.add(pointsTextField);

        lineTextField = new JTextField();
        thirdColumnPanel.add(lineTextField);

        levelTextField = new JTextField();
        thirdColumnPanel.add(levelTextField);

        musicButton = new JButton("Music On/Off");
        thirdColumnPanel.add(musicButton);

        musicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Main.bMusic)
                    Main.audio.stopBgMusic();
                else
                    Main.audio.playBgMusic();


                System.out.println("Music button clicked!");
            }
        });

        startStopButton = new JButton("Start/Stop");
        thirdColumnPanel.add(startStopButton);

        startStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Main.bGameOn)
                {
                    Main.bGameOn = false;
                    Main.thread.stop();
                }
                else
                {
                    Main.bGameOn = true;
                    Main.thread = new GameThread(Main.gameArea); // Create a new thread
                    Main.thread.start();
                }


                System.out.println(Main.bGameOn);
            }
        });

        // Display the frame
        frame.setVisible(true);
    }

    protected ImageIcon createImageIcon(String path) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image image = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            return new ImageIcon(image);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
