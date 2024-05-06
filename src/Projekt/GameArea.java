package Projekt;

import javax.swing.*;
import java.awt.*;

public class GameArea extends JPanel {

    private int rows, cols;
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


    public GameArea(int columns)
    {
        //this.setPreferredSize(new Dimension(300, 550));
        this.setBounds(0, 0, 300, 550);
        //this.setBackground(Color.BLACK);

        setCols(columns);
        setCellSize(this.getBounds().width / this.cols);
        setRows(this.getBounds().height / this.cellSize);

    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        for (int y = 0; y < this.getRows(); y++) {
            for (int x = 0; x < this.getCols(); x++) {
                g.drawRect(x * this.getCellSize(), y * this.getCellSize(), this.getCellSize(), this.getCellSize());
            }
        }
    }
}
