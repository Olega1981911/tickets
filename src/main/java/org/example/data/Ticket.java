package org.example.data;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private Long id;
    private String passengerNo;
    private String passengerName;
    private Long flightId;
    private String seatNo;
    private Double cost;
}
