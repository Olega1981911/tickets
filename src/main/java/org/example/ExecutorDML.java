package org.example;

import org.example.config.ConnectionManager;
import org.example.data.Flight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExecutorDML {
    // Метод для выполнения SQL-запроса и возвращения списка рейсов
    public static List<Flight> getFlightsById(long id) {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM flight WHERE id = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id); // Устанавливаем параметр запроса
            ResultSet resultSet = statement.executeQuery();

            // Обрабатываем результат запроса
            while (resultSet.next()) {
                Flight flight = new Flight(
                        resultSet.getLong("id"),
                        resultSet.getString("flight_no"),
                        resultSet.getTimestamp("departure_date").toLocalDateTime(),
                        resultSet.getString("departure_airport_code"),
                        resultSet.getTimestamp("arrival_date").toLocalDateTime(),
                        resultSet.getString("arrival_date_code"),
                        resultSet.getInt("aircraft_id"),
                        resultSet.getString("status")
                );
                flights.add(flight); // Добавляем рейс в список
            }

        } catch (SQLException e) {
            throw new RuntimeException("Не удалось выполнить SQL-запрос", e);
        }

        return flights; // Возвращаем список рейсов
    }
}
