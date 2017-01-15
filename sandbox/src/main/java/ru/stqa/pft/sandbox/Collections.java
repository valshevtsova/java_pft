package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main (String[] args) {
        String[] langs = {"Java", "C#", "Pyton", "PHP"};

        List<String> languages = Arrays.asList("Java", "C#", "Pyton", "PHP");

        for (String l : languages) {
            System.out.println("Я хочу выучить " + l);
        }
    }
}
