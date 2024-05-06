package Projekt;

import javax.swing.*;
import java.awt.*;

public class GameArea extends JPanel {

    private int rows, cols;
    private int cellSize;
    private Brick block;

    private Color[][] fallenBlocks; //Basically its a background for fallen blocks that are seen in game area


    public Color[][] getFallenBlocks() {
        return fallenBlocks;
    }

    public void setFallenBlocks(Color[][] fallenBlocks) {
        this.fallenBlocks = fallenBlocks;
    }

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

        this.setFallenBlocks(new Color[this.getCols()][this.getRows()]);
        fallenBlocks = new Color[getRows()][getCols()];
        fallenBlocks[0][0] = Color.BLUE;
        //spawner();
    }
    public void spawner()
    {
        this.block = new Brick(new int[][]{{1, 0}, {1, 0}, {1, 1}}, Color.red, 0, 0);
        block.spwanBlock(cols);
    }

    public boolean force()
    {
        if (!bTouchedGrass()) return false;

        this.block.down();
        repaint();

        return true;
    }

    public boolean bTouchedGrass()
    {
        if (this.block.getGrassLevel() == rows)
        {
            addToFallenOnes();
            return false;
        }
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

                    drawSquare(g, block.getColor(), x, y);
                }
            }
        }
    }

    public void drawBg(Graphics g)
    {
        Color color;
        for(int row = 0; row < getRows(); row++)
        {
            for(int col = 0; col < getCols(); col++)
            {
                color = getFallenBlocks()[row][col];

                if (color != null)
                {
                    int x = col * getCellSize();
                    int y = row * getCellSize();

                    drawSquare(g, color, x, y);
                }
            }
        }
    }

    public void addToFallenOnes()
    {
        int[][] shape = this.getBlock().getBlockShape();
        Color color = this.getBlock().getColor();

        for(int row = 0; row < this.getBlock().getHeight(); row++)
        {
            for(int col = 0; col < this.getBlock().getWidth(); col++)
            {
                if(shape[row][col] == 1)
                {
                    this.fallenBlocks[row + this.getBlock().getY()][col + this.getBlock().getX()] = color;
                }
            }
        }

    }


    public void drawSquare(Graphics g, Color color, int x, int y)
    {
        g.setColor(color);
        g.fillRect(x,y,this.getCellSize(), this.getCellSize());
        g.setColor(Color.black);
        g.drawRect(x, y, this.getCellSize(), this.getCellSize());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        drawBg(g);
        drawBlock(g, this.getBlock());

        /*for (int y = 0; y < this.getRows(); y++) {
            for (int x = 0; x < this.getCols(); x++) {
                g.drawRect(x * this.getCellSize(), y * this.getCellSize(), this.getCellSize(), this.getCellSize());
            }
        }*/
    }
}
