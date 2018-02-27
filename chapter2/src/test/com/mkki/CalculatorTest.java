package com.mkki;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {
    private Calculator cal;

    @Before
    public void setup(){
        cal = new Calculator();
    }

    @Test
    public void add(){
        assertEquals(9, cal.add(4, 5));
    }

    @Test
    public void substract(){
        assertEquals(4, cal.subtract(5,1));
    }
}