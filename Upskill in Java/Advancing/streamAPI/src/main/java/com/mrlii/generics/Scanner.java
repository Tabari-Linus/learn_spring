package com.mrlii.generics;

public class Scanner implements Scannable{

    @Override
    public void scan(String file) {
        System.out.println("Scanning file: " + file);
    }

}
