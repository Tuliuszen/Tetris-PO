package Projekt;

import javax.swing.*;
import java.awt.*;

public class GameArea extends JPanel {

    private int rows;
    private int cols;
    private int cellSize;

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }


    public GameArea()
    {
        this.setPreferredSize(new Dimension(300, 600));
        this.setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        //g.fillRect(0,0,50,50);
    }
}
