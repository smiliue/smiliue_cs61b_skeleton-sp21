package deque;
import org.junit.Test;

public class ComparsionTest {
    @Test
    public void test1()
    {
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        deque.ArrayDeque<Integer> ad = new deque.ArrayDeque<>();

        // Java office interface
        java.util.ArrayDeque<Integer> ad_auth = new java.util.ArrayDeque<>();

        for (int i=0; i<100000; i++)
        {
            lld.addLast(i);
            ad.addLast(i);
            ad_auth.addLast(i);
        }
        for (int i=1; i<=100000; i++)
        {
            int n1 = lld.removeLast();
            int n2 = ad.removeLast();
            int n3 = ad_auth.removeLast();
            assert n1 == n2 && n2 == n3;
        }
    }
}
