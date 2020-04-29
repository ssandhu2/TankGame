package beingInherited;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//constructor
public class Picture {
    protected BufferedImage[] bf_arr;
    protected int pc_size;
    protected String fileName;

//constructor with filename and size
    public Picture(String fileName, int pc_size) throws IOException{
        this.pc_size = pc_size;
        this.fileName = fileName;
        this.loadImages();
    }
    private void loadImages() throws IOException{
        BufferedImage bufferedImg = ImageIO.read(new File(fileName)); //read file
        this.bf_arr = new BufferedImage[bufferedImg.getWidth()/ pc_size];
        for(int i = 0; i <this.bf_arr.length; i++){ //loop
            this.bf_arr[i] = bufferedImg.getSubimage(i *this.pc_size,0,this.pc_size,this.pc_size); //make subimage
        }
    }
    //bufferedimage method
    public BufferedImage getFrame(int frame){
        return this.bf_arr[frame];
    }
    public int frameCount(){
        return this.bf_arr.length;
    }

}
