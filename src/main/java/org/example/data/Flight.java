package org.example.data;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    private Long id;
    private String flightNo;
    private LocalDateTime departureDate;
    private String departureAirportCode;
    private LocalDateTime arrivalDate;
    private String arrivalDateCode;
    private Integer aircraftId;
    private String status;

    @Override
    public String toString() {
        return "Flight{" +
               "id=" + id +
               ", flightNo='" + flightNo + '\'' +
               ", departureDate=" + departureDate +
               ", departureAirportCode='" + departureAirportCode + '\'' +
               ", arrivalDate=" + arrivalDate +
               ", arrivalAirportCode='" + arrivalDateCode + '\'' +
               ", aircraftId=" + aircraftId +
               ", status='" + status + '\'' +
               '}';
    }
}
