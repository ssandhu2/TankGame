package wall;

import beingInherited.ObjClass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SetupWall extends ObjClass {
    private int height_minMap =30;
    private int width_minMap =40;
    private String [][] mapArr = new String[height_minMap][width_minMap]; //array to setup the map
    private BufferedImage nonBreakable_wall; //image for non breakable wall
    private BufferedReader buffR_map;

    public SetupWall(String param_wall) throws IOException{
        super(param_wall);
        buffR_map = new BufferedReader(new FileReader("Resources/background.txt")); //read the text file using bufferreader
        nonBreakable_wall = ImageIO.read(new File("Resources/Wall2.gif")); //wall 2
    }

    public void mapForWall() throws IOException{
        int loop_x =0;
        String to_read_line;

        while ((to_read_line = buffR_map.readLine()) != null){ //read line until it's empty
            for (int loop_y = 0; loop_y < mapArr[loop_x].length; loop_y++){
                mapArr[loop_x][loop_y] = String.valueOf(to_read_line.charAt(loop_y));
            }
            loop_x++;
        }
    }
    //draw map to screen
    @Override
    public void render(Graphics g){
        for(x_objclass =0; x_objclass < width_minMap; x_objclass++){
            for(y_objclass =0; y_objclass < height_minMap; y_objclass++){
                if(mapArr[y_objclass][x_objclass].equals("1")){ //if one draw non breakable walls
                    g.drawImage(nonBreakable_wall, x_objclass * nonBreakable_wall.getWidth(), y_objclass * nonBreakable_wall.getHeight(), imgObs);
                } else if(mapArr[y_objclass][x_objclass].equals("2")){ //if two draw breakable walls
                    g.drawImage(bufImage, x_objclass * bufImage.getWidth(), y_objclass * bufImage.getHeight(), imgObs);
                }
            }
        }
    }
    //return maparray to be used in the other classes
    public String [][] r_mapWall(){
        return mapArr;
    }

    //updated map
    public void latestMapWall(String [][] l_map_wall){
        this.mapArr = l_map_wall;
    }
}


