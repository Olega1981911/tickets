package org.example.util;

import org.example.InsertExecutor;
import org.example.data.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileDataLoader {

    public static void loadDataFromFile(String filePath) {
        try (InputStream inputStream = FileDataLoader.class.getClassLoader().getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            if (inputStream == null) {
                throw new RuntimeException("File not found: " + filePath);
            }

            String line;
            while ((line = reader.readLine()) != null) {
                processLine(line);
            }
            System.out.println("Data loaded and inserted successfully from file: " + filePath);

        } catch (IOException e) {
            throw new RuntimeException("Failed to read file: " + filePath, e);
        }
    }

    private static void processLine(String line) {
        try {
            String[] tokens = line.split(",");
            String entityType = tokens[0];

            switch (entityType) {
                case "airport" -> {
                    Airport airport = new Airport();
                    airport.setCode(tokens[1]);
                    airport.setCountry(tokens[2]);
                    airport.setCity(tokens[3]);
                    InsertExecutor.insertAirport(airport);
                }
                case "aircraft" -> {
                    Aircraft aircraft = new Aircraft();
                    aircraft.setModel(tokens[1]);
                    InsertExecutor.insertAircraft(aircraft);
                }
                case "seat" -> {
                    Seat seat = new Seat();
                    seat.setSeatNo(tokens[1]); // Убедитесь, что в файле достаточно данных для seat
                    InsertExecutor.insertSeat(seat);
                }
                case "flight" -> {
                    Flight flight = new Flight();
                    flight.setFlightNo(tokens[1]);
                    flight.setDepartureDate(LocalDateTime.parse(tokens[2], DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                    flight.setDepartureAirportCode(tokens[3]);
                    flight.setArrivalDate(LocalDateTime.parse(tokens[4], DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                    flight.setArrivalDateCode(tokens[5]);
                    flight.setAircraftId(Integer.parseInt(tokens[6]));
                    flight.setStatus(tokens[7]);
                    InsertExecutor.insertFlight(flight);
                }
                case "ticket" -> {
                    Ticket ticket = new Ticket();
                    ticket.setPassengerNo(tokens[1]);
                    ticket.setPassengerName(tokens[2]);
                    ticket.setFlightId(Long.parseLong(tokens[3]));
                    ticket.setSeatNo(tokens[4]);
                    ticket.setCost(Double.parseDouble(tokens[5]));
                    InsertExecutor.insertTicket(ticket);
                }
                default -> throw new IllegalArgumentException("Unknown entity type: " + entityType);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to process line: " + line, e);
        }
    }
}