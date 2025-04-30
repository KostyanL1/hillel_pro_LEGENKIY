package org.lesson29;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DatabaseConnector.connect();
        EmployeeService employeeService = new EmployeeService(DatabaseConnector.connection);
        employeeService.add(new EmployeeDAO("Kostya", 20, "developer", 0));
        employeeService.read().forEach(System.out::println);
        employeeService.delete(2);
        employeeService.read().forEach(System.out::println);
        employeeService.update(new EmployeeDAO(5, "Kostya111", 22, "developer", 0));
        System.out.println(employeeService.read().stream().filter(i -> i.getId() == 5).findFirst());
    }
}
