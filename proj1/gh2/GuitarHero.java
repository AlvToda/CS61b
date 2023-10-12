package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHero{
    // TODO: create an array of 37 GuitarString objects and an array
    //  (String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";)
    public static int cap=37;
    public static double[] CONCERT = new double[cap];
    public static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    //public static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        // TODO: initialize the Guitar string arrays,which type is GuitarString.
        int cap=37;
        /*GuitarString stringA = new GuitarString(CONCERT_A);
        GuitarString stringC = new GuitarString(CONCERT_C);*/
        GuitarString[] strings=new GuitarString[cap];
        for(int i=0; i < cap ; i++){
            //440⋅2(i−24)/12
            CONCERT[i]=440*Math.pow(2.0, (double) (i - 24) /12);
            strings[i]=new GuitarString(CONCERT[i]);
            //System.out.println(CONCERT[i]);
        }



        while (true) {
            // TODO:use the function( String a.indexOf(); ) to get the index of keyboard
            //  to match the guitar string
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                /*if (key == 'a') {
                    stringA.pluck();
                } else if (key == 'c') {
                    stringC.pluck();
                }*/
                int i =keyboard.indexOf(key);
                if (i>-1&&i<cap){
                    strings[i].pluck();
                }

            }
            // TODO:Here is also a change
            /* compute the superposition of samples */
            double sample = 0.0;
            for(int i=0;i<cap;i++){
                //stringA.sample() + stringC.sample()
                sample+=strings[i].sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            // TODO:Here is also a change
            /* advance the simulation of each guitar string by one step */
            /*stringA.tic();
            stringC.tic();*/
            for(int i=0;i<cap;i++){
               strings[i].tic();
            }

        }
    }
}