package org.example.util;

import org.example.InsertExecutor;
import org.example.data.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileDataLoader {
    public static void loadDataFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                processLine(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file: " + filePath, e);
        }
    }

    private static void processLine(String line) {
        String[] tokens = line.split(","); // разделяем строку по разделителю
        String entityType = tokens[0]; // Первый элемент указывает на тип сущности
        switch (entityType) {
            case "Airport" -> {
                Airport airport = new Airport();
                airport.setCode(tokens[1]);
                airport.setCountry(tokens[2]);
                airport.setCity(tokens[3]);
                InsertExecutor.insertAirport(airport); // Вставляем данные в таблицу
            }
            case "Aircraft" -> {
                Aircraft aircraft = new Aircraft();
                aircraft.setModel(tokens[1]);
                InsertExecutor.insertAircraft(aircraft);

            }
            case "Seat" -> {
                Seat seat = new Seat();
                seat.setAircraftId(Integer.parseInt(tokens[1]));
                seat.setSeatNo(tokens[2]);
                InsertExecutor.insertSeat(seat);
            }
            case "Flight" -> {
                Flight flight = new Flight();
                flight.setFlightNo(tokens[1]);
                flight.setDepartureDate(LocalDateTime.parse(tokens[2], DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                flight.setDepartureAirportCode(tokens[3]);
                flight.setArrivalDate(LocalDateTime.parse(tokens[4], DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                flight.setArrivalAirportCode(tokens[5]);
                flight.setAircraftId(Integer.parseInt(tokens[6]));
                flight.setStatus(tokens[7]);
                InsertExecutor.insertFlight(flight);
            }
            case "Ticket" -> {
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

    }
}
