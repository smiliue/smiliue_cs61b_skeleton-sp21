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
    public void test1(){
        AListNoResizing<Integer> list1 = new AListNoResizing<>();
        list1.addLast(3);
        list1.addLast(4);
        list1.addLast(5);

        BuggyAList<Integer> list2 = new BuggyAList<>();
        list2.addLast(3);
        list2.addLast(4);
        list2.addLast(5);

    }

    @Test
    public void test2()
    {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> L1 = new BuggyAList<>();

        int N = 5000;
        for (int i=0; i<N; i++)
        {
            int operationNumber = StdRandom.uniformInt(0, 4);
            if (operationNumber == 0){
                // addLast
                int randVal = StdRandom.uniformInt(0, 100);
                L.addLast(randVal);
                L1.addLast(randVal);
                // System.out.println("addLast(" + randVal + ")");
                // System.out.println("! addLast(" + randVal + ")");
            }
            else if (operationNumber == 1){
                // size
                int size = L.size();
                int size1 = L1.size();

                assert size == size1;

                // System.out.println("size: " + size);
                // System.out.println("! size: " + size1);
            }
            else if (operationNumber == 2 && L.size() > 0){
                // getLast
                int num = L.getLast();
                int num1 = L1.getLast();
                assert num1 == num;

                // System.out.println("getLast: " + num);
                // System.out.println("! getLast: " + num1);
            }
            else if (operationNumber == 3 && L.size() > 0){
                // removeLast
                int num = L.removeLast();
                int num1 = L1.removeLast();
                assert num1 == num;
                // System.out.println("removeLast: " + num);
                // System.out.println("! removeLast: " + num1);
            }
        }
    }
}
