package aplikacja.entities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static aplikacja.entities.Film.decreaseIloscEgzemplarzy;
import static org.junit.Assert.*;

public class FilmTest {
    Dane dane;
    @Before
    public void setUp(){
        dane= new Dane();
    }

    @Parameterized.Parameter
    public int numer1;


//    @Parameterized.Parameters
//    public static Collection<Object[]> data() {
//        Object[][] data1 = new Object[][]{ {0}, {1}, {2}, {3}, {4}, {5}, {6}, {7} };
//        return Arrays.asList(data1);
//    }

    @Test
    public void testEquals() {
        System.out.println("equals");
        for(int j=numer1;j<1;j++)
            if(numer1==j)
                assertTrue(dane.filmy[numer1].equals(dane.filmy[j]));
            else
                assertFalse(dane.filmy[numer1].equals(dane.filmy[j]));
    }

    @Test
    public void decreaseTestIloscEgzemplarzy() {
        for(int i =0;i< dane.ilosciEgzemplarzy.length;i++) {
            assertEquals(dane.result[i], decreaseIloscEgzemplarzy(dane.ilosciEgzemplarzy[i]));
        }
    }
}