package org.example;

import org.example.config.ConnectionManager;
import org.example.data.Flight;
import org.example.data.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExecutorDML {

    // Общий метод для выполнения SQL-запроса и возвращения ResultSet
    private static ResultSet executeQuery(String query, Object... params) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]); // Устанавливаем параметры
        }
        return preparedStatement.executeQuery();
    }

    // Метод для выполнения SQL-запроса и возвращения списка рейсов
    public static List<Flight> getFlightsById(long id) {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM flight WHERE id = ?"; // запрос SQL с параметром
        try (ResultSet resultSet = executeQuery(sql, id)) {
            while (resultSet.next()) {
                flights.add(mapToFlight(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось выполнить SQL-запрос", e);
        }
        return flights; // Возвращаем список рейсов
    }

    public static List<Flight> getFullFlight() {
        String sql = "SELECT * FROM flight";
        List<Flight> flights = new ArrayList<>();
        try (ResultSet resultSet = executeQuery(sql)) {
            while (resultSet.next()) {
                flights.add(mapToFlight(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось выполнить SQL-запрос", e);
        }
        return flights;
    }

    private static Flight mapToFlight(ResultSet resultSet) throws SQLException {
        Flight flight = new Flight();
        flight.setId(resultSet.getLong("id"));
        flight.setFlightNo(resultSet.getString("flight_no"));
        flight.setDepartureDate(resultSet.getTimestamp("departure_date").toLocalDateTime());
        flight.setDepartureAirportCode(resultSet.getString("departure_airport_code"));
        flight.setArrivalDate(resultSet.getTimestamp("arrival_date").toLocalDateTime());
        flight.setArrivalDateCode(resultSet.getString("arrival_date_code"));
        flight.setAircraftId(resultSet.getInt("aircraft_id"));
        flight.setStatus(resultSet.getString("status"));
        return flight;

    }

    public static List<Flight> getFullFlightByStatus(String status) {
        String sql = "SELECT * FROM flight WHERE status = ?";
        List<Flight> flights = new ArrayList<>();
        try (ResultSet resultSet = executeQuery(sql, status)) {
            while (resultSet.next()) {
                flights.add(mapToFlight(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось выполнить SQL-запрос", e);
        }
        return flights;
    }

    public static List<Ticket> getTicketsById(long id) {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM ticket WHERE id = ?";
        try (ResultSet resultSet = executeQuery(sql, id)) {
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getLong("id"));
                ticket.setPassengerNo(resultSet.getString("passenger_no"));
                ticket.setPassengerName(resultSet.getString("passenger_name"));
                ticket.setFlightId(resultSet.getLong("flight_id"));
                ticket.setSeatNo(resultSet.getString("seat_no"));
                ticket.setCost(resultSet.getDouble("cost"));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось выполнить SQL-запрос", e);
        }
        return tickets;
    }
}
