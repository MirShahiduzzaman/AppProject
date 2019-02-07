package sample;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface DigitalPicture {

    public String getFileName(); // get the file name that the picture came from
    public String getTitle(); // get the title of the picture
    public void setTitle(String title); // set the title of the picture
    public int getWidth(); // get the width of the picture in pixels
    public int getHeight(); // get the height of the picture in pixels
    public Image getImage(); // get the image from the picture
    public int getBasicPixel(int x, int y); // get the pixel information as an int
    public void setBasicPixel(int x, int y, int rgb); // set the pixel information
    public Pixel getPixel(int x, int y); // get the pixel information as an object
    public Pixel[] getPixels(); // get all pixels in row-major order
    public Pixel[][] getPixels2D(); // get 2-D array of pixels in row-major order
    public void load(Image image); // load the image into the picture
    public boolean load(String fileName); // load the picture from a file
    public void show(); // show the picture
    public void explore(); // explore the picture
    public boolean write(String fileName); // write out a file
}
