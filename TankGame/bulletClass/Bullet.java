package bulletClass;
import beingInherited.Panel;
import beingInherited.Action;
import gamerClass.Gamer;
import beingInherited.Picture;
import Tank.Tank;
import wall.SetupWall;
import java.awt.*;
import java.io.IOException;

public class Bullet extends Action {
    //initializing variables
    private int xVar, yVar;
    private int [] arrayAng;
    private Picture destroyed, shellGuide;
    private int indicator;
    private int miniMapY = 30;
    private int miniMapX = 40;
    private int firstRectX =15;
    private int secondRectX =20;
    private int firstRectY =15;
    private int secondRectY =20;
    private final int velocity = 30;
    private Panel objPanel;
    private Gamer gamer1, gamer2;
    private Tank firstTank;
    private Tank secondTank;
    private SetupWall setupWall;

    //constructor with the following parameters
    public Bullet(Panel objPanel, Tank firstTank, Tank secondTank, Gamer gamer1, Gamer gamer2, SetupWall setupWall, Picture shellGuide, int xVar, int yVar, int frame_delay, boolean reCircle){
        super(shellGuide, xVar, yVar,frame_delay,reCircle);
        this.firstTank = firstTank;
        this.secondTank = secondTank;
        this.setupWall = setupWall;
        this.gamer1 = gamer1;
        this.gamer2 = gamer2;
        this.arrayAng = firstTank.getSlantArr();
        this.indicator = firstTank.gIndicator();
        this.objPanel = objPanel;
        this.xVar = xVar;
        this.yVar = yVar;
        this.shellGuide = shellGuide;
        try {
            destroyed = new Picture("Resources/mysmallexplosion.png", 24); //load image using the picture class
        }
        catch (IOException e){
            System.out.println("Can't load explosion image");
        }

    }
    public void bumpedTank(){
        Rectangle rectShell = new Rectangle(xVar, yVar, firstRectX, firstRectY);
        Rectangle firstTankRect = new Rectangle(firstTank.retriveImgX(), firstTank.retrieveImgY(), firstTank.retWidth()-10, firstTank.retHeight()-10); //rectangle with image, height and width
        Rectangle secondTankRect = new Rectangle(secondTank.retriveImgX(), secondTank.retrieveImgY(), secondTank.retWidth()-10, secondTank.retHeight()-10);//rectangle to check for colission

        if(rectShell.intersects(firstTankRect)){ //if colission happens  with tank
            gamer1.bangTank(); //call method
            if(gamer1.zeroStrength()){ //if dies
                gamer2.tallyUp();
                destroyedTank(firstTank);
                gamer1.location_fix();
                firstTank.indc(0);
            }
        }
        if(rectShell.intersects(secondTankRect)){ //if tank2 hits
            gamer2.bangTank();
            if(gamer2.zeroStrength()){
                gamer1.tallyUp();
                destroyedTank(secondTank);
                gamer2.location_fix();
                secondTank.indc(0);
            }
        }

    }
    //to check for colission with call
    public void wallBang() {
        try {
            setupWall.mapForWall();//map for wall
        } catch (IOException e) {
            e.printStackTrace();
        }
        Rectangle rectShellCheck = new Rectangle(xVar, yVar, secondRectX, secondRectY); //new retangle
        for (int xAxis = 0; xAxis < miniMapY; xAxis++) { // loop through map
            for (int yAxis = 0; yAxis < miniMapX; yAxis++) {
                if(setupWall.r_mapWall()[xAxis][yAxis].equals("2")){ //breakable wall
                    Rectangle rectWall = new Rectangle(yAxis * setupWall.retWidth(), xAxis * setupWall.retHeight(), setupWall.retWidth(), setupWall.retHeight());// new rectangle
                    if(rectShellCheck.intersects(rectWall)){ //if colission with wall
                        setupWall.r_mapWall()[xAxis][yAxis] = "0";
                        setupWall.latestMapWall(setupWall.r_mapWall());
                    }
                }

            }
        }
    }
    //returns if there is colission
    public boolean wallBangCheck(){
        try {
            setupWall.mapForWall();
        }
        catch (IOException e){
            System.out.println("Map error");
        }

        Rectangle rectShellCheck = new Rectangle(xVar, yVar, secondRectX, secondRectY); //rectangle

        for(int xAxis = 0; xAxis < miniMapY; xAxis++){
            for(int yAxis = 0; yAxis < miniMapX; yAxis++){
                if(setupWall.r_mapWall()[xAxis][yAxis].equals("1")|| setupWall.r_mapWall()[xAxis][yAxis].equals("2")){ //1 for non breakbale wall and 2 for breakable wall
                    Rectangle rectWall = new Rectangle(yAxis * setupWall.retWidth(), xAxis * setupWall.retHeight(), setupWall.retWidth(), setupWall.retHeight());
                    if(rectShellCheck.intersects(rectWall)){//colission check
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void shellUp(){

        yVar -= (int) (Math.sin(Math.toRadians(arrayAng[indicator])) * velocity);//guide bullet
        xVar += (int) (Math.cos(Math.toRadians(arrayAng[indicator])) * velocity); //guide bullet y axis

    }

    public void destroyedTank(Tank deadTank){
        objPanel.doAction(new Action(this.destroyed, deadTank.retriveImgX(), deadTank.retrieveImgY(),4,false));
    }
    //if tank bump into each other
    public boolean tankBumpCheck(){
        Rectangle rectShell = new Rectangle(xVar, yVar, firstRectX, firstRectY);
        Rectangle firstTankRect = new Rectangle(firstTank.retriveImgX(), firstTank.retrieveImgY(), firstTank.retWidth()-12, firstTank.retHeight()-12);
        Rectangle secondTankRect = new Rectangle(secondTank.retriveImgX(), secondTank.retrieveImgY(), secondTank.retWidth()-12, secondTank.retHeight()-12);

        return (rectShell.intersects(firstTankRect)|| rectShell.intersects(secondTankRect));
    }
    //draw to screen
    public void render(Graphics g){
        if(end == false){
            shellUp();
            g.drawImage(shellGuide.getFrame(indicator), xVar, yVar,null);
            if(tankBumpCheck()==true){
                bumpedTank();
                end = true;
            }
            if(wallBangCheck()== true){
                wallBang();
                end = true;
            }
        }
    }
}



