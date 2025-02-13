package org.xomishin.courseSpringApp.dao;

import org.springframework.stereotype.Component;
import org.xomishin.courseSpringApp.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PERSON_ID;

    private static final String URL = "jdbc:postgresql://localhost:5433/spring";
    private static final String USER = "admin";
    private static final String PASS = "example";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public List<Person> index() {
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM person";

            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));
                people.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

    public Person show(int id) {

        Person person = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE id =?");

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Проверяем, есть ли данные
                person = new Person(); // Инициализируем объект
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;
    }

    public void save(Person person) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO person VALUES (1,?,?,?)");
            preparedStatement.setString(1,person.getName());
            preparedStatement.setInt(2,person.getAge());
            preparedStatement.setString(3,person.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Person person) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE person SET name=?, age=?, email=? WHERE id=?");

            preparedStatement.setString(1,person.getName());
            preparedStatement.setInt(2,person.getAge());
            preparedStatement.setString(3,person.getEmail());
            preparedStatement.setInt(4,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM person WHERE id=?");

            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
