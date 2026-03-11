package com.mrlii.lamdaExpressions;

public class Lambdas {

    public static void main(String[] args) {
        Cat myCat = new Cat("Whiskers", 3);

        // Using Lambda expression to implement Printable
        Printable lambdaPrintable = () -> System.out.println("Meow from Lambda!");
        printThing(lambdaPrintable);

        // Using Method Reference
        Printable methodRefPrintable = myCat::print;
        printThing(methodRefPrintable);

        // Using Lambda expression to implement Scannable
        Scannable lambdaScannable = (s) -> System.out.println("Scanning names" + s);
        scanThing(lambdaScannable);


    }

    static void printThing(Printable thing) {
        thing.print();
    }

    static void scanThing(Scannable thing) {
        thing.scan(".exe");
    }

}
