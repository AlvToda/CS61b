package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        BuggyAList<Integer> bug=new BuggyAList<Integer>();
        AListNoResizing<Integer> correct=new AListNoResizing<Integer>();
        for(int i =0;i<3;i++){
            bug.addLast(i+4);
            correct.addLast(i+4);
        }
        for(int i =0;i<3;i++){
            assertEquals(correct.removeLast(), bug.removeLast());
        }

    }
    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B=new BuggyAList<Integer>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int sizeL = L.size();
                int sizeB = B.size();
                System.out.println("sizeL: " + sizeL);
                System.out.println("sizeB: " + sizeB);
                assertEquals(sizeL, sizeB);
            }else if (operationNumber==2){
                assertEquals(L.size(),B.size());
                if (L.size()>0&&B.size()>0) {
                    int m = L.getLast();
                    L.removeLast();
                    int b = B.getLast();
                    B.removeLast();
                    System.out.println("removeLast(" + m + ")");
                    //assertEquals(L.removeLast(), B.removeLast());
                    assertEquals(m,b);
                }
                /*
                if(B.size()>0){
                    int b = B.getLast();
                    B.removeLast();
                    System.out.println("removeLast(" + m + ")");
                    //assertEquals(L.removeLast(), B.removeLast());
                    assertEquals(m,b);
                }
                */
            }
        }
    }

}
