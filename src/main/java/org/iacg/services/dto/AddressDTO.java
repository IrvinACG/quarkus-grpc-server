package org.iacg.services.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AddressDTO {

    private Long id;

    private String number;

    private String street;

    private String city;

    private Long userId;
}
