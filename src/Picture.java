import javax.sound.midi.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class Picture extends SimplePicture {

    ArrayList<Integer> notes = new ArrayList<Integer>();
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes a file name and creates the picture
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName)
    {
        // let the parent class handle this fileName
        super(fileName);
    }

public void printNotes()
{
    for(int x: notes)
    System.out.println(x);
}
    /**
     * Constructor that takes the width and height
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int height, int width)
    {
        // let the parent class handle this width and height
        super(width,height);
    }

    /**
     * Default Option: uses the avg of the three RGB values in each
     *                 pixel to add a note to the notes arrayList
     */
    public void defaultConverter() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                notes.add(((pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue()) / 6) +10);
            }
        }
    }
    public static void song()
    {
        Picture pix = new Picture("jellyfish.jpg");
        Picture smallP = pix.scale(1,1);
        smallP.write("jellyfish.jpg");
        smallP.defaultConverter();
        smallP.song();
    int bass = 20;
    for(int x:notes)
    {
        if(x<bass && bass>20)
        {
            int bassCount = 0;
            while(bassCount < 50)
            {
                try {
                    Synthesizer midiSynth = MidiSystem.getSynthesizer();
                    midiSynth.open();
                    Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
                    MidiChannel[] mChannels = midiSynth.getChannels();
                    midiSynth.loadInstrument(instr[0]);//load an instrument
                    mChannels[0].noteOn(bass, 300);//On channel 0, play note number 60 with velocity 100
                    try {
                        Thread.sleep(12); // wait time in milliseconds to control duration
                        System.out.println(bass);
                    } catch (InterruptedException e) {
                        System.out.println("CATTCH");
                    }
                } catch (MidiUnavailableException e) {
                    System.out.println("unavible");
                }
                bassCount++;
            }
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
                notes.add((pixelObj.getRed())/3);
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
                notes.add((pixelObj.getGreen())/3);
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

    public static void smoothMusic(String picName)
    {
        Picture pix = new Picture(picName);
        Picture smallP = pix.scale(1,1);
        smallP.write(picName);
        smallP.defaultConverter();
        System.out.println();
        //smallP.printNotes();
        for (int i=0; i < smallP.getArray().size(); i++)
        {
            try {
                Synthesizer midiSynth = MidiSystem.getSynthesizer();
                midiSynth.open();
                Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
                MidiChannel[] mChannels = midiSynth.getChannels();
                midiSynth.loadInstrument(instr[0]);//load an instrument
                mChannels[0].noteOn((int)smallP.getArray().get(i), 300);//On channel 0, play note number 60 with velocity 100
                try {
                    Thread.sleep(12); // wait time in milliseconds to control duration
                    System.out.println((int)smallP.getArray().get(i));
                } catch (InterruptedException e) {
                    System.out.println("CATTCH");
                }
            } catch (MidiUnavailableException e) {
                System.out.println("unavible");
            }
        }
    }

    /**
     * Method to return a string with information about this picture.
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString()
    {
        String output = "Picture, filename " + getFileName() +
                " height " + getHeight()
                + " width " + getWidth();
        return output;

    }

    public ArrayList getArray()
    {
        return notes;
    }

}