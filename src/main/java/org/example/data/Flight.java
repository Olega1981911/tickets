package org.example.data;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Setter
@Getter
public class Flight {
    private long id;
    private String flightNo;
    private LocalDateTime departureDate;
    private String departureAirportCode;
    private LocalDateTime arrivalDate;
    private String arrivalAirportCode;
    private int aircraftId;
    private String status;

    public Flight(long id, String flightNo, LocalDateTime departureDate, String departureAirportCode, LocalDateTime arrivalDate, String arrivalAirportCode, int aircraftId, String status) {
        this.id = id;
        this.flightNo = flightNo;
        this.departureDate = departureDate;
        this.departureAirportCode = departureAirportCode;
        this.arrivalDate = arrivalDate;
        this.arrivalAirportCode = arrivalAirportCode;
        this.aircraftId = aircraftId;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightNo='" + flightNo + '\'' +
                ", departureDate=" + departureDate +
                ", departureAirportCode='" + departureAirportCode + '\'' +
                ", arrivalDate=" + arrivalDate +
                ", arrivalAirportCode='" + arrivalAirportCode + '\'' +
                ", aircraftId=" + aircraftId +
                ", status='" + status + '\'' +
                '}';
    }
}
