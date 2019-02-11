import javax.sound.midi.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLOutput;
import java.util.*;

public class Picture extends SimplePicture {

    ArrayList<Integer> notes = new ArrayList<Integer>(); //stores notes + keys that are close in scale to make song more smooth and realistic
    ArrayList<Integer> ref = new ArrayList<Integer>(); // stores each pixel's piano note equivalent made in the converter methods

    public Picture(String fileName) {
        // let the parent class handle this fileName
        super(fileName);
    }

    public void makeRef(int min, int max) {
        int tempNum = min;
        ref.add(tempNum);

        while (tempNum < max) {
            for (int i = 0; i < 2; i++) {
                tempNum += 2;
                ref.add(tempNum);
            }

            tempNum++;
            ref.add(tempNum);

            for (int i = 0; i < 3; i++) {
                tempNum += 2;
                ref.add(tempNum);
            }

            tempNum++;
            ref.add(tempNum);
        }

        //IDEA: Check if arrayList contains max, if not add it to the arrayList
    }


    /**
     * Constructor that takes the width and height
     *
     * @param height the height of the desired picture
     * @param width  the width of the desired picture
     */
    public Picture(int height, int width) {
        // let the parent class handle this width and height
        super(width, height);
    }

    /**
     * Default Option: uses the avg of the three RGB values in each
     * pixel to add a note to the notes arrayList
     */
    public void defaultConverter() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                if (pixelObj.getRed() > pixelObj.getBlue() && pixelObj.getRed() > pixelObj.getGreen()) // finds the most dominant color.
                {
                    notes.add(((pixelObj.getRed()) / 10) + 35);  // Uses a formula that takes the most dominant color's value and converts it to a piano key.
                }                                        // Red represents piano keys 35-60 Green: 55-80 blue: 70-95 The scales overlap to produce a better sound
                else                                     // If all colors are equal it converts the average of the colors ranging from 30-81
                {
                    if (pixelObj.getGreen() > pixelObj.getRed() && pixelObj.getGreen() > pixelObj.getBlue()) {
                        notes.add(((pixelObj.getGreen()) / 10) + 55);
                    } else {
                        if (pixelObj.getBlue() > pixelObj.getRed() && pixelObj.getBlue() > pixelObj.getGreen()) {
                            notes.add(((pixelObj.getBlue()) / 10) + 70);
                        } else {
                            notes.add((((pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue()) / 15) + 30));

                        }
                    }
                }
            }
        }

    }

    public void reversedConverter() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                if (pixelObj.getBlue() > pixelObj.getRed() && pixelObj.getBlue() > pixelObj.getGreen())// finds the most dominant color.
                {
                    notes.add(((pixelObj.getBlue()) / 10) + 35);  // Uses a formula that takes the most dominant color's value and converts it to a piano key.
                }                                        //Reverse of default converter Blue represents piano keys 35-60 Green: 55-80 Red: 75-100 The scales overlap to produce a better sound
                else                                     // If all colors are equal it converts the average of the colors ranging from 30-81
                {
                    if (pixelObj.getGreen() > pixelObj.getRed() && pixelObj.getGreen() > pixelObj.getBlue()) {
                        notes.add(((pixelObj.getGreen()) / 10) + 55);
                    } else {
                        if (pixelObj.getRed() > pixelObj.getBlue() && pixelObj.getRed() > pixelObj.getGreen()) {
                            notes.add(((pixelObj.getRed()) / 10) + 70);
                        } else {
                            notes.add((((pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue()) / 15) + 30));

                        }
                    }
                }
            }
        }

    }

    public void song() {

        this.write(this.getFileName());

        for (int i = this.getArray().size() - 1; i >= 0; i--) //clears notes array
        {
            this.getArray().remove(i);
        }

        this.defaultConverter(); //converts pixels to notes
        this.clean();            //makes song sound realistic by replacing it with a more complementary key


        for (int i = 0; i < this.getArray().size(); i++) //loops through array and plays each key
        {
            try {
                System.out.println(this.getArray().get(i));

                Synthesizer midiSynth = MidiSystem.getSynthesizer();
                midiSynth.open();
                Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
                MidiChannel[] mChannels = midiSynth.getChannels();
                midiSynth.loadInstrument(instr[0]);//load an instrument
                mChannels[0].noteOn((int) this.getArray().get(i), 1000);

            } catch (MidiUnavailableException e) {
                System.out.println("unavailable");
            }

            try {
                Thread.sleep(200); // wait time in milliseconds to control duration
            } catch (InterruptedException e) {
                System.out.println("CATCH");
            }
        }
    }


    /**
     * First Option: uses the red RGB value in each pixel
     * to add a note to the notes arrayList
     */
    public void redConverter() { //pays attention to only the red values. Larger red value == higher key on piano
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                notes.add(((pixelObj.getRed()) / 4) + 35);
            }
        }


    }

    /**
     * Second Option: uses the green RGB value in each pixel
     * to add a note to the notes arrayList
     */
    public void greenConverter() { //pays attention to only the green values. Larger green value == higher key on piano
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                notes.add((pixelObj.getGreen() / 4) + 35);
            }
        }


    }

    /**
     * Third Option: uses the blue RGB value in each pixel
     * to add a note to the notes arrayList
     */
    public String blueConverter() //pays attention to only the blue values. Larger blue value == higher key on piano
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                notes.add((pixelObj.getBlue() / 4) + 35);
            }
        }
        return ("using only blue values");
    }

    public void clean() {

        for (int i = 0; i < notes.size(); i++) {
            for (int a = ref.size() - 1; a >= 0; a--) {
                if (notes.get(i) > ref.get(a)) {
                    notes.set(i, ref.get(a));
                    a = -1;
                }
            }
        }
    }


    /**
     * Method to return a string with information about this picture.
     *
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString() {
        String output = "Picture, filename " + getFileName() +
                " height " + getHeight()
                + " width " + getWidth();
        return output;
    }

    public ArrayList<Integer> getArray() {
        return notes;
    }

}