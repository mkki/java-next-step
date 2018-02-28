package com.mkki;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public int add(String text) {
        if (isBlank(text)) {
            return 0;
        }

        return sum(toInts(split(text)));
    }

    public String[] split(String text){
        Matcher m = Pattern.compile("//(.)\n(.*)").matcher(text);
        if(m.find()){
            return m.group(2).split(m.group(1));
        }

        return text.split(",|:");
    }

    public boolean isBlank(String text){
        return text == null || text.isEmpty();
    }

    public int[] toInts(String[] values){
        int[] numbers = new int[values.length];
        for(int i=0; i<values.length; i++){
            numbers[i] = toPositive(values[i]);
        }

        return numbers;
    }

    public int toPositive(String value){
        int number = Integer.parseInt(value);
        if(number < 0){
            throw new RuntimeException();
        }

        return number;
    }

    public int sum(int[] numbers){
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }

        return sum;
    }
}
