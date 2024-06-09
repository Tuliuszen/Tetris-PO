package Projekt;

import Blocks.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BrickArea extends JPanel {

    private int rows, cols;
    private int cellSize;
    private Brick block;

    private Color[][] fallenBlocks; //Basically its a background for fallen blocks that are seen in game area

    private Brick[] bricks;

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

    public Brick getBlock() {
        return block;
    }

    public void setBlock(Brick block) {
        this.block = block;
    }

    public Color[][] getFallenBlocks() {
        return fallenBlocks;
    }

    public void setFallenBlocks(Color[][] fallenBlocks) {
        this.fallenBlocks = fallenBlocks;
    }

    public Brick[] getBricks() {
        return bricks;
    }

    public void setBricks(Brick[] bricks) {
        this.bricks = bricks;
    }

    public void spawner()
    {
        Random random = new Random();

        this.block = bricks[random.nextInt(bricks.length)];
        block.spwanBlock(cols);
    }

    public BrickArea(int columns)
    {
        //this.setPreferredSize(new Dimension(300, 550));
        this.setBounds(0, 0, 100, 100);
        //this.setBackground(Color.BLACK);

        setCols(columns);
        setCellSize(this.getBounds().width / this.cols);
        setRows(this.getBounds().height / this.cellSize);

        this.setFallenBlocks(new Color[this.getCols()][this.getRows()]);

        //fallenBlocks = new Color[getRows()][getCols()];
        fallenBlocks[0][0] = Color.GREEN;
        //spawner();

        bricks = new Brick[]{new LBlock(), new JBlock(), new ZBlock(), new SBlock(), new TBlock(), new OBlock(), new IBlock()};
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

    public void drawSquare(Graphics g, Color color, int x, int y)
    {
        g.setColor(color);
        g.fillRect(x,y,this.getCellSize(), this.getCellSize());
        g.setColor(Color.black);
        g.drawRect(x, y, this.getCellSize(), this.getCellSize());
        repaint();
    }

    public void drawBg(Graphics g)
    {
        Color color;

        for(int row = 0; row < this.getRows(); row++)
        {
            for(int c = 0; c < this.getCols(); c++)
            {

                color = getFallenBlocks()[row][c];
                if (color != null)
                {
                    int x = c * getCellSize();
                    int y = row * getCellSize();

                    drawSquare(g, color, x, y);
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        drawBg(g);
        //drawBlock(g, this.getBlock());

        /*for (int y = 0; y < this.getRows(); y++) {
            for (int x = 0; x < this.getCols(); x++) {
                g.drawRect(x * this.getCellSize(), y * this.getCellSize(), this.getCellSize(), this.getCellSize());
            }
        }*/
    }
}
