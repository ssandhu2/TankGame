package beingInherited;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

public class ObjClass {
    protected int x_objclass;
    protected int y_objclass;
    protected ImageObserver imgObs; //imageobserver
    protected BufferedImage bufImage; //bufferedimage

    public ObjClass(String param_img) {
        this(param_img,null);
    } //constructor

    public ObjClass(String param_img, ImageObserver imgObs) { //contructor with image and imageobserver
        y_objclass =0;
        x_objclass =0;
      try {
          bufImage = ImageIO.read(new File(param_img)); //read image
          this.imgObs = imgObs;
      }
      catch (Exception e){
          System.out.println("ERROR LOADING");
      }

    }


    //getters and setters
    public int retriveImgX(){
        return this.x_objclass;
    }
    public int retrieveImgY(){
        return this.y_objclass;
    }
    public void xAxisImg(int x){
        this.x_objclass =x;
    }
    public void YaxisImg(int y){
        this.y_objclass =y;
    }
    public int retHeight(){
        return this.bufImage.getHeight();
    }
    public int retWidth(){
        return this.bufImage.getWidth();
    }
    //draw image
    public void render(Graphics g){
        g.drawImage(bufImage, x_objclass, y_objclass, imgObs);
    }
}
