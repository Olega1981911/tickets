package org.example.data;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Airport {
    private String code;
    private String country;
    private String city;
}
