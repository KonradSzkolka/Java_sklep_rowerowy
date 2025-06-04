package Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
    private String firstName;
    private String lastName;
    private String address;
    private String postCode;
    private String city;
}
