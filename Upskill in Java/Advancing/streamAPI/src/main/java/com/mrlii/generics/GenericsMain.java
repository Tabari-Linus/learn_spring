package com.mrlii.generics;

public class GenericsMain {

    public static void main(String[] args){

        Printer<String> printer = new Printer<>("Word Book");
        printer.print();

        Scanner scanner = new Scanner();
        MultiPrinter<Printer<String>> multiPrinter = new MultiPrinter<>(printer);
        multiPrinter.printAndScan("file.txt");


    }
}
