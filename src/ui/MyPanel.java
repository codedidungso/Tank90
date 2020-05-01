package ui;

import model.MyTank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyPanel extends JPanel implements Runnable {

    private ManagerItem managerItem;
    private Thread thread;
    private boolean isLeft;
    private boolean isRight;
    private boolean isUp;
    private boolean isDown;
    private boolean isFire;
    private boolean pause = false;

    public MyPanel() {
        ///
        setSize(GUI.WIDTH_FRAME, GUI.HEIGHT_FRAME);
        setLocation(0, 0);
        setBackground(Color.BLACK);
        managerItem = new ManagerItem();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == ENV.setting.keyLeft) {
                    isLeft = true;
                } else if (key == ENV.setting.keyRight) {
                    isRight = true;
                } else if (key == ENV.setting.keyUp) {
                    isUp = true;
                } else if (key == ENV.setting.keyDown) {
                    isDown = true;
                } else if (key == ENV.setting.keyFire) {
                    isFire = true;
                } else if (key == ENV.setting.keyPause) {
                    pause = !pause;
                }
            }

            @Override

            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == ENV.setting.keyLeft) {
                    isLeft = false;
                } else if (key == ENV.setting.keyRight) {
                    isRight = false;
                } else if (key == ENV.setting.keyUp) {
                    isUp = false;
                } else if (key == ENV.setting.keyDown) {
                    isDown = false;
                } else if (key == ENV.setting.keyFire) {
                    isFire = false;
                }
            }

        }
        );

        setRequestFocusEnabled(true);
        setFocusable(true);

        thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // noi de ve
        // Graphics la but ve

        Graphics2D g2d = (Graphics2D) g;

        managerItem.drawMyTank(g2d);
        managerItem.drawEnemyAllTank(g2d);
        managerItem.drawBulletOfMyTank(g2d);
        managerItem.drawAllBulletEnemyTank(g2d);
        managerItem.drawAll(g2d);

    }

    @Override
    public void run() {
        //chay khi goi thread.start

        while (true) {
            try {
                Thread.sleep(ENV.setting.gameSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (pause) {
                continue;
            }
            moveMyTank();
            moveBulletOfTank();
            fireOfMyTank();
            managerItem.interactBulletOfMyTank();
            managerItem.moveAllEnemyTank();
            managerItem.moveAllBulletEnemyTank();
            managerItem.fireEnemyTank();
            managerItem.interactBulletOfAllEnemyTank();
            managerItem.killEnemyTank();
            if (managerItem.checkGameOver()) {
                JOptionPane.showMessageDialog(MyPanel.this, "Game Over");
                break;
            }
            if (managerItem.checkWin()) {
                JOptionPane.showMessageDialog(MyPanel.this, "Win");
                break;
            }
            repaint();
        }

    }

    private void moveBulletOfTank() {
        managerItem.moveBulletOfMyTank();

    }

    void fireOfMyTank() {
        if (isFire) {
            managerItem.fireBullet();
        }
    }

    void moveMyTank() {
        if (isDown) {
            managerItem.moveMyTank(MyTank.DOWN);

        } else {
            if (isUp) {
                managerItem.moveMyTank(MyTank.UP);

            } else {
                if (isRight) {
                    managerItem.moveMyTank(MyTank.RIGHT);

                } else {
                    if (isLeft) {
                        managerItem.moveMyTank(MyTank.LEFT);

                    }

                }
            }
        }

    }
}
