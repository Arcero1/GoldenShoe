package com.example.goldenshoeprototype;

public class Util {
    static String getDisplayPrice(int priceInPennies) {
        String p = String.valueOf(priceInPennies);
        int len = p.length() - 2;

        switch (len){
            case -1:
            case -2:
                return "£0.0" + p;
            case 0:
                return "£0." + p;
                default:
                    return "£" + p.substring(0, len) + "." + p.substring(len);
        }
    }
}
