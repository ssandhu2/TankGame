package keys;


import beingInherited.Action;
import beingInherited.Panel;
import beingInherited.Picture;
import bulletClass.Bullet;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Controller extends Panel implements KeyListener {
    private Picture shell;
    private boolean dHit;
    private boolean altHit;
    private boolean wHit;
    private boolean sHit;
    private boolean aHit;
    private Picture destroyed;
    private boolean enterHit;
    private boolean leftHit;
    private boolean rightHit;
    private boolean upHit;
    private boolean downHit;

    public Controller(){
        super();
        this.setFocusable(true);
        this.addKeyListener(this);
        new Thread(this).start();
        try{
            this.shell = new Picture("Resources/myshell.png", 24); //load shell
            this.destroyed = new Picture("Resources/mysmallexplosion.png", 32); //load explosion
        }
        catch (IOException e){
            System.out.println("can't load image small explosion and shell");
        }
    }
    @Override
    public void run(){
        secondTank.scale();
        firstTank.scale();

        while (true){
            if(!(gamer_1.gameEnd() || gamer_2.gameEnd())){ //if game doesn't end
                //key controls
                if(sHit){
                    firstTank.B_move();
                    if(!((firstTank.wallBang(breakable_Setup_wall))|| (firstTank.tankBumpCheck(secondTank)))){
                        firstTank.xAxisImg(firstTank.rXAxisImg());
                        firstTank.YaxisImg(firstTank.rYAxisImg());
                    }
                }

                if(altHit){
                    doAction(new Action(this.destroyed, firstTank.x_shoot(), firstTank.y_shoot(),1,false));
                    doAction(new Bullet(this, firstTank, secondTank, gamer_1, gamer_2, breakable_Setup_wall, this.shell, firstTank.x_shoot(), firstTank.y_shoot(), 5, false));
                }
                if(aHit){
                    firstTank.l_move();
                }
                if(dHit){
                    firstTank.R_move();
                }
                if(wHit){
                    firstTank.F_move();
                    if(!((firstTank.wallBang(breakable_Setup_wall))|| (firstTank.tankBumpCheck(secondTank)))){
                        firstTank.xAxisImg(firstTank.rXAxisImg());
                        firstTank.YaxisImg(firstTank.rYAxisImg());
                    }
                }

                if(upHit){
                    secondTank.F_move();
                    if(!((secondTank.wallBang(breakable_Setup_wall))|| (secondTank.tankBumpCheck(firstTank)))){
                        secondTank.xAxisImg(secondTank.rXAxisImg());
                        secondTank.YaxisImg(secondTank.rYAxisImg());
                    }
                }
                if(downHit){
                    secondTank.B_move();
                    if(!((secondTank.wallBang(breakable_Setup_wall))|| (secondTank.tankBumpCheck(firstTank)))){
                        secondTank.xAxisImg(secondTank.rXAxisImg());
                        secondTank.YaxisImg(secondTank.rYAxisImg());
                    }
                }
                if(leftHit){
                    secondTank.l_move();
                }
                if(rightHit){
                    secondTank.R_move();
                }

                if(enterHit){
                    doAction(new Action(this.destroyed, secondTank.x_shoot(), secondTank.y_shoot(),1,false));
                    doAction(new Bullet(this, secondTank, firstTank, gamer_2, gamer_1, breakable_Setup_wall, this.shell, secondTank.x_shoot(), secondTank.y_shoot(), 5, false));

                }

            }
            this.repaint(); //call repaint method
            try {
                Thread.sleep(110); //sleep thread
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    //if the following keys are pressed return true
    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();


        if (keyPressed  == KeyEvent.VK_A) {
            aHit = true;
        }
        else if (keyPressed == KeyEvent.VK_D) {
            dHit = true;
        }
        else if (keyPressed == KeyEvent.VK_W) {
            wHit = true;
        }
        else if (keyPressed == KeyEvent.VK_S) {
            sHit = true;
        }
        else if (keyPressed == KeyEvent.VK_ALT) {
            altHit = true;
        }
        else if (keyPressed == KeyEvent.VK_LEFT) {
            leftHit = true;
        }
        else if (keyPressed == KeyEvent.VK_RIGHT) {
            rightHit = true;
        }
        else if (keyPressed == KeyEvent.VK_UP) {
            upHit = true;
        }
        else if (keyPressed == KeyEvent.VK_DOWN) {
            downHit = true;
        }
        else if (keyPressed == KeyEvent.VK_ENTER) {
            enterHit = true;
        }
    }

    //when key released return false
    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();

        if (keyReleased == KeyEvent.VK_D) {
            dHit = false;
        }

        else if (keyReleased == KeyEvent.VK_S) {
            sHit = false;
        }
        else if (keyReleased == KeyEvent.VK_ALT) {
            altHit = false;
        }
        else if (keyReleased == KeyEvent.VK_W) {
            wHit = false;
        }
        else if (keyReleased  == KeyEvent.VK_A) {
            aHit = false;
        }

        else if (keyReleased == KeyEvent.VK_UP) {
            upHit = false;
        }
        else if (keyReleased == KeyEvent.VK_DOWN) {
            downHit = false;
        }
        else if (keyReleased == KeyEvent.VK_LEFT) {
            leftHit = false;
        }
        else if (keyReleased == KeyEvent.VK_RIGHT) {
            rightHit = false;
        }
        else if (keyReleased == KeyEvent.VK_ENTER) {
            enterHit = false;
        }

    }
}


