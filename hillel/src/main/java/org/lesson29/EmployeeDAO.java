package org.lesson29;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EmployeeDAO {

    private int id;
    private String name;
    private int age;
    private String  position;
    private int salary;

    public EmployeeDAO(String name, int age, String position, int salary) {
        this.name = name;
        this.age = age;
        this.position = position;
        this.salary = salary;
    }

    public EmployeeDAO(int id, String name, int age, String position, int salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.position = position;
        this.salary = salary;
    }
}
