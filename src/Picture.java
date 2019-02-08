import javax.sound.midi.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLOutput;
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
                notes.add((((pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue()) / 15) +30));
            }
        }
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
             //   }

    public static void inOrderSong(String picName)
    {
      Picture pix = new Picture(picName);
      Picture smallP = pix.scale(1,1);
     smallP.write(picName);
     smallP.redConverter();
        clean(smallP.getArray());
        for (int i=0; i < smallP.getArray().size(); i++)
     {
         try {      System.out.println(smallP.getArray().get(i));

             Synthesizer midiSynth = MidiSystem.getSynthesizer();
                                        midiSynth.open();
                                        Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
                                        MidiChannel[] mChannels = midiSynth.getChannels();
                                        midiSynth.loadInstrument(instr[0]);//load an instrument
                                        mChannels[0].noteOn((int) smallP.getArray().get(i), 1000);//On channel 0, play note number 60 with velocity 100

                                    } catch (MidiUnavailableException e) {
                                        System.out.println("unavible");
                                    }
                                    try {
                                        Thread.sleep(200); // wait time in milliseconds to control duration
                                    } catch (InterruptedException e) {
                                        System.out.println("CATTCH");
                                    }
    }
}

    public static void smoothMusic(String picName)
    {
        int prevNote = -1;

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
                if(prevNote == -1)
                {
                    prevNote = (int)smallP.getArray().get(i);
                }
                else
                {
                    if(Math.abs(prevNote - (int)smallP.getArray().get(i)) > 127)
                    {
                        smallP.getArray().set(i,(Integer)(255-(int)smallP.getArray().get(i)));
                        System.out.println("127: " + (int)smallP.getArray().get(i));
                    }
                    if(Math.abs(prevNote - (int)smallP.getArray().get(i)) > 63)
                    {
                        smallP.getArray().set(i,(Integer)(127-(int)smallP.getArray().get(i)));
                        System.out.println("63: " + (int)smallP.getArray().get(i));
                    }
                    if(Math.abs(prevNote - (int)smallP.getArray().get(i)) > 32)
                    {
                        smallP.getArray().set(i,(Integer)(63-(int)smallP.getArray().get(i)));
                        System.out.println("32: " + (int)smallP.getArray().get(i));
                    }
                    prevNote = (int)smallP.getArray().get(i);
                }

                mChannels[0].noteOn((int)smallP.getArray().get(i), 300);//On channel 0, play note number 60 with velocity 100
                try {
                    Thread.sleep(12); // wait time in milliseconds to control duration
                    System.out.println((int)smallP.getArray().get(i));
                } catch (InterruptedException e) {
                    System.out.println("CATCH");
                }
            } catch (MidiUnavailableException e) {
                System.out.println("unavailable");
            }
        }
    }
    /**
     * First Option: uses the red RGB value in each pixel
     *               to add a note to the notes arrayList
     */
    public void redConverter() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                notes.add(((pixelObj.getRed())/4)+25);
            }
        }
    }

    /**
     * Second Option: uses the green RGB value in each pixel
     *                to add a note to the notes arrayList
     */
    public void greenConverter() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                notes.add((pixelObj.getGreen()/4)+25);
            }
        }
    }

    /**
     * Third Option: uses the blue RGB value in each pixel
     *               to add a note to the notes arrayList
     */
    public void blueConverter() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                notes.add((pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue()/4)+25);
            }
        }
    }
    public static void clean(ArrayList list1)
    {
        insertionSort(list1);
        int i=1;
       while(i<list1.size()-1)
        {
            if((list1.get(i) == list1.get(i-1)) || ((int)list1.get(i) < 30) )
                list1.remove(i);
            i++;
        }

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
    public static void quickSort(ArrayList arr, int left, int right)
    {
        if(left<right)
        {
            int pivot = partition(arr,left,right);

            quickSort(arr,left,pivot-1);
            quickSort(arr,pivot+1,right);
        }
    }

    public static int partition(ArrayList arr, int left, int right)
    {
        int pivot = (int)arr.get(right);
        int i = left-1;

        for(int j = left;j<right;j++)
        {
            if((int)arr.get(i)<=pivot)
            {
                i++;
                swap(arr,i,j);
            }
        }
        swap(arr,i+1,right);
        return(i+1);
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

    public ArrayList getArray()
    {
        return notes;
    }

}