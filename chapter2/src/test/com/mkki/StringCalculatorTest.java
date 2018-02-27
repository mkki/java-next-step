package com.mkki;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class StringCalculatorTest {
    private StringCalculator stringCalculator;

    @Before
    public void setup() {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void addNull() {
        assertEquals(0, stringCalculator.add(null));
        assertEquals(0, stringCalculator.add(""));
    }

    @Test
    public void addSingleNum() {
        assertEquals(1, stringCalculator.add("1"));
    }

    @Test
    public void addNums() {
        assertEquals(3, stringCalculator.add("1,2"));
        assertEquals(3, stringCalculator.add("1:2"));
        assertEquals(3, stringCalculator.add("1:2"));
    }

    @Test
    public void addNumsWithCustomSepr(){
        assertEquals(6, stringCalculator.add("//;\n1;2;3"));
    }

    // @Test(expected = RuntimeException)... 으로 대체 가능
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void addNegativeNum(){
        thrown.expect(RuntimeException.class);
        stringCalculator.add("-1,1,1");
    }
}