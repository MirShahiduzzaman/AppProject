package sample;

import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.paint.Color;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

public class Picture {

    private int rgbValue;
    private ArrayList<Pixel> col ;
    private ArrayList<Pixel> row ;
    int width;
    int height;

    public Picture(){
        super();
    }

    public Image pixelate(Image Import,int boxCount){

        width = boxCount;
        height = boxCount;

        Pixel[][] pixels = getPixels2D();

        byte[] buffer = new byte[]{10,10,10};
        int offset = 0;

        for(int i = 0; i<col.size();i++){


            for(int j=0;j<row.size();j++){



            }
        }

        return Import;
    }
}
