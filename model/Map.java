package model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Map {
    private int x;
    private int y;
    private int bit;
    private Image[] images = new Image[]{
            new ImageIcon("/Users/user/IdeaProjects/Tank90/src/images/brick.png").getImage(),
            new ImageIcon("/Users/user/IdeaProjects/Tank90/src/images/rock.png").getImage(),
            new ImageIcon("/Users/user/IdeaProjects/Tank90/src/images/bird.png").getImage(),
            new ImageIcon("/Users/user/IdeaProjects/Tank90/src/images/tree.png").getImage(),
            new ImageIcon("/Users/user/IdeaProjects/Tank90/src/images/water.png").getImage(),

    };

    public Map(int x, int y, int bit) {
        this.x = x;
        this.y = y;
        this.bit = bit;
    }

    public void draw(Graphics2D g2d) {
        if (bit > 0) {
            if (bit == 3) {
                g2d.drawImage(images[bit - 1], x, y, 40, 40, null);
            } else {
                g2d.drawImage(images[bit - 1], x, y, null);
            }
        }
    }

    public Rectangle getRect() {
        Rectangle rectangle;
        if (bit == 0 || bit == 4) {
            rectangle = new Rectangle();
        } else if (bit == 3) {
            rectangle = new Rectangle(x, y, 40, 40);
        } else {
            rectangle = new Rectangle(x, y, images[bit - 1].getWidth(null), images[bit - 1].getHeight(null));
        }
        return rectangle;
    }

    public boolean checkBullet(ArrayList<Bullet> playerBullets) {
        for (int i = 0; i < playerBullets.size(); i++) {
            Rectangle rectangle = getRect().intersection(playerBullets.get(i).getRect());
            if (!rectangle.isEmpty()) {
                if (bit == 5 || bit == 4) {
                    return true;
                }
                playerBullets.remove(i);
                if (bit == 3) {
                    return false;
                }
                if (bit == 1) {
                    bit = 0;
                }
                return true;
            }
        }
        return true;
    }

    public boolean checkBulletBot(ArrayList<Bullet> botBullets) {
        for (int i = 0; i < botBullets.size(); i++) {
            Rectangle rectangle = getRect().intersection(botBullets.get(i).getRect());
            if (!rectangle.isEmpty()) {
                if (bit == 5 || bit == 4) {
                    return true;
                }
                botBullets.remove(i);
                if (bit == 3) {
                    return false;
                }
                if (bit == 1) {
                    bit = 0;
                }
                return true;
            }
        }
        return true;
    }
}
