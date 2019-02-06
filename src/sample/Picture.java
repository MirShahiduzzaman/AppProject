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
    private ArrayList<pixel> col ;
    private ArrayList<pixel> row ;
    int width;
    int height;

    public Image picelate(Image Import,int boxCount){

        PixelReader imgReader = new PixelReader() {
            @Override
            public PixelFormat getPixelFormat() {
                return null;
            }

            @Override
            public int getArgb(int x, int y) {
                return 0;
            }

            @Override
            public Color getColor(int x, int y) {
                return null;
            }

            @Override
            public <T extends Buffer> void getPixels(int x, int y, int w, int h, WritablePixelFormat<T> pixelformat, T buffer, int scanlineStride) {

            }

            @Override
            public void getPixels(int x, int y, int w, int h, WritablePixelFormat<ByteBuffer> pixelformat, byte[] buffer, int offset, int scanlineStride) {

            }

            @Override
            public void getPixels(int x, int y, int w, int h, WritablePixelFormat<IntBuffer> pixelformat, int[] buffer, int offset, int scanlineStride) {

            }
        }

        width = boxCount;
        height = boxCount;

        col = new ArrayList<>(width);
        row = new ArrayList<>(height);

        for(int i = 0; i<col.size();i++){

            imgReader.getPixels();

            for(int j=0;j<row.size();j++){



            }
        }

        return Import;
    }
}
