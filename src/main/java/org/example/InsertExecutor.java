package org.example;

import org.example.config.ConnectionManager;
import org.example.data.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertExecutor {
    // Метод для вставки данных в таблицу airport
    public static void insertAirport(Airport airport) {
        String sql = """
                INSERT INTO airport VALUES (?,?,?)""";
        try (var connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, airport.getCode());
            statement.setString(2, airport.getCountry());
            statement.setString(3, airport.getCity());

            statement.executeUpdate();
            System.out.println("Airport inserted successfully: " + airport);

        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert airport", e);
        }
    }

    // Метод для вставки данных в таблицу aircraft
    public static void insertAircraft(Aircraft aircraft) {
        String sql = """
                INSERT INTO aircraft (model) VALUES (?)""";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, aircraft.getModel());

            statement.executeUpdate();
            System.out.println("Aircraft inserted successfully: " + aircraft);

        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert aircraft", e);
        }
    }

    // Метод для вставки данных в таблицу seat
    public static void insertSeat(Seat seat) {
        String sql = """
                INSERT INTO seat (aircraft_id, seat_no) VALUES (?, ?)""";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, seat.getAircraftId());
            statement.setString(2, seat.getSeatNo());

            statement.executeUpdate();
            System.out.println("Seat inserted successfully: " + seat);

        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert seat", e);
        }
    }

    // Метод для вставки данных в таблицу flight
    public static void insertFlight(Flight flight) {
        String sql = """
        INSERT INTO flight (flight_no, departure_date, departure_airport_code, arrival_date, arrival_date_code, aircraft_id, status)
        VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, flight.getFlightNo());
            statement.setTimestamp(2, java.sql.Timestamp.valueOf(flight.getDepartureDate()));
            statement.setString(3, flight.getDepartureAirportCode());
            statement.setTimestamp(4, java.sql.Timestamp.valueOf(flight.getArrivalDate()));
            statement.setString(5, flight.getArrivalDateCode());
            statement.setInt(6, flight.getAircraftId());
            statement.setString(7, flight.getStatus());

            statement.executeUpdate();
            System.out.println("Flight inserted successfully: " + flight);

        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage()); // Логируем ошибку
            throw new RuntimeException("Failed to insert flight", e);
        }
    }

    // Метод для вставки данных в таблицу ticket
    public static void insertTicket(Ticket ticket) {
        String sql = """
                INSERT INTO ticket (passenger_no, passenger_name, flight_id, seat_no, cost)
                VALUES (?, ?, ?, ?, ?)""";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, ticket.getPassengerNo());
            statement.setString(2, ticket.getPassengerName());
            statement.setLong(3, ticket.getFlightId());
            statement.setString(4, ticket.getSeatNo());
            statement.setDouble(5, ticket.getCost());

            statement.executeUpdate();
            System.out.println("Ticket inserted successfully: " + ticket);

        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
            throw new RuntimeException("Failed to insert ticket", e);
        }
    }

}
