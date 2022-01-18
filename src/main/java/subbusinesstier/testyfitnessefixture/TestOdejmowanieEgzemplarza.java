/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subbusinesstier.testyfitnessefixture;

import aplikacja.entities.Film;
import subbusinesstier.entities;
import fit.ColumnFixture;
import java.util.IllegalFormatCodePointException;

/**
 *
 * @author marci
 */
public class TestOdejmowanieEgzemplarza extends ColumnFixture {
    long dane;

    Film film;
    public boolean decreaseIloscEgzemplarzy() throws IllegalFormatCodePointException {
        long s1 = liczbaEgzemplarzy() ;
        try{
            SetUp.film.decreaseIloscEgzemplarzy(dane);
            long s2 = liczbaEgzemplarzy();
            return s1!=s2;
        }
        catch(IllegalFormatCodePointException e) {
            return false;
        }
       }
    
    public long liczbaEgzemplarzy() {
        return SetUp.film.getIloscEgzemplarzy();
 } 
    
}
