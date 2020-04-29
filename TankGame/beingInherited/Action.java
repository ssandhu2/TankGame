package beingInherited;
import java.awt.*;

public class Action {
    private Picture picObj;
    private int lagInFrame;
    private int totalFrames;
    private int frameAsofNow;
    private int xVar;
    private int yVar;
    private int inLength;
    private boolean inCircle;
    protected boolean end;

    //constructor with picture object, variable x and y, frame and boolean loop
    public Action(Picture picObj, int xVar, int yVar, int lagInFrame, boolean inCircle){

        this.xVar = xVar;
        this.yVar = yVar;
        this.picObj = picObj;
        this.end = false;
        this.totalFrames =0;
        this.inCircle = inCircle;
        this.inLength =0;
        this.frameAsofNow =0;
        this.lagInFrame = lagInFrame;

    }
    //getter for length
    public int totLength(){
        return this.lagInFrame * picObj.frameCount();
    }
    //return if gameended
    public boolean ifEnded(){
        return this.end;
    }


    //draw to screen
    public void render(Graphics g){
        if(!end){ //if the condition is true and game hasn't ended
            inLength++;
            totalFrames++;
            if(lagInFrame< totalFrames ){
                totalFrames =0;
                end = ( this.totLength() < inLength) && !inCircle;
                frameAsofNow = (frameAsofNow +1) % picObj.frameCount();
            }
            g.drawImage(picObj.getFrame(frameAsofNow), xVar, yVar,null); //draws image to screen
        }
    }

}

