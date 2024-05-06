package Projekt;

import javax.swing.*;
import java.awt.*;

public class GameArea extends JPanel {

    private int rows, cols;
    private int cellSize;

    private Brick block;

    public Brick getBlock() {
        return block;
    }

    public void setBlock(Brick block) {
        this.block = block;
    }

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

        spawner();
    }
    public void spawner()
    {
        this.block = new Brick(new int[][]{{1, 0}, {1, 0}, {1, 1}}, Color.red, 0, 0);
        block.spwanBlock(cols);
    }

    public void force()
    {
        if (!bTouchedGrass()) return;

        this.block.down();
        repaint();
    }

    public boolean bTouchedGrass()
    {
        if (block.getGrassLevel() == rows)
            return false;
        else
            return true;
    }

    public void drawBlock(Graphics g,  Brick block)
    {
        int x, y;

        for (int row = 0; row < block.getBlockShape().length; row++) {
            for (int col = 0; col < block.getBlockShape()[0].length; col++) {
                if(block.getBlockShape()[row][col] == 1)
                {
                    x = getCellSize() * (block.getX() + col);
                    y = getCellSize() * (block.getY() + row);

                    g.setColor(block.getColor());
                    g.fillRect(x,  y, this.getCellSize(), this.getCellSize());
                    g.setColor(Color.black);
                    g.drawRect(x, y, this.getCellSize(), this.getCellSize()); // draw area
                    repaint();
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);


        drawBlock(g, this.getBlock());

        /*for (int y = 0; y < this.getRows(); y++) {
            for (int x = 0; x < this.getCols(); x++) {
                g.drawRect(x * this.getCellSize(), y * this.getCellSize(), this.getCellSize(), this.getCellSize());
            }
        }*/
    }
}
