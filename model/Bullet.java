package model;

import gui.CustomFrame;

import javax.swing.*;
import java.awt.*;

public class Bullet {
    private int x;
    private int y;
    private int speed;
    private int orient;
    private Image img = new ImageIcon("/Users/user/IdeaProjects/Tank90/src/images/bullet.png").getImage();

    public Bullet(int x, int y, int orient) {
        this.x = x - img.getWidth(null) / 2;
        this.y = y - img.getHeight(null) / 2;
        this.orient = orient;
        this.speed = 2;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(this.img, x, y, null);
    }

    public boolean move() {
        switch (orient) {
            case Tank.LEFT:
                x -= speed;
                break;
            case Tank.RIGHT:
                x += speed;
                break;
            case Tank.UP:
                y -= speed;
                break;
            case Tank.DOWN:
                y += speed;
                break;
        }
        if (x <= 0 || y <= 0 || x >= CustomFrame.W_FRAME - img.getWidth(null) || y >= CustomFrame.H_FRAME - img.getHeight(null)) {
            return true;
        }
        return false;
    }
    public Rectangle getRect(){
        return new Rectangle(x,y,img.getWidth(null),img.getHeight(null));
    }
}

