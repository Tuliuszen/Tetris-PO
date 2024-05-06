package Projekt;

import java.awt.*;

public class Brick {

    private int[][] blockShape;
    private Color color;

    private int x, y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int[][] getBlockShape() {
        return blockShape;
    }

    public int getHeight()
    {
        return this.blockShape.length;
    }

    public int getWidth()
    {
        return this.blockShape[0].length;
    }

    public void setBlockShape(int[][] blockShape) {
        this.blockShape = blockShape;
    }


    public void down()
    {
        this.y++;
    }

    public void right()
    {
        this.x++;
    }

    public void left()
    {
        this.x--;
    }

    public void spwanBlock(int width)
    {
        x = (width - this.getWidth()) / 2;
        y = -this.getHeight();
    }

    public int getGrassLevel()
    {
        return this.getY() + this.getHeight();
    }

    public Brick(int[][] blockShape, Color color, int x, int y) {
        this.blockShape = blockShape;
        this.color = color;
        this.x = x;
        this.y = y;
    }
}
