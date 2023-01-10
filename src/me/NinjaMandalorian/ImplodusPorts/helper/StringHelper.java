package me.NinjaMandalorian.ImplodusPorts.helper;

public class StringHelper {
    
    public static String[] remFirst(String[] strings) {
        if (strings.length < 2) return new String[0];
        String[] returnArr = new String[strings.length-1];
        for (int i = 1; i < strings.length; i++) {
            returnArr[i-1] = strings[i];
        }
        return returnArr;
    }
    
}
