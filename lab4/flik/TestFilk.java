package flik;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestFilk {
    @Test
    public void testTheirCode(){
        int i = 0;
        int who=0;
        for (int j = 0; i < 500; ++i, ++j) {
            if (!Flik.isSameNumber(i, j)) {
                who=j;
                break;
            }
        }
        System.out.println("i is " + i);
        System.out.println("j is " + who);
    }
}
