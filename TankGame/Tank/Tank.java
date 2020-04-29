package Tank;

import beingInherited.ObjClass;
import beingInherited.Picture;
import wall.SetupWall;

import java.awt.*;
import java.io.IOException;

public class Tank extends ObjClass {
    private int height_forMap =30; //array
    private int width_forMap = 40; //array
    private int[] slantArr = new int [60]; //slantArr
    private int intN =9;
    private Picture pic_obj_direction;
    private int ang =0;
    private int indicator =0;
    private int latestX =0;
    private int latestY =0;
    public Tank(String param_imG) throws IOException{
        super(param_imG);
        pic_obj_direction = new Picture("Resources/mytank.png", 64); //load tankimage which has 64 frames

    }
    public void scale(){
        for(int row = 0; row< slantArr.length; row++){ //loop through the array
            slantArr[row] = ang;
            ang +=6; //add 6
        }
    }

    //get's indicator
    public void indc(int indCC){
        this.indicator = indCC;

    }
    //use rectangles to check for colissions
    public boolean tankBumpCheck(Tank anotherTank){
        Rectangle tank = new Rectangle(latestX, latestY, retWidth()- intN, retHeight()- intN);
        Rectangle secondTank = new Rectangle(anotherTank.retriveImgX(), anotherTank.retrieveImgY(), anotherTank.retWidth()- intN, anotherTank.retHeight()- intN);
        return tank.intersects(secondTank);

    }
    //check for colission with wall
    public boolean wallBang(SetupWall setupWall){
        try {
            setupWall.mapForWall();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        Rectangle col_check_rect = new Rectangle(latestX, latestY, retWidth()- intN, retHeight()- intN);
        for(int xAxis = 0; xAxis < height_forMap; xAxis++){
            for(int yAxis = 0; yAxis < width_forMap; yAxis++){
                if(setupWall.r_mapWall()[xAxis][yAxis].equals("1")|| setupWall.r_mapWall()[xAxis][yAxis].equals("2")){ //for breakable and non breakable wall
                    Rectangle col_check_rect_wall = new Rectangle(yAxis * setupWall.retWidth(), xAxis * setupWall.retHeight(), setupWall.retWidth()- intN, setupWall.retHeight()- intN);

                    if(col_check_rect.intersects(col_check_rect_wall)){ //return true if there is colission
                        return true;
                    }
                }
            }
        }
        return false;
    }
    //to move the tank backwards
    public void B_move(){
        latestX = this.retriveImgX() - (int) (Math.cos(Math.toRadians(slantArr[indicator])) * intN);
        latestY = this.retrieveImgY() + (int) (Math.sin(Math.toRadians(slantArr[indicator])) * intN);
    }
    //to move the tank left
    public void l_move(){
        if(indicator <59) {
            indicator++;
        }
        else {
            indicator =0;
        }
        bufImage = pic_obj_direction.getFrame(indicator);
    }
    //to move the tank right
    public void R_move(){
        if(indicator >0){
            indicator--;
        }
        else {
            indicator =59;
        }
        bufImage = pic_obj_direction.getFrame(indicator);
    }
    //to make the tank shoot on xaxis
    public int x_shoot(){
        return this.x_objclass + this.retWidth()/2+(int) ((int)(this.retWidth()/2*(Math.cos(Math.toRadians(50))))*2*(Math.cos(Math.toRadians(this.slantArr[indicator]))));
    }
    //to make the tank shoot on yaxis
    public int y_shoot(){
        return this.y_objclass + this.retHeight()/2-(int) ((int)(this.retHeight()/2*(Math.cos(Math.toRadians(50))))*2*(Math.sin(Math.toRadians(this.slantArr[indicator]))));
    }
    //to move tank forward
    public void F_move(){
        latestX = this.retriveImgX()+(int) (Math.cos(Math.toRadians(slantArr[indicator])) * intN);
        latestY = this.retrieveImgY()-(int) (Math.sin(Math.toRadians(slantArr[indicator])) * intN);
    }
    //draw to screen
    @Override
    public void render(Graphics g){
        g.drawImage(pic_obj_direction.getFrame(indicator), x_objclass, y_objclass, imgObs);
    }
    public int rXAxisImg(){
        return this.latestX;
    }
    public int rYAxisImg(){
        return this.latestY;
    }
    public int gIndicator(){
        return indicator;
    }
    public int[] getSlantArr(){
        return slantArr;
    }
}

