package org.example;

import java.sql.*;

public class Main {

    private static final String URL = "jdbc:postgresql://ep-snowy-king-a5ljd5qt.us-east-2.aws.neon.tech:5432/yekex64803db";
    private static final String USER = "yekex64803db_owner";
    private static final String PASSWORD = "ZPf2zIeMFG6K";

    private static Connection connection;

    public static void main(String[] args)  {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            //createTable();
            Abonent ivan = new Abonent();
            ivan.setName("Підкаблучник Іван Васильович");
            ivan.setPhoneNumber("098 78 67 567");
            ivan.setAddress("м. Рівне вул. Соборна 24, кв.45");
            ivan.setEmail("ivan@gmail.com");
//
//          addContact(ivan);

            ivan.setPhoneNumber("0000");
            updateContact(1, ivan);

            //deleteContact(4);

        } catch (SQLException e) {
            System.out.println("Щось пішло не так! " + e.getMessage());
        }
    }

    private static void createTable() throws SQLException {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // SQL-запит для створення таблиці
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Phonebook (" +
                    "ID SERIAL PRIMARY KEY, " +
                    "Name VARCHAR(100) NOT NULL, " +
                    "PhoneNumber VARCHAR(15) NOT NULL, " +
                    "Email VARCHAR(100), " +
                    "Address VARCHAR(255)" +
                    ");";
            Statement command = connection.createStatement();

            // Виконання SQL-запиту на створення таблиці
            command.execute(createTableSQL);
            System.out.println("Успішно створено таблицю Phonebook :)");



        } catch (Exception e) {
            System.out.println("Помилка з'єднання до БД!!!");
        }
        finally {
            if (connection!=null)
                connection.close();
        }
    }

    public static void addContact(Abonent abonent) throws SQLException {
        String insertSQL = "INSERT INTO Phonebook (Name, PhoneNumber, Email, Address) VALUES (?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, abonent.getName());
            preparedStatement.setString(2, abonent.getPhoneNumber());
            preparedStatement.setString(3, abonent.getEmail());
            preparedStatement.setString(4, abonent.getAddress());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Новий контакт доданий успішно.");
            }
        }
    }

    public static void updateContact(int id, Abonent abonent) throws SQLException {
        String updateSQL = "UPDATE Phonebook SET Name = ?, PhoneNumber = ?, Email = ?, Address = ? WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, abonent.getName());
            preparedStatement.setString(2, abonent.getPhoneNumber());
            preparedStatement.setString(3, abonent.getEmail());
            preparedStatement.setString(4, abonent.getAddress());
            preparedStatement.setInt(5, id);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Контакт оновлено успішно.");
            } else {
                System.out.println("Контакт з таким ID не знайдений.");
            }
        }
    }

    public static void deleteContact(int id) throws SQLException {
        String deleteSQL = "DELETE FROM Phonebook WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Контакт видалений успішно.");
            } else {
                System.out.println("Контакт з таким ID не знайдений.");
            }
        }
    }




}