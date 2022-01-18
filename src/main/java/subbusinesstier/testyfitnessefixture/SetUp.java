/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subbusinesstier.testyfitnessefixture;

import entities.Film;
import entities.Fasada;
import fit.Fixture;


/**
 *
 * @author marci
 */
public class SetUp extends Fixture {
    static Film film;
    public SetUp(){
    

        film = new Film("Zielona", "Dramat","Jerzy","Kukulka",2003,"DMC",1000);
    }
    
}
