package com.example.assignment01.utils;

public class Utils {
    public static String formatSearch(String search) {
        search = search.trim();
        while (search.contains("  ")) {
            search = search.replace("  ", " ");
        }

        return search;
    }
}
