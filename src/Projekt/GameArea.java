package Projekt;

import Blocks.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameArea extends JPanel {

    private int rows, cols;
    private int cellSize;
    private Brick block, nxtBlock;

    private Color[][] fallenBlocks; //Basically its a background for fallen blocks that are seen in game area

    private Brick[] bricks;

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

    public GameArea(int columns) {
        this.setBounds(0, 0, 300, 550);
        setCols(columns);
        setCellSize(this.getBounds().width / this.cols);
        setRows(this.getBounds().height / this.cellSize);

        // Initialize fallenBlocks array
        this.fallenBlocks = new Color[this.rows][this.cols];

        bricks = new Brick[]{new LBlock(), new JBlock(), new ZBlock(), new SBlock(), new TBlock(), new OBlock()};
        Random random = new Random();
        nxtBlock = bricks[random.nextInt(bricks.length)];

        // Initialize block with the next block
        spawner();
    }

    public void initialize() {
        // Set the block in the BrickArea for the next block display after everything is initialized
        if (Main.brickArea != null && this.nxtBlock != null) {
            Main.brickArea.setBlock(this.nxtBlock, 10, 5);
        }
    }

    public void spawner() {
        Random random = new Random();

        // Assign the next block to the current block
        this.block = this.nxtBlock;
        System.out.println(this.block);

        // Generate a new next block
        this.nxtBlock = bricks[random.nextInt(bricks.length)];
        System.out.println("nxt = " + this.nxtBlock);
        System.out.println("x = " + this.nxtBlock.getX() + "y = " + this.nxtBlock.getY());

        // Initialize the position of the current block
        this.block.spwanBlock(cols);
    }

    public boolean bIsOutOfBounds() {
        return block.getY() < 0;
    }

    public boolean force() {
        if (bHasTouchedGrass()) {
            addToFallenOnes();
            clearLines();
            return false;
        }

        moveDown();
        repaint();

        return true;
    }

    public boolean bHasTouchedGrass() {
        if (this.block.getGrassLevel() == rows) {
            addToFallenOnes();
            return true;
        }

        int[][] shape = block.getBlockShape();
        int width = block.getWidth();
        int height = block.getHeight();

        for (int col = 0; col < width; col++) {
            for (int row = height - 1; row >= 0; row--) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX();
                    int y = row + block.getY() + 1;
                    if (y < 0) break;
                    if (fallenBlocks[y][x] != null) {
                        addToFallenOnes();
                        return true;
                    }
                    break;
                }
            }
        }

        return false;
    }

    public boolean bTouchedRight() {
        if (this.block.getLeftEdge() == getCols() - 2) return false;

        int[][] shape = block.getBlockShape();
        int width = block.getWidth();
        int height = block.getHeight();

        for (int row = 0; row < height; row++) {
            for (int col = width - 1; col >= 0; col--) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX() + 1;
                    int y = row + block.getY();
                    if (y < 0) break;
                    if (fallenBlocks[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    public boolean bTouchedLeft() {
        if (this.block.getRightEdge() == 2) return false;

        int[][] shape = block.getBlockShape();
        int width = block.getWidth();
        int height = block.getHeight();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX() - 1;
                    int y = row + block.getY();
                    if (y < 0) break;
                    if (fallenBlocks[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    public void drawBlock(Graphics g, Brick block) {
        if (block == null) return; // Avoid NullPointerException

        int x, y;

        for (int row = 0; row < block.getBlockShape().length; row++) {
            for (int col = 0; col < block.getBlockShape()[0].length; col++) {
                if (block.getBlockShape()[row][col] == 1) {
                    x = getCellSize() * (block.getX() + col);
                    y = getCellSize() * (block.getY() + row);

                    drawSquare(g, block.getColor(), x, y);
                }
            }
        }
    }

    public void drawBg(Graphics g) {
        Color color;

        for (int row = 0; row < this.getRows(); row++) {
            for (int col = 0; col < this.getCols(); col++) {
                color = getFallenBlocks()[row][col];
                if (color != null) {
                    int x = col * getCellSize();
                    int y = row * getCellSize();
                    drawSquare(g, color, x, y);
                }
            }
        }
    }

    public void addToFallenOnes() {
        if (bIsOutOfBounds()) return;

        int[][] shape = this.getBlock().getBlockShape();
        Color color = this.getBlock().getColor();

        for (int row = 0; row < this.getBlock().getHeight(); row++) {
            for (int col = 0; col < this.getBlock().getWidth(); col++) {
                if (shape[row][col] == 1) {
                    this.fallenBlocks[row + this.getBlock().getY()][col + this.getBlock().getX()] = color;
                }
            }
        }
    }

    public void drawSquare(Graphics g, Color color, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, this.getCellSize(), this.getCellSize());
        g.setColor(Color.black);
        g.drawRect(x, y, this.getCellSize(), this.getCellSize());
        repaint();
    }

    public void moveRight() {
        if (!bTouchedRight()) return;
        block.right();
        repaint();
    }

    public void moveLeft() {
        if (!bTouchedLeft()) return;
        block.left();
        repaint();
    }

    public void rotate() {
        block.rotate();
        if (block.getLeftEdge() < 0) block.setX(0);
        if (block.getRightEdge() >= cols) block.setX(cols - block.getWidth());
        if (block.getGrassLevel() >= rows) block.setY(rows - block.getHeight());
        repaint();
    }

    public boolean moveDown() {
        if (bHasTouchedGrass()) {
            addToFallenOnes();
            repaint();
            return false;
        }
        block.down();
        repaint();
        return true;
    }

    public void clearLines() {
        boolean lineFilled;

        for (int row = rows - 1; row >= 0; row--) {
            lineFilled = true;

            for (int col = 0; col < cols; col++) {
                if (fallenBlocks[row][col] == null) {
                    lineFilled = false;
                    break;
                }
            }

            if (lineFilled) {
                clearLine(row);
                shiftDown(row);
                clearLine(0);
                row++;
                repaint();
            }
        }
    }

    private void shiftDown(int r) {
        for (int row = r; row > 0; row--) {
            for (int col = 0; col < cols; col++) {
                fallenBlocks[row][col] = fallenBlocks[row - 1][col];
            }
        }
    }

    private void clearLine(int row) {
        for (int i = 0; i < cols; i++) {
            fallenBlocks[row][i] = null;
            GUI.pointsTextField.setText(String.valueOf("Score: " + Main.getScore()));
            GUI.levelTextField.setText(String.valueOf("Level: " + Main.getLevel()));
            GUI.lineTextField.setText(String.valueOf("Lines: " + Main.getLines()));
        }
        Main.addScore(100);
        Main.addLines(1);
        if (Main.getLines() % 3 == 0) Main.addLevel(1);
    }

    public void removeFallenOnes() {
        for (int row = 0; row < this.getRows(); row++) {
            for (int col = 0; col < this.getCols(); col++) {
                fallenBlocks[row][col] = null;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBg(g);
        drawBlock(g, this.getBlock());
    }
}
