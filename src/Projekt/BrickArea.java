package Projekt;

import javax.swing.*;
import java.awt.*;

public class BrickArea extends JPanel {
    private int cols, rows;
    private int cellSize;
    private Brick block;

    public BrickArea(int cols) {
        this.setBounds(0, 0, 100, 100);

        this.cols = cols;
        this.cellSize = 10;
        this.rows = this.getBounds().height / this.cellSize;
        System.out.println(this.getBounds());
        //System.out.println(this.cols);
        this.setPreferredSize(new Dimension(cellSize * cols, cellSize * cols)); // Ensure the preferred size is correct
        this.setBackground(Color.BLACK);
    }

    public void setBlock(Brick block, int x, int y) {
        this.block = block;
        this.block.setX(12);
        this.block.setY(4);
        // Ensure block is centered within the BrickArea

        System.out.println("x = " + this.block.getX() + "y = " + this.block.getY());
        repaint(); // Request a repaint to show the new block
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (block != null) {
            drawBlock(g, block);
        }
    }

    private void drawBlock(Graphics g, Brick block) {
        int[][] shape = block.getBlockShape();
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] == 1) {
                    int x = cellSize * (block.getX() + col);
                    int y = cellSize * (block.getY() + row);
                    drawSquare(g, block.getColor(), x, y);
                }
            }
        }
    }

    private void drawSquare(Graphics g, Color color, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, cellSize, cellSize);
        g.setColor(Color.black);
        g.drawRect(x, y, cellSize, cellSize);
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
}
