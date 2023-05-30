package manager;

import model.Bot;
import model.Bullet;
import model.Map;
import model.Player;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    private Player player;
    private ArrayList<Bullet> playerBullets;
    private ArrayList<Bullet> botBullets;
    private ArrayList<Bot> bots;
    private ArrayList<Map> maps;
    private Random random;

    public void initGameManager() {
        player = new Player(350, 280);
        playerBullets = new ArrayList<>();
        botBullets = new ArrayList<>();
        maps = new ArrayList<>();
        random = new Random();
        readMap("/Users/user/IdeaProjects/Tank90/src/map/map.txt");
        bots = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            Bot bot = new Bot(2, 0);
            Bot bot1 = new Bot(231, 0);
            Bot bot2 = new Bot(459, 0);
            bots.add(bot);
            bots.add(bot1);
            bots.add(bot2);
        }
    }

    private void readMap(String path) {
        File file = new File(path);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            int row = 0;
            while (line != null) {
                for (int i = 0; i < line.length(); i++) {
                    int bit = Integer.parseInt(line.charAt(i) + "");
                    Map map = new Map(i * 19, row * 19, bit);
                    maps.add(map);
                }
                line = bufferedReader.readLine();
                row++;
            }
            bufferedReader.close();
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void draw(Graphics2D g2d) {
        try {
            player.draw(g2d);
            for (Bullet bullet : playerBullets) {
                bullet.draw(g2d);
            }
            for (Bullet bullet : botBullets) {
                bullet.draw(g2d);
            }
            for (int i = 0; i < bots.size(); i++) {
                bots.get(i).draw(g2d);
            }
            for (Map map : maps) {
                map.draw(g2d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AddBot() {
        int count = 0;
        for (int i = 0; i <bots.size(); i++) {
            if (bots.size()<=2) {
                Bot autoBot = new Bot(2, 0);
                bots.add(autoBot);
                count++;
            }
            if (count>6){
            }
        }
    }

    public void playerMove(int newOrient) {
        //Xac dinh huong
        player.changeOrient(newOrient);
        //Thuc hien di chuyen
        player.move(maps);
    }

    public void botMove() {
        for (int i = 0; i < bots.size(); i++) {
            int percent = random.nextInt(101);
            if (percent > 98) {
                int newOrient = random.nextInt(4);
                bots.get(i).changeOrient(newOrient);
            }
            bots.get(i).move(maps);
        }
    }

    public void playerFire() {
        Bullet bullet = player.fire();
        playerBullets.add(bullet);
    }

    public void botFire() {
        for (int i = 0; i < bots.size(); i++) {
            Bullet bullet = bots.get(i).fire();
            botBullets.add(bullet);
        }
    }

    public boolean Auto() {
        botMove();
        AddBot();
        for (int i = playerBullets.size() - 1; i >= 0; i--) {
            boolean check = playerBullets.get(i).move();
            if (check) {
                playerBullets.remove(i);
            }
        }
        for (int i = botBullets.size() - 1; i >= 0; i--) {
            boolean check = botBullets.get(i).move();
            if (check) {
                botBullets.remove(i);
            }
        }
        for (Map map : maps) {
            boolean check = map.checkBullet(playerBullets);
            if (!check) {
                return false;
            }
        }
        for (Map map : maps) {
            boolean check = map.checkBulletBot(botBullets);
            if (!check) {
                return false;
            }
        }
        for (int i = botBullets.size() - 1; i >= 0; i--) {
            boolean check = botBullets.get(i).move();
            if (check) {
                botBullets.remove(i);
            }
        }
        for (int i = bots.size() - 1; i >= 0; i--) {
            boolean check = bots.get(i).checkDie(playerBullets);
            if (!check) {
                bots.remove(i);
            }
        }
        boolean check = player.checkDie(botBullets);
        if (!check) {
            return false;
        }
        return true;
    }
}
