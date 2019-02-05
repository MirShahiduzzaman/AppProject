import java.awt.image.BufferedImage;
import java.util.*;

public class Picture extends SimplePicture {

    ArrayList<Integer> notes = new ArrayList<Integer>();

    public Picture(BufferedImage image) {
        super(image);
    }

    /**
     * Default Option: uses the avg of the three RGB values in each
     *                 pixel to add a note to the notes arrayList
     */
    public void defaultConverter() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                notes.add((pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue()) / 3);
            }
        }
    }

    /**
     * First Option: uses the red RGB value in each pixel
     *               to add a note to the notes arrayList
     */
    public void RedConverter() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                notes.add((pixelObj.getRed()));
            }
        }
    }

    /**
     * Second Option: uses the green RGB value in each pixel
     *                to add a note to the notes arrayList
     */
    public void GreenConverter() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                notes.add((pixelObj.getGreen()));
            }
        }
    }

    /**
     * Third Option: uses the blue RGB value in each pixel
     *               to add a note to the notes arrayList
     */
    public void BlueConverter() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                notes.add((pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue()) / 3);
            }
        }
    }

    public void keepOnlyRed() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setBlue(0);
                pixelObj.setGreen(0);
            }
        }
    }

    public void keepOnlyGreen() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setRed(0);
                pixelObj.setBlue(0);
            }
        }
    }
}