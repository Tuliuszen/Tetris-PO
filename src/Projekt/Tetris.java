package Projekt;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Tetris {
    public Tetris() {
        JFrame frame = new JFrame("Three Column Layout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create the main panel with a GridLayout for 3 columns
        JPanel mainPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        frame.add(mainPanel);

        // First Column
        JPanel firstColumnPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        mainPanel.add(firstColumnPanel);

        // Logo (Row 1)
        ImageIcon logoIcon = createImageIcon("logo.png");
        if (logoIcon != null) {
            JLabel logoLabel = new JLabel(logoIcon);
            firstColumnPanel.add(logoLabel);
        } else {
            System.err.println("Could not load logo image.");
        }

        // Data from Table (Row 2)
        // Assuming you have some table data, you can display it here
        String[][] tableData = {{"1201"}, {"103"}};
        String[] columnNames = {"Ranking"};
        JTable table = new JTable(tableData, columnNames);
        firstColumnPanel.add(new JScrollPane(table));

        // Second Column
        JPanel secondColumnPanel = new JPanel();
        secondColumnPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mainPanel.add(secondColumnPanel);

        // ----- GAME AREA -----
        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(Color.BLACK);  // Set background color for game area
        gamePanel.setPreferredSize(new Dimension(300, 600));  // Set preferred size for game area
        secondColumnPanel.add(gamePanel);

        // ----- GAME AREA -----
        JPanel thirdColumnPanel = new JPanel(new GridLayout(6, 1, 0, 10));
        mainPanel.add(thirdColumnPanel);

        // Next Brick
        JPanel nextBrickPanel = new JPanel();
        nextBrickPanel.setBackground(Color.BLUE);  // Set background color for game area
        nextBrickPanel.setPreferredSize(new Dimension(100, 100));
        thirdColumnPanel.add(nextBrickPanel);

        // Text Fields
        JTextField pointsTextField = new JTextField();
        thirdColumnPanel.add(pointsTextField);

        JTextField lineTextField = new JTextField();
        thirdColumnPanel.add(lineTextField);

        JTextField levelTextField = new JTextField();
        thirdColumnPanel.add(levelTextField);

        JButton musicButton = new JButton("Music On/Off");
        thirdColumnPanel.add(musicButton);

        JButton startStopButton = new JButton("Start/Stop");
        thirdColumnPanel.add(startStopButton);

        // Display the frame
        frame.setVisible(true);
    }

    // Method to create ImageIcon from file
    // Method to create ImageIcon from file
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

    public static void main(String[] args) {
        new Tetris();
    }
}
