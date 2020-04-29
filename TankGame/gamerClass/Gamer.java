package gamerClass;
import Tank.Tank;
import wall.SetupWall;

import java.awt.*;

public class Gamer {
    private String location_tank;
    private int x_loc_tank;
    private int y_loc_tank;
    private int tank_total_strength =500; //tank strength
    private int tank_total_lives =5; //5 lifes
    private int tank_tally =0; //scores
    private Tank my_tank;
    private SetupWall setupWall;
    private int map_loc_y = 30;
    private int map_loc_x = 40;
    private boolean game_finish = false;
    //constructor
    public Gamer(Tank my_tank, SetupWall setupWall, String location_tank){

        this.my_tank = my_tank;
        this.setupWall = setupWall;
        this.location_tank = location_tank;
    }

    //array for map with 30 height and 40 width
    public void location_fix(){
        for(int xAxis = 0; xAxis < map_loc_y; xAxis++){
            for(int yAxis = 0; yAxis < map_loc_x; yAxis++){
                if(setupWall.r_mapWall()[xAxis][yAxis].equals(location_tank)){//setupwall
                    x_loc_tank = yAxis * setupWall.retWidth();
                    y_loc_tank = xAxis * setupWall.retHeight();
                }
            }
        }

        my_tank.xAxisImg(x_loc_tank);
        my_tank.YaxisImg(y_loc_tank);

    }

    //to display lives
    public void tankTotalLives(Graphics g, int x_param, int y_param){
        int component =50;
        g.setColor(Color.black); //black oval
        for(int tankLive = 0; tankLive < tank_total_lives -1; tankLive++){
            g.fillOval(x_param + tankLive * component, y_param,30,30); //lives displayed in ovals
        }
        if(tank_total_lives ==0 ){ //if there are no lifes left
            this.game_finish = true;
            g.setColor(Color.red); //display in red
            Font objFont = g.getFont().deriveFont(80.0f);
            g.setFont(objFont);
            g.drawString("Game Over", 425, 450); //game over
        }
    }
    //display points in black
    public void onScreenPoints(Graphics g, int x_param, int y_param){
        g.setColor(Color.black);
        Font font = g.getFont().deriveFont(50.0f); //in 50 font
        g.setFont(font);
        g.drawString(Integer.toString(tank_tally), x_param, y_param);
    }
    public void tankStrength(Graphics g, int x_param, int y_param){
        if(tank_total_strength >300){ //if strength over 300 the blue
            g.setColor(Color.blue);
            g.fillRect(x_param, y_param, tank_total_strength,40);
        }
        else if(tank_total_strength >100 && tank_total_strength <=300){ //if strength less than 300 orange
            g.setColor(Color.orange);
            g.fillRect(x_param, y_param, tank_total_strength,40);
        }
        else if(tank_total_strength >0 && tank_total_strength <=100){
            g.setColor(Color.DARK_GRAY);
            g.fillRect(x_param, y_param, tank_total_strength,40);
        }
        else {
            tank_total_lives -=1; //end one life
            tank_total_strength =500;//respawan tank with 500 strength
        }
    }


    public void bangTank(){
        this.tank_total_strength -=100;
    } //everyhit subtract 100

    public void tallyUp(){
        tank_tally +=1;
    } //add points
    public boolean gameEnd(){
        return game_finish;
    }

    public boolean zeroStrength(){
        return tank_total_strength ==0;
    }

}



