package com.mrlii.generics;

public class MultiPrinter <T extends Printer & Scannable>{

    T thingToPrint;

    public MultiPrinter(T thingToPrint) {
        this.thingToPrint = thingToPrint;
    }

    public void printAndScan(String file) {
        thingToPrint.print();
        thingToPrint.scan(file);
    }

}
