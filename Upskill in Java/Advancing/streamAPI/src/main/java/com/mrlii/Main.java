package com.mrlii;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Main {

    record Car(
            String type,
            String make,
            String model,
            Integer engineCapacity){
    }

    public static void main(String[] args) {

        List<Car> cars = List.of(
                new Car("Sedan", "Toyota", "Camry", 1998),
                new Car("Hatchback", "Skoda", "Octavia", 1600),
                new Car("hatCockback", "Toyota", "Octavia", 2500),
                new Car("Sedan", "Mercedes", "E-Class", 1450),
                new Car("Sedan", "Audi", "A5", 1998)
        );

        List<Car> sedanCars = cars.stream()
                .filter(car -> car.type.equals("Sedan")).toList();

        List<String> carMake = cars.stream()
                .map(car -> car.make).toList();

        List<String> carMakeModelList = cars.stream()
                .flatMap(car -> Stream.of(car.make, car.model)).toList();


        Stream<Integer> integerStream = Stream.of(10,11,12,13,14);
        Stream<Integer> filteredIntegerStream = integerStream.filter(i -> {
            System.out.println("Filtering integers");
            return i % 2 == 0;
        });
        System.out.println(filteredIntegerStream.count());


        Map<Boolean, List<Car>> partitionedCars = cars.stream()
                .collect(partitioningBy(car -> car.type.equals("Sedan")));

        Map<String, Map<String, Integer>> groupedCars = cars.stream()
                .collect(
                        groupingBy(Car::type, toMap(Car::make,Car::engineCapacity))
                );
    }
}