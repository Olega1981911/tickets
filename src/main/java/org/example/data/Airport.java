package org.example.data;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Airport {
    private String code;
    private String country;
    private String city;
}
