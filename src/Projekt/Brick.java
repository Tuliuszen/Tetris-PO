package Projekt;

import java.awt.*;

public class Brick {

    private int[][] block;

    public int[][] getBlock() {
        return block;
    }

    public void setBlock(int[][] block) {
        this.block = block;
    }

    public Brick(int[][] block) {
        this.block = block;
    }

    public void drawBlock(Graphics g, GameArea area, Brick block)
    {
        for (int row = 0; row < block.getBlock().length; row++) {
            for (int col = 0; col < block.getBlock()[0].length; col++) {
                if(block.getBlock()[row][col] == 1)
                {
                    g.setColor(Color.red);
                    g.fillRect(col * area.getCellSize(), row * area.getCellSize(), area.getCellSize(), area.getCellSize());
                    g.setColor(Color.black);
                    g.drawRect(col * area.getCellSize(), row * area.getCellSize(), area.getCellSize(), area.getCellSize()); // draw area
                }
            }
        }
    }
}
