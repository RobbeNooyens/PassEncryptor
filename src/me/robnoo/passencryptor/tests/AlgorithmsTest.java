package me.robnoo.passencryptor.tests;

import me.robnoo.passencryptor.calculations.Algorithms;
import me.robnoo.passencryptor.calculations.Operation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AlgorithmsTest {

    Algorithms algorithms = new Algorithms();

    @Test
    public void testSplit(){
        String s = "4567890123";
        String splitted = algorithms.split(s, 6);
        assertEquals(splitted, "0123456789");
    }

    @Test
    public void testInvertChars(){
        String s = "Test";
        String inverted = algorithms.invertIndividualChars(s);
        assertEquals(inverted, "TEr1");
    }

    @Test
    public void testShift(){
        String s = "abc";
        String shifted = algorithms.shift(s, 1, Operation.ADD);
        assertEquals(shifted, "bcd");
    }

    @Test
    public void testShiftMult(){
        String s = "abc";
        String shifted = algorithms.shift(s, 2, Operation.MULTIPLY);
        assertEquals(shifted, "ace");
    }

}
