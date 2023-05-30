package model;

import javax.swing.*;
import java.awt.*;

public class Bot extends Tank{
    public Bot(int x, int y) {
        super(x, y);
        orient = DOWN;
        images = new Image[]{
                new ImageIcon("/Users/user/IdeaProjects/Tank90/src/images/bossyellow_left.png").getImage(),
                new ImageIcon("/Users/user/IdeaProjects/Tank90/src/images/bossyellow_right.png").getImage(),
                new ImageIcon("/Users/user/IdeaProjects/Tank90/src/images/bossyellow_up.png").getImage(),
                new ImageIcon("/Users/user/IdeaProjects/Tank90/src/images/bossyellow_down.png").getImage(),
        };
    }
}
