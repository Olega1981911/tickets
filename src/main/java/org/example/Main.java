package org.example;

import org.example.data.Flight;
import org.example.util.FileDataLoader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = ExecutorDML.getFlightsById(3);
        String filepath = "";
        FileDataLoader.loadDataFromFile(filepath);

        if (flights.isEmpty()) {
            System.out.println("No flights found.");
        } else {
            for (Flight flight : flights) {
                System.out.println(flight);
            }
        }
    }
}