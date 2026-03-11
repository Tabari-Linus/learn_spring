package com.mrlii.springboot_up_and_running;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringBootUpAndRunningApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootUpAndRunningApplication.class, args);
    }

}


class Coffee{
    private  final String id;
    private String name;

    public Coffee(String id, String name){
        this.id = id;
        this.name =name;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}

@RestController
class RestDemoController{
    private List<Coffee> coffees = new ArrayList<>();

    public RestDemoController(){
        coffees.addAll(List.of(
                new Coffee("1", "Latte"),
                new Coffee("2", "Americano"),
                new Coffee("3", "Mocha")
        ));

    }

    @RequestMapping(value = "/coffees", method = RequestMethod.GET)
    Iterable<Coffee> getCoffees() {
        return coffees;
    }
}