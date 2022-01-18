package aplikacja.entities;

import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JMockit.class)
public class FilmTestJmockit {

        @Mocked
        aplikacja.entities.Film film;

        @Test
        public void testEquals() {
            aplikacja.entities.Film filmy[] = {new aplikacja.entities.Film("Zielona", "Dramat","Jerzy","Kukulka",2003,"DMC",1000),
                    new aplikacja.entities.Film("Zielona", "Dramat","Jerzy","Kukulka",2003,"DMC",1000)
            };
            // dowolny konstruktor
            System.out.println("equals");
            for (int i = 0; i < 1; i++)
                for (int j = i; j < 2; j++)
                    if (i == j)
                        assertTrue(filmy[i].equals(filmy[i]));
                else
                    assertFalse(filmy[i].equals(filmy[j]));
        }
}
