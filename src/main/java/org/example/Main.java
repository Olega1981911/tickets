package org.example;

import org.example.data.Flight;
import org.example.data.Ticket;
import org.example.util.FileDataLoader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String status = "SCHEDULED";
        List<Flight> flights = ExecutorDML.getFullFlightByStatus(status);
        for (Flight flight : flights) {
            System.out.println(flight);
        }
        List<Ticket> ticket = ExecutorDML.getTicketsById(2);
        for (Ticket ticket1 : ticket) {
            System.out.println(ticket1);
        }
        // String filepath = "insert.txt";
       // FileDataLoader.loadDataFromFile(filepath);

      //  if (flights.isEmpty()) {
       //     System.out.println("No flights found.");
      //  } else {
        //    for (Flight flight : flights) {
        //        System.out.println(flight);
        //    }
      //  }
    }
}