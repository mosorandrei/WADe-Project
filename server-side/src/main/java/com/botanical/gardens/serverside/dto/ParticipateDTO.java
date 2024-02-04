package com.botanical.gardens.serverside.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipateDTO {
    private String tourName;
    private String firstName;
    private String lastName;
}
