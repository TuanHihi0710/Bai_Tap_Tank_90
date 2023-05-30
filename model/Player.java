package model;

import javax.swing.*;
import java.awt.*;

public class Player extends Tank{

    public Player(int x, int y) {
        super(x, y);
        orient=UP;
        images= new Image[]{
                new ImageIcon("/Users/user/IdeaProjects/Tank90/src/images/player_green_left.png").getImage(),
                new ImageIcon("/Users/user/IdeaProjects/Tank90/src/images/player_green_right.png").getImage(),
                new ImageIcon("/Users/user/IdeaProjects/Tank90/src/images/player_green_up.png").getImage(),
                new ImageIcon("/Users/user/IdeaProjects/Tank90/src/images/player_green_down.png").getImage(),

        };
    }
}
