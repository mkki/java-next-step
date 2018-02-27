package com.mkki;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public int add(String text){
        if(text == null || text.isEmpty()) {
            return 0;
        }

        if(text.contains(",") || text.contains(":")) {
            String[] tokens = text.split(",|:");

            int sum = 0;
            for (String str : tokens) {
                if(Integer.parseInt(str) < 0){
                    throw new RuntimeException();
                }
                sum += Integer.parseInt(str);
            }

            return sum;
        }

        Matcher m = Pattern.compile("//(.)\n(.*)").matcher(text);

        if(m.find()){
            String customDelimeter = m.group(1);
            String[] tokens = m.group(2).split(customDelimeter);

            int sum = 0;
            for (String str : tokens) {
                if(Integer.parseInt(str) < 0){
                    throw new RuntimeException();
                }
                sum += Integer.parseInt(str);
            }

            return sum;
        }

        return Integer.parseInt(text);
    }
}
