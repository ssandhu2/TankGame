package beingInherited;


import gamerClass.Gamer;
import Tank.Tank;
import wall.SetupWall;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static javax.imageio.ImageIO.read;

public abstract class Panel extends JPanel implements Runnable{

    public String myBackground = "Resources/Background.bmp"; //background image
    public String tank_img = "Resources/mytank.png"; //tank image
    public final int window_width = 1280; //window height
    public final int window_height = 960; //window width
    public final int height_panel = 680;
    public Dimension dm; //dimesnsion object
    public ObjClass bck;
    public Gamer gamer_1; //object gamer class
    public Gamer gamer_2;
    public Tank firstTank, secondTank; // Tank class object
    public SetupWall breakable_Setup_wall;//breakable wall
    public Graphics graphic_draw; //Graphics class
    public ArrayList<Action> action_arrayL; //arraylist
    public BufferedImage buf_sm_img = new BufferedImage(window_width, window_height, BufferedImage.TYPE_INT_RGB); //buffered image to store minimap
    public BufferedImage firstTankCam = new BufferedImage(window_width, window_height, BufferedImage.TYPE_INT_RGB); //buffered image to store cam
    public BufferedImage secondTankCam = new BufferedImage(window_width, window_height, BufferedImage.TYPE_INT_RGB);

    //constructor
    public Panel(){
        try {
            this.bck = new ObjClass(myBackground);
            this.firstTank = new Tank(tank_img);
            this.secondTank = new Tank(tank_img);
            this.breakable_Setup_wall = new SetupWall("Resources/Wall1.gif");
            this.breakable_Setup_wall.mapForWall();
            gamer_1 = new Gamer(firstTank, breakable_Setup_wall, "3");
            gamer_2 = new Gamer(secondTank, breakable_Setup_wall, "4");
            gamer_1.location_fix();
            gamer_2.location_fix();
        }

       catch (Exception e){
            System.out.println("ERROR LOADING WALL");
       }
        this.action_arrayL = new ArrayList<>();
        this.dm = new Dimension(window_width, height_panel);
    }

    //paint method
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        graphic_draw = buf_sm_img.createGraphics();
        for(int i_loop = 0; i_loop < window_width; i_loop += bck.retWidth()){ //run loop from window height to width
            for(int j_loop = 0; j_loop < window_height; j_loop += bck.retHeight()){
                bck.xAxisImg(i_loop);
                bck.YaxisImg(j_loop);
                bck.render(graphic_draw); //draw
            }
        }
        Action actObj;
        for(int ctr = 0; ctr < action_arrayL.size(); ctr++){
            actObj = action_arrayL.get(ctr);
            if(actObj.ifEnded()){
                action_arrayL.remove(ctr); //to remove from arraylist
            }
            else {
                actObj.render(graphic_draw); //to draw to offscreen
            }
        }
        breakable_Setup_wall.render(graphic_draw);
        firstTank.render(graphic_draw); //calling render method
        secondTank.render(graphic_draw);
        graphic_draw = g;

        firstTankCam = buf_sm_img.getSubimage(xPosTank(firstTank), yPosTank(firstTank), window_width /2, height_panel);
        secondTankCam = buf_sm_img.getSubimage(xPosTank(secondTank), yPosTank(secondTank), window_width /2, height_panel);

        graphic_draw.drawImage(firstTankCam,0,0, window_width /2, height_panel,this); //tank cam postion

        graphic_draw.drawImage(secondTankCam, window_width /2,0, window_width /2, height_panel,this);

        graphic_draw.drawImage(buf_sm_img, window_height /2-400,400,350,250,this); //minimap position


        gamer_1.tankStrength(graphic_draw,35, height_panel -670); //tank1 total strength position
        gamer_2.tankStrength(graphic_draw, window_width /2+10, height_panel -35);


        gamer_1.tankTotalLives(graphic_draw,35, height_panel-630);
        gamer_2.tankTotalLives(graphic_draw, window_width /2+20, height_panel -70); //total lifes position


        gamer_1.onScreenPoints(graphic_draw, 35, height_panel -640); //displays point at the certified location
        gamer_2.onScreenPoints(graphic_draw, window_width -200, height_panel -10);
    }

    //getter
    @Override
    public Dimension getPreferredSize(){
        return this.dm;
    }


    //for getting the Y position
    public int yPosTank(Tank tankPar){
        int positionY;
        positionY= tankPar.retrieveImgY()- height_panel /2;
        if(positionY>(window_height - height_panel)){
            positionY = (window_height - height_panel);
        }
        if(positionY<0){
            positionY=0;
        }

        return positionY;
    }
    //to get Xposition
    public int xPosTank(Tank tankPar){
        int xPos;
        xPos = tankPar.retriveImgX()- window_width /4;
        if(xPos >(window_width /2)){
            xPos = window_width /2;
        }
        if(xPos <0){
            xPos =0;
        }

        return xPos;
    }
    //to add to arraylist
    public void doAction(Action actObj){
        action_arrayL.add(actObj);
    }
}
