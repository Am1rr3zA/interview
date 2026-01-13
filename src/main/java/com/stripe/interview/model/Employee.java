package com.stripe.interview.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Employee data class using Lombok.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//@ToString
public class Employee {
    private String name;
    private int age;
    private String department;
    private double salary;

//    @Override
//    public String toString() {
//        return "Hello " + "Employee(name=" + name + ", age=" + age + ", department=" + department + ", salary=" + salary + ")";
//    }
}