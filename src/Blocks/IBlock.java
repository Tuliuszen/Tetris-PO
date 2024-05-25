package Blocks;

import Projekt.Brick;

public class IBlock extends Brick
{
    public IBlock() {
        super(new int[][]{ {1,1,1,1}}, 0, 0); //jak sie nie uda naprawic buga to zamienic na L
    }

    @Override
    public void rotate()
    {
        super.rotate();

        if (this.getWidth() == 1)
        {
            this.setX(this.getX() + 1);
            this.setY(this.getY() - 1);
        }
        else
        {
            this.setX(this.getX() - 1);
            this.setY(this.getY() + 1);
        }
    }
}
