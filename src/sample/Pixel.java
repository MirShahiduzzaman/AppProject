package sample;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class Pixel implements DigitalPicture{

    private DigitalPicture picture;
    private int x;
    private int y;


    public Pixel(DigitalPicture picture, int x, int y)
    {
        // set the picture
        this.picture = picture;

        // set the x location
        this.x = x;

        // set the y location
        this.y = y;

    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getRow() { return y; }
    public int getCol() { return x; }

    public int getAlpha() {

        /* get the value at the location from the picture as a 32 bit int
         * with alpha, red, green, blue each taking 8 bits from left to right
         */
        int value = picture.getBasicPixel(x,y);

        // get the alpha value (starts at 25 so shift right 24)
        // then and it with all 1's for the first 8 bits to keep
        // end up with from 0 to 255
        int alpha = (value >> 24) & 0xff;

        return alpha;
    }


    public String getFileName(){return "bleh";}
    public String getTitle(){return "bleh";}
    public void setTitle(String title){}
    public int getWidth() {return 0;}
    public int getHeight() {return 0;}
    public Image getImage(){Image l = new Image() {
        @Override
        public int getWidth(ImageObserver observer) {
            return 0;
        }

        @Override
        public int getHeight(ImageObserver observer) {
            return 0;
        }

        @Override
        public ImageProducer getSource() {
            return null;
        }

        @Override
        public Graphics getGraphics() {
            return null;
        }

        @Override
        public Object getProperty(String name, ImageObserver observer) {
            return null;
        }
    };
    return l;}
    public int getBasicPixel(int x, int y){return 0;}
    public void setBasicPixel(int x, int y, int rgb){}
    public Pixel getPixel(int x, int y){return this;}
    public Pixel[] getPixels(){
        Pixel[] pixelArray = new Pixel[]{};
        return pixelArray;
    }
    public Pixel[][] getPixels2D() {
        int width = getWidth();
        int height = getHeight();
        Pixel[][] pixelArray = new Pixel[height][width];

        // loop through height rows from top to bottom
        for (int row = 0; row < height; row++)
            for (int col = 0; col < width; col++)
                pixelArray[row][col] = new Pixel(this,col,row);

        return pixelArray;
    }
    public void load(Image image){}
    public boolean load(String fileName){return false;}
    public void show(){}
    public void explore(){}
    public boolean write(String fileName){return false;}

}
