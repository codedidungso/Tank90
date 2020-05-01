/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.sun.glass.events.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.objects.NativeArray;
import model.Setting;

/**
 *
 * @author thiennd
 */
public class ENV {

    private static final int PLAYING = 1;
    private static final int WIN = 2;
    private static final int LOSE = 3;
    private static final int PAUSE = 4;

    public static Setting setting;
    public static int status = PLAYING;

    static void loadSetting() {
        setting = new Setting();
        setting = setting.loadOldSetting();
    }


}
