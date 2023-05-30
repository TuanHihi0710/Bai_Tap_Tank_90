package gui;

import manager.GameManager;
import model.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.BitSet;
import java.util.Random;

public class CustomPanel extends JPanel implements Runnable, KeyListener {
    private GameManager gameManager;
    private BitSet bitSet;

    public CustomPanel() {
        gameManager = new GameManager();
        gameManager.initGameManager();
        bitSet = new BitSet(256);
        //Gán mau cho background
        setBackground(Color.BLACK);
        setFocusable(true);

        this.addKeyListener(this);

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        gameManager.draw(g2d);
    }

    @Override
    public void run() {
        int count = 0;
        int countBot = 0;
        boolean isRunning = true;
        while (isRunning) {
            count++;
            countBot++;
            if (bitSet.get(KeyEvent.VK_LEFT)) {
                gameManager.playerMove(Tank.LEFT);
            } else if (bitSet.get(KeyEvent.VK_RIGHT)) {
                gameManager.playerMove(Tank.RIGHT);
            } else if (bitSet.get(KeyEvent.VK_UP)) {
                gameManager.playerMove(Tank.UP);
            } else if (bitSet.get(KeyEvent.VK_DOWN)) {
                gameManager.playerMove(Tank.DOWN);
            }
            if (bitSet.get(KeyEvent.VK_SPACE)) {
                if (count >= 20) {
                    gameManager.playerFire();
                    count = 0;
                }
            }
            if (countBot >= 50) {
                gameManager.botFire();
                countBot = 0;
            }
            isRunning = gameManager.Auto();
            if (!isRunning) {
                int confirm = JOptionPane.showConfirmDialog
                        (null,"Do you want to replay?","Game over",JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.NO_OPTION){
                    System.exit(0);
                }else{
                    isRunning = true;
                    gameManager.initGameManager();
                    bitSet.clear();
                }
            }
            synchronized (CustomPanel.this) {
                repaint();
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        bitSet.set(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        bitSet.clear(e.getKeyCode());
    }
}
