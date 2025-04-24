package org.lesson29;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class EmployeeService {

    private Connection connection;

    public int add(EmployeeDAO employeeDAO) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employees (name, age, position, salary) values (?,?,?,?)")) {
            preparedStatement.setString(1,employeeDAO.getName() );
            preparedStatement.setInt(2,employeeDAO.getAge() );
            preparedStatement.setString(3,employeeDAO.getPosition() );
            preparedStatement.setInt(4, employeeDAO.getSalary());
            return preparedStatement.executeUpdate();
        }
    }

    public List<EmployeeDAO> read() throws SQLException {
        try (Statement statement = connection.createStatement()){
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM employees")){
                List<EmployeeDAO> employeeDAOS = new ArrayList<>();
                while (resultSet.next()){


                    employeeDAOS.add(new EmployeeDAO(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getString("position"),
                            resultSet.getInt("salary")
                    ));
                }
                return employeeDAOS;
            }
        }
    }

    public int update(EmployeeDAO employeeDAO) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employees SET name=?, age=?, position=?, salary=? WHERE id=?")){
            preparedStatement.setString(1, employeeDAO.getName());
            preparedStatement.setInt(2, employeeDAO.getAge());
            preparedStatement.setString(3, employeeDAO.getPosition());
            preparedStatement.setInt(4, employeeDAO.getSalary());
            preparedStatement.setInt(5, employeeDAO.getId());
            return preparedStatement.executeUpdate();
        }
    }

    public int delete(int id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM employees WHERE id=?")){
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        }
    }

}
