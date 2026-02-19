package com.mrlii.producer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    private String id;
    private String title;
    private String description;
    private String instructor;
    private double price;

}
