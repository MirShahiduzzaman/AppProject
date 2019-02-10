import javax.sound.midi.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLOutput;
import java.util.*;

public class Picture extends SimplePicture {

    ArrayList<Integer> notes = new ArrayList<Integer>();
    ArrayList<Integer> ref = new ArrayList<Integer>();
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

    public void makeRef(int min, int max)
    {
        int tempNum = min;
        ref.add(tempNum);

        while(tempNum<max)
        {
            for(int i = 0;i<2;i++)
            {
                tempNum += 2;
                ref.add(tempNum);
            }

            tempNum++;
            ref.add(tempNum);

            for(int i = 0;i<3;i++)
            {
                tempNum += 2;
                ref.add(tempNum);
            }

            tempNum++;
            ref.add(tempNum);
        }

        //IDEA: Check if arrayList contains max, if not add it to the arrayList
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
    public String defaultConverter()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                if(pixelObj.getRed() > pixelObj.getBlue() &&  pixelObj.getRed() > pixelObj.getGreen())
                {
                notes.add(((pixelObj.getRed())/10)+35);
                }
                else
                {
                    if(pixelObj.getBlue() > pixelObj.getRed() &&  pixelObj.getBlue() > pixelObj.getGreen())
                    {
                        notes.add(((pixelObj.getRed())/10)+55);
                    }
                    else
                    {
                        if(pixelObj.getGreen() > pixelObj.getRed() &&  pixelObj.getGreen() > pixelObj.getBlue())
                        {
                            notes.add(((pixelObj.getRed())/10)+75);
                        }
                        else
                        {
                            notes.add((((pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue()) / 15) +30));

                        }
                    }
                }
            }
        }
        return("using all RGB values");
    }

    public void inOrderSong()
    {
        String message = "";
        this.write(this.getFileName());

        for (int i=this.getArray().size()-1; i >= 0; i--)
        {
            this.getArray().remove(i);
        }

        message += this.defaultConverter();
        this.clean();


      //   message += " " + smallP.clean(smallP.getArray());
        System.out.print(message);

        for (int i=0; i < this.getArray().size(); i++)
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
     *               to add a note to the notes arrayList
     */
    public String redConverter() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                notes.add(((pixelObj.getRed())/4)+35);
            }
        }
        return("using only red values");

    }

    /**
     * Second Option: uses the green RGB value in each pixel
     *                to add a note to the notes arrayList
     */
    public String greenConverter() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                notes.add((pixelObj.getGreen()/4)+35);
            }
        }
        return("using only green values");

    }

    /**
     * Third Option: uses the blue RGB value in each pixel
     *               to add a note to the notes arrayList
     */
    public String blueConverter()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                notes.add(( pixelObj.getBlue()/4)+35);
            }
        }
        return("using only blue values");
    }

    public void clean()
    {

        for (int i = 0; i < notes.size(); i++)
        {
            for (int a = ref.size()-1; a >=0; a--)
            {
                if (notes.get(i) > ref.get(a))
                {
                    notes.set(i, ref.get(a));
                    a=-1;
                }
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

    public ArrayList<Integer> getArray()
    {
        return notes;
    }

}