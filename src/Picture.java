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

    public static void song(String picName)
    {
        Picture pix = new Picture(picName);
        Picture smallP = pix.scale(1,1);
        smallP.write(picName);
        smallP.defaultConverter();
        insertionSort(smallP.getArray());
        int bassLow = 25;
        int bassHigh = 35;
        int bass = 25;
        int notePos = 0;
        while((notePos <= smallP.getArray().size()-1) && bassHigh > (int)smallP.getArray().get(notePos))
        {
            if(bassLow < (int)smallP.getArray().get(notePos))
            {
                bass = (int)smallP.getArray().get(notePos);
            }
            notePos++;
        }
        int bassCount = 0;
                while (bassCount < 10) {
                    System.out.println(bassCount%3);

//                    try {
//                        Synthesizer midiSynth = MidiSystem.getSynthesizer();
//                        midiSynth.open();
//                        Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
//                        MidiChannel[] mChannels = midiSynth.getChannels();
//                        midiSynth.loadInstrument(instr[0]);//load an instrument
//                        mChannels[2].noteOn(bass, 500);//On channel 0, play note number 60 with velocity 100
//
//                    } catch (MidiUnavailableException e) {
//                        System.out.println("unavible");
//                    }

                    if (bassCount%10 == 3) {
                        try {
                            Synthesizer midiSynth = MidiSystem.getSynthesizer();
                            midiSynth.open();
                            Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
                            MidiChannel[] mChannels = midiSynth.getChannels();
                            midiSynth.loadInstrument(instr[0]);//load an instrument
                            mChannels[0].noteOn(((int)smallP.getArray().get(smallP.getArray().size() / 2)), 1000);//On channel 0, play note number 60 with velocity 100
                            System.out.println((int) smallP.getArray().get(smallP.getArray().size()));
                        } catch (MidiUnavailableException e) {
                            System.out.println("unavible");
                        }
                        try {
                            Thread.sleep(1000); // wait time in milliseconds to control duration
                        } catch (InterruptedException e) {
                            System.out.println("CATTCH");
                        }
//                    } else {
//                        if (bassCount % 10 == 6) {
//                            try {
//                                Synthesizer midiSynth = MidiSystem.getSynthesizer();
//                                midiSynth.open();
//                                Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
//                                MidiChannel[] mChannels = midiSynth.getChannels();
//                                midiSynth.loadInstrument(instr[0]);//load an instrument
//                                mChannels[0].noteOn((int) smallP.getArray().get(smallP.getArray().size() / 2), 1000);//On channel 0, play note number 60 with velocity 100
//
//                            } catch (MidiUnavailableException e) {
//                                System.out.println("unavible");
//                            }
//                            try {
//                                Thread.sleep(500); // wait time in milliseconds to control duration
//                                System.out.println(bass);
//                            } catch (InterruptedException e) {
//                                System.out.println("CATTCH");
//                            }
//                            try {
//                                Synthesizer midiSynth = MidiSystem.getSynthesizer();
//                                midiSynth.open();
//                                Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
//                                MidiChannel[] mChannels = midiSynth.getChannels();
//                                midiSynth.loadInstrument(instr[0]);//load an instrument
//                                mChannels[0].noteOn((int) smallP.getArray().get(smallP.getArray().size() / 2), 1000);//On channel 0, play note number 60 with velocity 100
//
//                            } catch (MidiUnavailableException e) {
//                                System.out.println("unavible");
//                            }}
//
//                        else{
//                                if (bassCount % 10 == 8) {
//                                    try {
//                                        Synthesizer midiSynth = MidiSystem.getSynthesizer();
//                                        midiSynth.open();
//                                        Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
//                                        MidiChannel[] mChannels = midiSynth.getChannels();
//                                        midiSynth.loadInstrument(instr[0]);//load an instrument
//                                        mChannels[0].noteOn((int) smallP.getArray().get(smallP.getArray().size() / 2), 1000);//On channel 0, play note number 60 with velocity 100
//
//                                    } catch (MidiUnavailableException e) {
//                                        System.out.println("unavible");
//                                    }
//                                    try {
//                                        Thread.sleep(1000); // wait time in milliseconds to control duration
//                                        System.out.println(bass);
//                                    } catch (InterruptedException e) {
//                                        System.out.println("CATTCH");
//                                    }
//                                } else {
//                                    try {
//                                        Thread.sleep(1000); // wait time in milliseconds to control duration
//                                        System.out.println(bass);
//                                    } catch (InterruptedException e) {
//                                        System.out.println("CATTCH");
//                                    }
//                                }
                            }
                    bassCount++;
                        }

                    }

    public void inOrderSong()
    {
        String message = "";
        this.write(this.getFileName());

        for (int i=this.getArray().size()-1; i >= 0; i--)
        {
            this.getArray().remove(i);
        }

        message += this.greenConverter();
        this.clean();
        //need to work on cleaning

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
                    mChannels[0].noteOn((int) this.getArray().get(i), 1000);//On channel 0, play note number 60 with
                // velocity 100

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

    public void beautyMusic() {
        for (int i = 0; i < notes.size(); i++) {
            for (int a = ref.size()-1; a >=0; a--) {
                if (notes.get(i) > ref.get(a)) {
                    notes.set(i, ref.get(a));
                }
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
//        int b=list1.size()-2;
//        while(b>=0)
//        {
//            if(((int)list1.get(b) == (int)list1.get(b+1)) || (list1.get(b) < 30) )
//                list1.remove(b);
//            b--;
//        }

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
        //insertionSort(notes);
    }

    public static void insertionSort(ArrayList list1)
    {
        int swapPos;
        int min;

        for(int i = 1;i<list1.size();i++)
        {
            swapPos = i;
            min = (int)list1.get(swapPos);

            for(int a = i-1;a>=0;a--)
            {
                if((int)list1.get(a)>min)
                {
                    swap(list1,swapPos,a);
                    swapPos = a;
                    min = (int)list1.get(swapPos);
                }
                else
                {
                    a=-1;
                }
            }
        }
    }

    public static void swap(ArrayList arr,int pos1,int pos2)
    {
        int temp = (int)arr.get(pos1);
        arr.set(pos1,arr.get(pos2));
        arr.set(pos2, temp);
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