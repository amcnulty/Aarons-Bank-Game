/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.entity.mob.Npc;

import bank.graphics.Sprite;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author Aaron
 */
public class FemaleNpc extends Npc {
    
    private boolean walking;
    
        public FemaleNpc(int x, int y) {
            super(x, y);
        }
        
        public FemaleNpc(int x, int y, String path) {
            super(x, y, path);
        }
        
        protected void setSprite(int dir) {
            switch (dir) {
                case 0:
                    this.sprite = Sprite.femaleNPC_forward;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.femaleNPC_forward1;
                        }
                        else this.sprite = Sprite.femaleNPC_forward2;
                    }
                    break;
                case 1:
                    this.sprite = Sprite.femaleNPC_right;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.femaleNPC_right1;
                        }
                        else this.sprite = Sprite.femaleNPC_right2;
                    }
                    break;
                case 2:
                    this.sprite = Sprite.femaleNPC_back;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.femaleNPC_back1;
                        }
                        else this.sprite = Sprite.femaleNPC_back2;
                    }
                    break;
                case 3:
                    this.sprite = Sprite.femaleNPC_left;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.femaleNPC_left1;
                        }
                        else this.sprite = Sprite.femaleNPC_left2;
                    }
                    break;
            }
        }
    
    public String[] getConversationDialog(int dir) throws FileNotFoundException {
        this.dir = dir;
        talking = true;
        if (dialogPath == null) {
            String[] convo = {"Hi I am a female non-player character." , "Do you like what you see?" , "I am still being worked on :("};
            return convo;
        }
        else {
            String s1;
            String s2;
            String s3;
            boolean iSaySo = true;
            BufferedReader textReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(dialogPath)));
            Scanner inputFile = new Scanner(textReader);
            s1 = inputFile.nextLine();
            if (nextMessage > Integer.parseInt(s1)) nextMessage = 1;
            do {
                s2 = inputFile.nextLine();
                if (Integer.parseInt(s2) == nextMessage) {
                    iSaySo = false;
                    nextMessage++;
                }
                else {
                    s3 = inputFile.nextLine();
                        for (int i = 0; i < Integer.parseInt(s3) + 1; i++) {
                            inputFile.nextLine();
                        }
                }
            }
            while (iSaySo);
            int lines = Integer.parseInt(inputFile.nextLine());
            String[] convo = new String[lines];
            for (int i = 0; i < lines; i++) {
			convo[i] = inputFile.nextLine();
		}
            inputFile.close();
            return convo;
        }
    }

}
