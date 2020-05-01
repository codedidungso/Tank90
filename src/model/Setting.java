/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.sun.glass.events.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.ENV;

/**
 *
 * @author thiennd
 */
public class Setting implements Serializable {

    public static final int EASY = 1;
    public static final int MEDIUM = 2;
    public static final int HARD = 3;
    public static final int LEGEND = 4;
    public int level = EASY;

    public int playerFireRate = 1000;
    public int gameSpeed = 20;
    public int enemyFireRate = 1000;
    public int bulletSpeed = 2;

    public int keyLeft = KeyEvent.VK_LEFT;
    public int keyRight = KeyEvent.VK_RIGHT;
    public int keyUp = KeyEvent.VK_UP;
    public int keyDown = KeyEvent.VK_DOWN;
    public int keyFire = KeyEvent.VK_SPACE;
    public int keyPause = KeyEvent.VK_P;

    public final String SETTING_FILE_PATH = "/setting";

    File settingFile = new File(SETTING_FILE_PATH);

    public Setting() {
//        if (settingFile.exists()) {
//            settingFile.delete();
//
//        }
    }

    public Setting(Setting setting) {

    }

    public void saveSetting() {
        setupLevel();
        checkFileExist();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(settingFile));
            out.writeObject(this);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Setting loadOldSetting() {
        checkFileExist();
        Setting oldSetting;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(settingFile));
            oldSetting = (Setting) in.readObject();
            in.close();
        } catch (Exception e) {
            oldSetting = this;
        }
        return oldSetting;
    }

    private void checkFileExist() {
        if (!settingFile.exists()) {
            try {
                settingFile.createNewFile();

            } catch (IOException ex) {
                Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void restoreDefaultSetting() {
        level = EASY;

        playerFireRate = 1000;
        gameSpeed = 20;
        enemyFireRate = 1000;
        bulletSpeed = 2;

        keyLeft = KeyEvent.VK_LEFT;
        keyRight = KeyEvent.VK_RIGHT;
        keyUp = KeyEvent.VK_UP;
        keyDown = KeyEvent.VK_DOWN;
        keyFire = KeyEvent.VK_SPACE;

    }

    public void setupLevel() {
        switch (level) {
            case Setting.EASY:
                playerFireRate = 1000;
                gameSpeed = 20;
                enemyFireRate = 1500;
                bulletSpeed = 2;
                break;
            case Setting.MEDIUM:
                playerFireRate = 1000;
                gameSpeed = 15;
                enemyFireRate = 1000;
                bulletSpeed = 2;
                break;
            case Setting.HARD:
                playerFireRate = 1000;
                gameSpeed = 10;
                enemyFireRate = 1000;
                bulletSpeed = 1;
                break;
            case Setting.LEGEND:
                playerFireRate = 500;
                gameSpeed = 10;
                enemyFireRate = 500;
                bulletSpeed = 1;
                break;
        }
    }

    @Override
    public String toString() {
        return "Setting{"
                + "\n\tplayerFireRate=" + playerFireRate
                + "\n\tgameSpeed=" + gameSpeed
                + "\n\tenemyFireRate=" + enemyFireRate
                + "\n\tbulletSpeed=" + bulletSpeed
                + "\n\tkeyLeft=" + keyLeft
                + "\n\tkeyRight=" + keyRight
                + "\n\tkeyUp=" + keyUp
                + "\n\tkeyDown=" + keyDown
                + "\n\tkeyFire=" + keyFire
                + "\n\tsettingFile=" + settingFile.getAbsolutePath()
                + "\n}";
    }

}
