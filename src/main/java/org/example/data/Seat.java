package org.example.data;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Seat {
    private int aircraftId;
    private String seatNo;
}
