package org.example.data;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private long id;
    private String passengerNo;
    private String passengerName;
    private long flightId;
    private String seatNo;
    private double cost;
}
