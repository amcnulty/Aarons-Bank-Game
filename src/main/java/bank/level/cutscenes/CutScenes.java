/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.level.cutscenes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;


/**
 *
 * @author Aaron
 */
public class CutScenes {
    
    //private static String introDialogPath = CutScenes.class.getResource("/dialogs/intro_scene_dialog.txt").getPath();
    private static String introDialogPath = "/dialogs/intro_scene_dialog.txt";
    //private static InputStream introDialogPath = CutScenes.class.getClassLoader().getResourceAsStream("/dialogs/intro_scene_dialog.txt");
    
    public boolean introScenePlaying = false;
    private byte[][] introScene = new byte[0][3];
    public int index;
    private int messageGroup = 1;

    public CutScenes() {
        buildIntroScene(2, 120, false);
        //buildIntroScene(1, 60, false);
        //buildIntroScene(2, 120, false);
        
        buildIntroScene(8, 0, false);       // Dialog attempt
        
        buildIntroScene(2, 30, false);
        buildIntroScene(1, 30, true);
        buildIntroScene(2, 30, false);
        //buildIntroScene(2, 1, false);
        
        buildIntroScene(8, 0, false);       // Dialog
        
       // buildIntroScene(3, 60, false);
        buildIntroScene(2, 60, true);
        
        buildIntroScene(8, 0, false);       // Dialog
    }
    
    private void buildIntroScene(int dir, int clicks, boolean running) {
        switch (dir) {
            case 0:
                byte[][] copy0 = north(clicks, running, introScene);
                introScene = new byte[copy0.length][3];
                for (int i = 0; i < copy0.length; i++) {
                    for (int ii = 0; ii < 3; ii++) {
                        introScene[i][ii] = copy0[i][ii];
                    }
                }
                break;
            case 1:
                byte[][] copy1 = east(clicks, running, introScene);
                introScene = new byte[copy1.length][3];
                for (int i = 0; i < copy1.length; i++) {
                    for (int ii = 0; ii < 3; ii++) {
                        introScene[i][ii] = copy1[i][ii];
                    }
                }
                break;
            case 2:
                byte[][] copy2 = south(clicks, running, introScene);
                introScene = new byte[copy2.length][3];
                for (int i = 0; i < copy2.length; i++) {
                    for (int ii = 0; ii < 3; ii++) {
                        introScene[i][ii] = copy2[i][ii];
                    }
                }
                break;
            case 3:
                byte[][] copy3 = west(clicks, running, introScene);
                introScene = new byte[copy3.length][3];
                for (int i = 0; i < copy3.length; i++) {
                    for (int ii = 0; ii < 3; ii++) {
                        introScene[i][ii] = copy3[i][ii];
                    }
                }
                break;
            case 4:
                
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                byte[][] copy8 = new byte[introScene.length + 1][3];
                copy8[introScene.length][2] = 1;
                for (int i = 0; i < introScene.length; i++) {
                    for(int ii = 0; ii < 3; ii++) {
                        copy8[i][ii] = introScene[i][ii];
                    }
                }
                introScene = new byte[copy8.length][3];
                for (int i = 0; i < copy8.length; i++) {
                    for (int ii = 0; ii < 3; ii++) {
                        introScene[i][ii] = copy8[i][ii];
                    }
                }
                break;
        }
        
    }

    private byte[][] north(int clicks, boolean running, byte[][] array) {
        byte[][] copyArray = new byte[clicks + array.length][3];
        
        for (int i = 0; i < array.length; i++) {
            for (int ii = 0; ii < 3; ii++) {
                copyArray[i][ii] = array[i][ii];
            }
        }
        
        byte speed = -1;
        if (running) {
            speed = -2;
        }
        for (int i = array.length; i < array.length + clicks; i++) {
            copyArray[i][1] = speed;
        }
        return copyArray;
    }
    
    private byte[][] east(int clicks, boolean running, byte[][] array) {
        byte[][] copyArray = new byte[clicks + array.length][3];
        
        for (int i = 0; i < array.length; i++) {
            for (int ii = 0; ii < 3; ii++) {
                copyArray[i][ii] = array[i][ii];
            }
        }
        
        byte speed = 1;
        if (running) {
            speed = 2;
        }
        for (int i = array.length; i < array.length + clicks; i++) {
            copyArray[i][0] = speed;
        }
        return copyArray;
    }
    
    private byte[][] south(int clicks, boolean running, byte[][] array) {
        byte[][] copyArray = new byte[clicks + array.length][3];
        
        for (int i = 0; i < array.length; i++) {
            for (int ii = 0; ii < 3; ii++) {
                copyArray[i][ii] = array[i][ii];
            }
        }
        
        byte speed = 1;
        if (running) {
            speed = 2;
        }
        for (int i = array.length; i < array.length + clicks; i++) {
            copyArray[i][1] = speed;
        }
        return copyArray;
    }

    private byte[][] west(int clicks, boolean running, byte[][] array) {
        byte[][] copyArray = new byte[clicks + array.length][3];
        
        for (int i = 0; i < array.length; i++) {
            for (int ii = 0; ii < 3; ii++) {
                copyArray[i][ii] = array[i][ii];
            }
        }
        
        byte speed = -1;
        if (running) {
            speed = -2;
        }
        for (int i = array.length; i < array.length + clicks; i++) {
            copyArray[i][0] = speed;
        }
        return copyArray;
    }

    /**
     * Creates diagonal movement in a cutscene
     * @param dir 0 - NE  1 - SE  2 - SW  3 - NW
     * @param clicks How many updates to apply this movement
     * @param startingIndex starting spot in the animation array
     * @param running true if character is running, false if he is walking
     */

    private void diagonal(byte dir, int clicks, int startingIndex, boolean running, byte[][] array) {

    }
    
    public String[] getMessage() throws FileNotFoundException {
        BufferedReader textReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(introDialogPath)));
        Scanner inputFile = new Scanner(textReader);
        boolean iSaySo = true;
        String s1;
        String s2;
        
        do {
            s1 = inputFile.nextLine();
            s2 = inputFile.nextLine();
            
            if (Integer.parseInt(s1) == messageGroup) {
                iSaySo = false;
                messageGroup++;
            }
            else {
                for (int i = 0; i < Integer.parseInt(s2) + 1; i++) {
                    inputFile.nextLine();
                }
            }
        }
        while (iSaySo);
        String[] array = new String[Integer.parseInt(s2)];
        for (int i = 0; i < array.length; i++) {
            array[i] = inputFile.nextLine();
        }
        inputFile.close();
        return array;
    }
    
    public byte checkForDialog() {
        if (introScenePlaying) {
            try {
                return introScene[index][2];
            }
            catch (ArrayIndexOutOfBoundsException e) {
            }
        }
        return (byte) 0;
    }
    
    public byte getXa() {
        if (introScenePlaying) {
            try {
                return introScene[index][0];
            }
            catch(ArrayIndexOutOfBoundsException e) {
                return (byte) 5;
            }
        }
        return (byte) 5;
    }
    
     public byte getYa() {
        if (introScenePlaying) {
            try {
                return introScene[index++][1];
            }
            catch(ArrayIndexOutOfBoundsException e) {
                index = 0;
                messageGroup = 1;
            }
        }
        return (byte) 5;
    }
}
